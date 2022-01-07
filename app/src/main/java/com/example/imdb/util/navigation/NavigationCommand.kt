package com.example.imdb.util.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

sealed class NavigationCommand {
    data class ToDirectionId(
        @IdRes val resId: Int,
        val args: Bundle?,
        val navOptions: NavOptions?,
        val navigatorExtras: Navigator.Extras?
    ) : NavigationCommand()

    data class ToDirection(val directions: NavDirections) : NavigationCommand()

    object Back : NavigationCommand()
}
