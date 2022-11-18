package com.example.androidstudystronger.jetpack.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter


class BindingAdapterHelper {

    companion object {

        @BindingAdapter("text")
        @JvmStatic
        fun setText(textView: TextView, text: String?) {

        }

    }
}