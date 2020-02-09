package com.chunter.coordinatorlayoutplayground

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chunter.coordinatorlayoutplayground.databinding.ActivityScrollingBinding

class ScrollingActivity : AppCompatActivity() {

    private val filterAdapter = FilterAdapter()
    private val mainAdapter = MainAdapter()

    private val maxHeight by lazy { resources.getDimensionPixelSize(R.dimen.collapsing_recycler_view_height) }

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.filterList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.filterList.adapter = filterAdapter

        binding.mainList.layoutManager = LinearLayoutManager(this)
        binding.mainList.adapter = mainAdapter
        binding.mainList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            private var isPositive = true

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                isPositive = dy >= 0
                Log.d("ScrollingActivity", "dy $dy")
                binding.filterList.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    val newHeight = binding.filterList.height - dy
                    if (newHeight < 0 || newHeight > maxHeight) return@updateLayoutParams

                    height = newHeight
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("ScrollingActivity", "Scroll Idle")
                    binding.filterList.updateLayoutParams<ConstraintLayout.LayoutParams> {
                        height = if (isPositive) 0 else maxHeight
                    }
                }
            }
        })
    }
}
