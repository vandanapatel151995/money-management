package com.example.moneymanagement.ui.main.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanagement.R
import com.example.moneymanagement.model.NavigationItemModel

class NavigationRVAdapter(private var items: ArrayList<NavigationItemModel>, private var currentPos: Int) :RecyclerView.Adapter<NavigationRVAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context
    private lateinit var navigation_icon: ImageView
    private lateinit var navigation_title: TextView
    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.row_nav_drawer, parent, false)
        navigation_icon=   navItem.findViewById<ImageView>(R.id.navigation_icon)
        navigation_title=    navItem.findViewById<TextView>(R.id.navigation_title)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
 
    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {



        // To highlight the selected item, show different background color
        if (position == currentPos) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_500))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }
        navigation_icon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
      navigation_title.setTextColor(Color.WHITE)
        //val font = ResourcesCompat.getFont(context, R.font.mycustomfont)
        //holder.itemView.navigation_text.typeface = font
        //holder.itemView.navigation_text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.toFloat())

       navigation_title.text = items[position].title

       navigation_icon.setImageResource(items[position].icon)
    }

}