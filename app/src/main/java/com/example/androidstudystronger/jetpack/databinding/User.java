package com.example.androidstudystronger.jetpack.databinding;

import com.example.androidstudystronger.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableArrayMap;

/**
 * viewModel
 */
public class User extends BaseObservable {
    private String name;
    private String pwd;
    private String deposit;

    public ObservableArrayList<String> list = new ObservableArrayList<>();

    public ObservableArrayMap<String, String> observableArrayMap = new ObservableArrayMap<>();

    public User(String name, String pwd, String deposit) {
        this.name = name;
        this.pwd = pwd;
        this.deposit = deposit;
        list.add("aaa");
        list.add("bbb");
        observableArrayMap.put("zq", "zhhhhh");
        observableArrayMap.put("wq", "wIIIII");
    }

    @Bindable
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);
    }

    @Bindable({"name", "pwd", "deposit"})
    public String getInfo() {
        return name + ":" + pwd + ":" + deposit;
    }

    @Bindable
    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String money) {
        this.deposit = money;
        notifyPropertyChanged(BR.deposit);
    }

}
