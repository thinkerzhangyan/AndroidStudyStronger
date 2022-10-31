package com.example.androidstudystronger.jetpack.databinding

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidstudystronger.R
import com.example.androidstudystronger.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {

    private val viewModel by viewModels<DataBindViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding = DataBindingUtil.setContentView<ActivityDataBindingBinding>(this,R.layout.activity_data_binding)
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel

        viewModel.mmMutableLiveData3.value = "end"

//        Handler(Looper.getMainLooper()).postDelayed({ viewModel.mmMutableLiveData3.value = "end"
//        }, 1000)
    }
}