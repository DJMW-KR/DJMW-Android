package com.pss.djmw_android.widget.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

object Utils {
     fun checkSdkVersion(activity : Activity) {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true, activity)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false, activity)
            activity.window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean, activity: Activity) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}