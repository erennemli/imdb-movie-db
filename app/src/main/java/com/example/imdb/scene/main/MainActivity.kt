package com.example.imdb.scene.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.imdb.R
import com.example.imdb.base.BaseActivity
import com.example.imdb.databinding.ActivityMainBinding
import com.example.imdb.util.extension.observeNonNull
import com.example.imdb.util.navigation.NavigationCommand
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId: Int get() = R.layout.activity_main

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.main_host_fragment) as NavHostFragment
    }

    val navController: NavController by lazy { navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder.lifecycleOwner = this
        initBottomNavBar()
        observeData()
    }

    private fun initBottomNavBar() {
        NavigationUI.setupWithNavController(binder.bottomNavView, navHostFragment.navController)
    }

    private fun observeData() {
        viewModel.navigation.observeNonNull(this) {
            it.getContentIfNotHandled()?.let { command ->
                handleNavigation(command)
            }
        }
    }

    private fun handleNavigation(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.ToDirectionId -> navController.navigate(
                command.resId,
                command.args,
                command.navOptions
            )
            is NavigationCommand.ToDirection -> navController.navigate(command.directions)
            is NavigationCommand.Back -> navController.navigateUp()
        }
    }

}