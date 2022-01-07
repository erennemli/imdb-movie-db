package com.example.imdb.base

import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.imdb.util.extension.lazyThreadSafetyNone
import com.example.imdb.util.extension.observeNonNull
import com.example.imdb.util.navigation.NavigationCommand
import java.lang.reflect.ParameterizedType

class BaseFragmentDecorator<VM: BaseViewModel, B : ViewDataBinding>(
    val fragment: Fragment,
    val binder: B
) {
    @Suppress("UNCHECKED_CAST")
    val viewModel by lazyThreadSafetyNone {
        val persistenViewModelClass = (fragment.javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>
        return@lazyThreadSafetyNone ViewModelProvider(fragment)
            .get(persistenViewModelClass)
    }

    inline fun <reified VM : ViewModel> activityViewModels(): Lazy<VM> {
        return fragment.activityViewModels()
    }

    inline fun <reified VM: ViewModel> viewModels(): Lazy<VM> {
        return fragment.viewModels()
    }

    inline fun <reified VM: ViewModel> parentViewModels(): Lazy<VM> {
        return fragment.requireParentFragment().viewModels()
    }

    inline fun <reified VM: ViewModel> navGraphViewModels(@IdRes navGraphId: Int): Lazy<VM> {
        return fragment.navGraphViewModels(navGraphId)
    }

    init {
        with(binder) {
            lifecycleOwner = fragment.viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
        }
    }

    fun startObservers() {
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.navigation.observeNonNull(fragment.viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { command ->
                handleNavigation(command)
            }
        }
    }

    private fun handleNavigation(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.ToDirectionId -> {
                fragment.findNavController()
                    .navigate(command.resId, command.args, command.navOptions)
            }
            is NavigationCommand.ToDirection -> {
                fragment.findNavController().navigate(command.directions, getExtras())
            }
            is NavigationCommand.Back -> fragment.findNavController().navigateUp()
        }
    }

    private fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()
}