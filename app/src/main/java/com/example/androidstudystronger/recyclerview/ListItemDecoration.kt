package com.example.androidstudystronger.recyclerview

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudystronger.R
import com.example.androidstudystronger.extensions.dp
import com.example.androidstudystronger.MainApplication
import kotlin.math.roundToInt

class ListItemDecoration @JvmOverloads constructor() : RecyclerView.ItemDecoration() {

    companion object {
        private val SMALL_DIVIDER_HEIGHT = 20.dp().toInt()
        private val GROUP_ITEM_HORIZONTAL_OFFSET = 19.dp().toInt()
    }

    private val paint = Paint().apply {
        color = MainApplication.instance.getColor(R.color.blackF5)
        style = Paint.Style.FILL
    }

    private val bounds = Rect()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
//        val holder = parent.getChildViewHolder(view)
//        if (holder is GroupTitleItemHolder) {
//            outRect.set(0, 0, 0, 0)
//            return
//        }
        outRect.set(0, 0, 0, SMALL_DIVIDER_HEIGHT)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        c.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            c.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, bounds)
//            val holder = parent.getChildViewHolder(child)
//            if (holder is GroupTitleItemHolder) {
//                continue
//            }
//            if (holder is FooterHolder) {
//                continue
//            }
            if (i == 0) {
                Log.d("yan", "" + bounds.toString())
                child.translationY= 10.dp()
                parent.getDecoratedBoundsWithMargins(child, bounds)
                Log.d("yan", "" + bounds.toString())
            }
            val bottom = bounds.bottom + child.translationY.roundToInt()
            val top = bottom - SMALL_DIVIDER_HEIGHT
            val leftOffset = GROUP_ITEM_HORIZONTAL_OFFSET
            val rightOffset = -GROUP_ITEM_HORIZONTAL_OFFSET
            c.drawRect(
                left.toFloat() + leftOffset.toFloat(),
                top.toFloat(),
                right.toFloat() + rightOffset.toFloat(),
                bottom.toFloat(),
                paint
            )
        }
        c.restore()
    }
}