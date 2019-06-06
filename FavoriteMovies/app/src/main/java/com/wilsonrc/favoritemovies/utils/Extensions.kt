package com.wilsonrc.favoritemovies.utils

import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerAppCompatActivity

/**
 * Method to replace the fragment. The [fragment] is added to the container view with id
 * [containerViewId] and a [tag]. The operation is performed by the supportFragmentManager.
 */
fun DaggerAppCompatActivity.replaceFragmentSafely(fragment: Fragment,
                                                  tag: String,
                                                  allowStateLoss: Boolean = false,
                                                  @IdRes containerViewId: Int,
                                                  @AnimRes enterAnimation: Int = 0,
                                                  @AnimRes exitAnimation: Int = 0,
                                                  @AnimRes popEnterAnimation: Int = 0,
                                                  @AnimRes popExitAnimation: Int = 0) {
    val ft = supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        .replace(containerViewId, fragment, tag)
    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}