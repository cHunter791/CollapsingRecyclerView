package com.chunter.coordinatorlayoutplayground

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView

class CollapsingRecyclerViewBehaviour @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CoordinatorLayout.Behavior<CollapsingRecyclerView>(context, attrs) {

    private var maxHeight = context.resources.getDimensionPixelSize(
        R.dimen.collapsing_recycler_view_height
    )

    private var isPositive = false

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: CollapsingRecyclerView,
        dependency: View
    ): Boolean {
        return dependency is RecyclerView
    }

    override fun onMeasureChild(
        parent: CoordinatorLayout,
        child: CollapsingRecyclerView,
        parentWidthMeasureSpec: Int,
        widthUsed: Int,
        parentHeightMeasureSpec: Int,
        heightUsed: Int
    ): Boolean {
        val target = parent.getDependencies(child).firstOrNull() ?: return false
        if (target.marginTop != 0) return false
        target.updateLayoutParams<CoordinatorLayout.LayoutParams> {
            topMargin = maxHeight + parent.children
                .filter { it !is RecyclerView }
                .sumBy { it.height }
        }

        return false
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: CollapsingRecyclerView,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL && target is RecyclerView && target !is CollapsingRecyclerView
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: CollapsingRecyclerView,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        val offset = if (dyConsumed > 0) {
            // scrolling down
            (target as RecyclerView).computeVerticalScrollOffset()
        } else {
            // scrolling up
            (target as RecyclerView).computeVerticalScrollOffset() * -1
        }
        val newHeight = child.height - offset
        if (newHeight > maxHeight || newHeight < 0) return

        child.updateLayoutParams<CoordinatorLayout.LayoutParams> {
            height = newHeight
        }

        target.updateLayoutParams<CoordinatorLayout.LayoutParams> {
            topMargin = newHeight + coordinatorLayout.children
                .filter { it !is RecyclerView }
                .sumBy { it.height }
        }
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: CollapsingRecyclerView,
        target: View,
        type: Int
    ) {
        if (child.height < maxHeight) {
            child.updateLayoutParams<CoordinatorLayout.LayoutParams> {
                height = if (isPositive) 0 else maxHeight
            }
        }
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: CollapsingRecyclerView,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        isPositive = dy > 0
    }
}
