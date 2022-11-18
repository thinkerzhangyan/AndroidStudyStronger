package com.example.androidstudystronger.jetpack.databinding;

import com.example.androidstudystronger.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * viewModel
 */
public class Money extends BaseObservable {
    private String total;
    private String type;

    public Money(String total, String type) {
        this.total = total;
        this.type = type;
    }

    @Bindable
    public String getTotal() {
        return total;
    }


    public void setTotal(String total) {
        this.total = total;
        notifyPropertyChanged(BR.total);
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }
}
