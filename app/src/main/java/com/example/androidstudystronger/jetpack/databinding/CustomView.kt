package com.example.androidstudystronger.jetpack.databinding

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.*

@BindingMethods(value = [
    BindingMethod(type = CustomView::class,attribute = "android:custom_text",method = "showCustomToast")
])
class CustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var data: String? = ""

    companion object{

        @BindingAdapter("time")
        @JvmStatic
        fun setTime(view: CustomView, newValue: String?) {
            Log.i("MyBindingConversion", "commonLog - setTime: $newValue")
            if (view.data != newValue) {
                view.data = newValue
            }
        }

        @InverseBindingAdapter(attribute = "time", event = "timeAttrChanged")
        @JvmStatic
        fun getTime(view: CustomView): String? {
            Log.i("MyBindingConversion", "commonLog - getTime: ${view.data}")
            return view.data
        }

        @BindingAdapter("timeAttrChanged")
        @JvmStatic
        fun setListeners(
            view: CustomView,
            attrChange: InverseBindingListener
        ) {
            Log.i("MyBindingConversion", "commonLog - setListeners: ")
            // 设置 view 改变的监听，看需求可以是点击，滑动，双击，长按什么的。
            view.setOnClickListener {
                view.data = "click"
                attrChange.onChange()
            }
        }

    }

    fun showCustomToast(text:String){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }

    //如果把showCustomToast写成这种形式，会报错
//    fun showCustomToast(view: CustomView, text: String) {
//        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
//    }
}