package com.example.androidstudystronger.popupwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.androidstudystronger.R;

import androidx.appcompat.app.AppCompatActivity;

public class PopupActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

    }

    @Override
    public void onClick(View v) {
        PopupWindow popupWindow = new PopupWindow();
        Button button = new Button(this);
        button.setBackgroundColor(Color.YELLOW);
        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width, height);
        button.setLayoutParams(lp);
        popupWindow.setContentView(button);
        int x = 0;
        int y = 0;
        int gravity = Gravity.NO_GRAVITY;
        popupWindow.setClippingEnabled(false);
        switch (v.getId()) {
            case R.id.top:
                gravity = Gravity.TOP;
                button.setText("TOP");
                break;
            case R.id.center:
                gravity = Gravity.CENTER;
                button.setText("CENTER");
                break;
            case R.id.bottom:
                gravity = Gravity.BOTTOM;
                button.setText("BOTTOM");
                break;
            case R.id.left:
                gravity = Gravity.LEFT;
                button.setText("LEFT");
                break;
            case R.id.right:
                gravity = Gravity.RIGHT;
                button.setText("RIGHT");
                break;
            case R.id.right_bottom:
                gravity = Gravity.RIGHT | Gravity.BOTTOM;
                button.setText("RIGHT|BOTTOM");
                break;
            case R.id.right_top:
                gravity = Gravity.RIGHT | Gravity.TOP;
                button.setText("RIGHT|TOP");
                break;
            case R.id.left_bottom:
                gravity = Gravity.LEFT | Gravity.BOTTOM;
                button.setText("LEFT|BOTTOM");
                break;
            case R.id.left_top:
                gravity = Gravity.LEFT | Gravity.TOP;
                button.setText("LEFT| TOP");
                break;
        }
        //popupWindow.showAtLocation(v, gravity, x, y);
        showSubjectPopupWindow(v.getContext(),v);
    }

    private void showSubjectPopupWindow(Context context, View view) {//想要选择框在哪个view下面，就把那个view作为参数传递进来

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(context).inflate(R.layout.subject_select_layout,null);


        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        //实例化一个ColorDrawable颜色为半透明，已达到变暗的效果
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(dw);

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view, 0, 0);

    }
}