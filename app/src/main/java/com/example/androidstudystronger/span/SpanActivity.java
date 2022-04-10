package com.example.androidstudystronger.span;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.example.androidstudystronger.R;

import androidx.appcompat.app.AppCompatActivity;

public class SpanActivity extends AppCompatActivity {


    private TextView mTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span);
        mTextView = findViewById(R.id.text_view);
        String text =
                "这波疫情你觉得短时间内会结束吗？更多观点咱们跟贴交流" +
                        "\n" + "[红方] 会结束" +
                        "\n" + "[蓝方] 不会";

        mTextView.setText(getSpanContent(text,R.drawable.biz_comment_pk_comment_red_icon));

    }

    private SpannableStringBuilder getSpanContent(String text, int supportIconId) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        Drawable drawable = this.getDrawable(supportIconId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        CenterVerticalImgSpan imageSpan = new CenterVerticalImgSpan(drawable);
        ssb.setSpan(imageSpan,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }

}