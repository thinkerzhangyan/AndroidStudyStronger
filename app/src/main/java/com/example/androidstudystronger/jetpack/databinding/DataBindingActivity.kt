package com.example.androidstudystronger.jetpack.databinding

import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.*
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.example.androidstudystronger.R
import com.example.androidstudystronger.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {

    private var binding: ActivityDataBindingBinding? = null

    //从model层来的数据
    private var user: User? = null
    private var money: Money? = null

    var userId = ObservableInt(111)
    var userFamily = ObservableField("农村")
    var userAge: ObservableDouble = ObservableDouble(31.toDouble())
    var userSex: ObservableFloat = ObservableFloat(1f)

    private val viewModel by viewModels<DataBindViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_data_binding
        )
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        //数据是从网络或是数据库拿来的

        //数据是从网络或是数据库拿来的
        user = User("jett", "123", "10000000")

        money = Money("10000000", "美元")

        binding?.user = user //view.setText(text);


        binding?.money = money

        binding!!.viewModel = viewModel

        binding?.userId = userId
        binding?.userFamily = userFamily
        binding?.userAge = userAge
        binding?.userSex = userSex

        //binding.setVariable(BR.name,"zhangsan");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    try {
//                        Thread.sleep(1000);
//                        user.setName(user.getName()+"1");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();


        //binding.setVariable(BR.name,"zhangsan");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    try {
//                        Thread.sleep(1000);
//                        user.setName(user.getName()+"1");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
        binding?.root?.postDelayed({ viewModel.mMutableLiveData.setValue("hahaha") }, 1000)

        binding?.root?.postDelayed({ user!!.list[0] = "aaaa" }, 1010)

        binding?.root?.postDelayed({ user!!.observableArrayMap["zq"] = "dfjdjfla" }, 1020)

        binding?.root?.postDelayed({
            userId.set(111111)
            userFamily.set("农村-城市")
            userAge.set(32.0)
            userSex.set(11f)
        }, 1030)


        binding?.addOnRebindCallback(object : OnRebindCallback<ViewDataBinding>() {
            override fun onPreBind(binding: ViewDataBinding?): Boolean {
                return super.onPreBind(binding)
            }

            override fun onCanceled(binding: ViewDataBinding?) {
                super.onCanceled(binding)
            }

            override fun onBound(binding: ViewDataBinding?) {
                super.onBound(binding)
            }
        })

        binding?.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {}
        })


        //  viewModel.mmMutableLiveData3.value = "end"

//        Handler(Looper.getMainLooper()).postDelayed({ viewModel.mmMutableLiveData3.value = "end"
//        }, 1000)
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({ viewModel.mmMutableLiveData3.value = "end" }, 1000)
    }
}