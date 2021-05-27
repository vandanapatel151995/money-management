package com.example.moneymanagement.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanagement.R
import com.example.moneymanagement.local.db.CircleEntity

class MyCircleAdapter(context: Context, itemList: List<CircleEntity>) :
    RecyclerView.Adapter<MyCircleAdapter.ViewHolder?>() {
    private val itemList: List<CircleEntity>
    private val context: Context
    private var clickListener: ItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1: TextView

        var imageView: ImageView

        init {
            tv1 = itemView.findViewById(R.id.list_title) as TextView

            imageView = itemView.findViewById(R.id.list_avatar) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.row_circles, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv1.setText(itemList[position].PersonName)

        holder.imageView.setImageResource(itemList[position].PersonPhoto)
        holder.imageView.setOnClickListener{  if (clickListener != null) clickListener!!.itemClick(holder.imageView, position)}
        holder.imageView.setTag(holder)
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        clickListener = itemClickListener
    }

    override fun getItemCount(): Int = itemList.size
    interface ItemClickListener {
        fun itemClick(view: View?, position: Int)
    }

    init {
        this.itemList = itemList
        this.context = context
    }
}