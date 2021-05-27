package com.example.moneymanagement.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanagement.R
import com.example.moneymanagement.local.db.ExpenseEntity

class TransactionAdapter(context: Context, itemList: List<ExpenseEntity>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder?>() {
    private val itemList: List<ExpenseEntity>
    private val context: Context
    private var clickListener: ItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var list_txt_trn_item: TextView
        var list_txt_trn_date: TextView
        var list_txt_trn_amount: TextView
        var list_txt_trn_catogery: TextView

        var list_trans_icon: ImageView

        init {
            list_txt_trn_item = itemView.findViewById(R.id.list_txt_trn_item) as TextView
            list_txt_trn_date = itemView.findViewById(R.id.list_txt_trn_date) as TextView
            list_txt_trn_amount = itemView.findViewById(R.id.list_txt_trn_amount) as TextView
            list_txt_trn_catogery = itemView.findViewById(R.id.list_txt_trn_catogery) as TextView
            list_trans_icon = itemView.findViewById(R.id.list_trans_icon) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.row_transaction, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.list_txt_trn_item.setText(itemList[position].TransName)
        holder.list_txt_trn_date.setText(itemList[position].TransDate)
        holder.list_txt_trn_catogery.setText(itemList[position].TransCategory)
        holder.list_txt_trn_amount.setText("$"+itemList[position].TransAmount)

        holder.list_trans_icon.setImageResource(R.drawable.card)
        holder.list_trans_icon.setOnClickListener{  if (clickListener != null) clickListener!!.itemClick(holder.list_trans_icon, position)}
        holder.list_trans_icon.setTag(holder)
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