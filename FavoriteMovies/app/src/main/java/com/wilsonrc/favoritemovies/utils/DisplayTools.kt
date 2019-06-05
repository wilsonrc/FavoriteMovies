package com.wilsonrc.favoritemovies.utils

import android.app.Activity
import android.util.DisplayMetrics



object DisplayTools {

    fun isTabletDisplay(activity : Activity) : Boolean{
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)

        val yInches = metrics.heightPixels / metrics.ydpi
        val xInches = metrics.widthPixels / metrics.xdpi
        val diagonalInches = Math.sqrt((xInches * xInches + yInches * yInches).toDouble())
        return (diagonalInches >= 6.5)
    }

}