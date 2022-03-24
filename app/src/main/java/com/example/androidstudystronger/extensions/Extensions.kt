package com.example.androidstudystronger.extensions

import com.example.androidstudystronger.utils.ScreenUtils
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty


fun Number.dp(): Float {
    return ScreenUtils.dp2px(this.toFloat())
}

fun Number.sp(): Float {
    return ScreenUtils.sp2px(this.toFloat())
}

fun Number.px2dp(): Int {
    return ScreenUtils.px2dp(this.toInt())
}

fun <T : Any> Delegates.notNullSingleInit(): ReadWriteProperty<Any?, T> = NotNullSingleInitVar()
