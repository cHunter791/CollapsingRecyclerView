package com.chunter.coordinatorlayoutplayground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunter.coordinatorlayoutplayground.databinding.ListItemMainBinding

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            ListItemMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        ) {}
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = Unit
}