package com.example.androidstudystronger.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import com.example.androidstudystronger.MainApplication

object ScreenUtils {
        
    fun dp2px(context: Context, dp: Float): Float {
        return dp2px(context.resources, dp)
    }

    fun dp2px(resources: Resources, dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
    }

    @JvmStatic
    fun dp2px(dp: Float): Float {
        return dp2px(MainApplication.instance, dp)
    }

    fun sp2px(context: Context, sp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp,
            context.resources.displayMetrics
        )
    }

    fun sp2px(sp: Float): Float {
        return sp2px(MainApplication.instance, sp)
    }

    fun px2dp(px: Int): Int {
        return px2dp(MainApplication.instance.resources, px)
    }

    fun px2dp(resources: Resources, px: Int): Int {
        val scale = resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    /**
     * 获取已经用显示窗体宽度. 默认窗体宽度和屏幕宽度相同
     *
     * @param context
     * @return
     */
    fun getWindowWidth(context: Context?): Int {
        if (context == null || context !is Activity) {
            return -1
        }
        val manager = context.windowManager
        val outMetrics = DisplayMetrics()
        manager.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    fun getWindowHeight(context: Context?): Int {
        if (context == null || context !is Activity) {
            return -1
        }
        val manager = context.windowManager
        val outMetrics = DisplayMetrics()
        manager.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }
        
}