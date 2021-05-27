package com.example.moneymanagement.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanagement.model.PaymentsModel
import com.example.moneymanagement.R
import com.example.moneymanagement.local.db.PaymentsEntity

class PaymentsAdapter(context: Context, itemList: List<PaymentsEntity>) :
    RecyclerView.Adapter<PaymentsAdapter.ViewHolder?>() {
    private val itemList: List<PaymentsEntity>
    private val context: Context
    private var clickListener: ItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var list_txt_payments: TextView

        var list_icn_payments: ImageView

        init {
            list_txt_payments = itemView.findViewById(R.id.list_txt_payments) as TextView

            list_icn_payments = itemView.findViewById(R.id.list_icn_payments) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.row_payments, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.list_txt_payments.setText(itemList[position].PaymentType)

        holder.list_icn_payments.setImageResource(itemList[position].PaymentTypeIcon)
        holder.list_icn_payments.setOnClickListener {
            if (clickListener != null) clickListener!!.itemClick(
                holder.list_txt_payments,
                position
            )
        }
        holder.list_icn_payments.setTag(holder)
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