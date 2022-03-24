package com.example.androidstudystronger.extensions

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class NotNullSingleInitVar<T : Any> : ReadWriteProperty<Any?, T> {

    private var value: T? = null    // 持有属性的值

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
            ?: throw IllegalStateException("Property ${property.name} should be initialized before get.")  // 如果属性的值为空，就会抛出异常
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (null == this.value) this.value = value
        else throw IllegalStateException("Property ${property.name} already initialized")   // 第二次赋值就抛出异常
    }
}