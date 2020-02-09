package com.chunter.coordinatorlayoutplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chunter.coordinatorlayoutplayground.databinding.ActivityScrollingBinding

class ScrollingActivity : AppCompatActivity() {

    private val filterAdapter = FilterAdapter()
    private val mainAdapter = MainAdapter()

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
    }
}
