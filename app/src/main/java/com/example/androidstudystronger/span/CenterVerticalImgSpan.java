package com.example.androidstudystronger.span;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.style.ImageSpan;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

/**
 * @author: guizhihong
 * @Date: 2019/11/1
 * @Description:带Image的span在垂直方向上和文字一起居中
 * @module reader
 * @blame 谢翔宇 & 周宏
 */
public class CenterVerticalImgSpan extends ImageSpan {

    private final char BOM_BIG_EDIAN = '\uFEFF';
    private final char BOM_LITTILE_EDIAN = '\uFFFE';
    private final char ELLIPSIS = '…';
    private int marginLeft;
    private int marginRight;

    public CenterVerticalImgSpan(@NonNull Drawable drawable) {
        super(drawable);
    }

    public CenterVerticalImgSpan(@NonNull Context context, @DrawableRes int resourceId) {
        super(context, resourceId);
    }

    public CenterVerticalImgSpan(@NonNull Context context, @DrawableRes int resourceId, int marginLeft, int marginRight) {
        super(context, resourceId);
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end,
                       Paint.FontMetricsInt fontMetricsInt) {
        Drawable drawable = getDrawable();
        Rect rect = drawable.getBounds();
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
            int fontHeight = fmPaint.bottom - fmPaint.top;
            int drHeight = rect.bottom - rect.top;

            int top = drHeight / 2 - fontHeight / 4;
            int bottom = drHeight / 2 + fontHeight / 4;

            fontMetricsInt.ascent = -bottom;
            fontMetricsInt.top = -bottom;
            fontMetricsInt.bottom = top;
            fontMetricsInt.descent = top;
        }
        if (marginLeft != 0 || marginRight != 0) {
            return rect.right + marginLeft + marginRight;
        } else {
            return rect.right;
        }
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        if (TextUtils.isEmpty(text)) {
            return;
        }

        if (checkEllipsisOrBomInSpanRange(text, start, end)) {
            canvas.drawText(text, start, end, x, y, paint);
            return;
        }
        if (marginLeft != 0) {
            x = x + marginLeft;
        }
        Drawable drawable = getDrawable();
        int transY = ((bottom - top) - drawable.getBounds().bottom) / 2 + top;
        canvas.save();
        canvas.translate(x, transY);
        drawable.draw(canvas);
        canvas.restore();
    }

    /**
     * 如果Text被省略的位置正好落在画span的区间，这时不应该画span，否则TextView原有的省略显示的功能被屏蔽
     *
     * @tip 对于emoji，如果emoji图片span匹配的字符串内正好有省略号字符或BOM字符，这样处理就有点问题，但这种case的概率应该很小
     */
    private boolean checkEllipsisOrBomInSpanRange(CharSequence text, int start, int end) {
        if (!TextUtils.isEmpty(text) || start > end) {
            return false;
        }
        String spanString = text.toString().substring(start, end);
        for (int index = 0; index < spanString.length(); index++) {
            char c = spanString.charAt(index);
            if (ELLIPSIS == c || BOM_BIG_EDIAN == c || BOM_LITTILE_EDIAN == c) {
                return true;
            }
        }
        return false;
    }
}
