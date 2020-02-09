package com.chunter.coordinatorlayoutplayground

import android.content.Context
import android.util.AttributeSet
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView

class CollapsingRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr), CoordinatorLayout.AttachedBehavior {

    override fun getBehavior(): CoordinatorLayout.Behavior<*> =
        CollapsingRecyclerViewBehaviour(context)
}