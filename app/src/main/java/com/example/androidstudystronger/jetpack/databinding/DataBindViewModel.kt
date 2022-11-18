package com.example.androidstudystronger.jetpack.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataBindViewModel : ViewModel() {
    val mmMutableLiveData3 = MutableLiveData("start")
    var mMutableLiveData = MutableLiveData<String>()
}