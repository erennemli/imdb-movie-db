package com.example.imdb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding>(contentLayoutId: Int) :
    Fragment(contentLayoutId) {

    protected lateinit var decorator: BaseFragmentDecorator<VM, B>

    protected val binder get() = decorator.binder
    protected val viewModel get() = decorator.viewModel

    open fun initialize() {
        // no-op
    }

    open fun observeData() {
        // no-op
    }

    protected inline fun <reified VM : ViewModel> viewModels() =
        decorator.viewModels<VM>()

    protected inline fun <reified VM: ViewModel> parentViewModels() =
        decorator.parentViewModels<VM>()

    protected inline fun <reified VM: ViewModel> navGraphViewModels(@IdRes navGraphId: Int) =
        decorator.navGraphViewModels<VM>(navGraphId)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        if (view != null) {
            val binder: B = DataBindingUtil.bind(view)!!
            decorator = BaseFragmentDecorator(this, binder)
        }

        initialize()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        decorator.startObservers()
    }
}