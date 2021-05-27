package com.example.moneymanagement.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewsSliderAdapter(private val layouts:IntArray) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
    override fun getItemViewType(position: Int): Int {
        return layouts.get(position)
    }

    override fun getItemCount(): Int {
        return layouts.size
    }

    inner class SliderViewHolder(view: View?) : RecyclerView.ViewHolder(
        view!!
    ) {
        var title: TextView? = null
        var year: TextView? = null
        var genre: TextView? = null
    }
}