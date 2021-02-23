package com.example.weatherapp.helpers

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R

// Reference: https://stackoverflow.com/questions/46215810/recyclerview-remove-divider-decorator-after-the-last-item
class DividerItemDecorator(private val mDivider: Drawable, private val paddingPx: Int = 0) :
    RecyclerView.ItemDecoration() {

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft + paddingPx
        val dividerRight = parent.width - parent.paddingRight - paddingPx

        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child = parent.getChildAt(i)

            if (child.getTag(R.id.noDivider) == true) continue

            val params = child.layoutParams as RecyclerView.LayoutParams

            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + mDivider.intrinsicHeight

            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider.draw(canvas)
        }
    }
}

fun RecyclerView.withDivider(
    @DrawableRes drawableResId: Int = R.drawable.item_divider_light_gray,
    @DimenRes paddingResId: Int? = null
) {
    val paddingPx = paddingResId?.let { context.resources.getDimensionPixelSize(it) } ?: 0
    context.getDrawable(drawableResId)?.let { drawable ->
        addItemDecoration(
            DividerItemDecorator(
                drawable,
                paddingPx
            )
        )
    }
}