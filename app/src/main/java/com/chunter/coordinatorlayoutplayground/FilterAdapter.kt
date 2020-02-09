package com.chunter.coordinatorlayoutplayground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunter.coordinatorlayoutplayground.databinding.ListItemFilterBinding

class FilterAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            ListItemFilterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        ) {}
    }

    override fun getItemCount(): Int = 6

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = Unit
}