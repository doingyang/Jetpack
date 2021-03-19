package com.project.jetpack.viewpager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.jetpack.R

class MyViewPager2Adapter : RecyclerView.Adapter<MyViewPager2Adapter.ViewHolder>() {

    private var mColorsList = ArrayList<Int>()

    fun setData(colors: List<Int>) {
        mColorsList.clear()
        if (colors.isNotEmpty()) {
            mColorsList.addAll(colors)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_my_viewpager2,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.setBackgroundColor(mColorsList[position])
    }

    override fun getItemCount(): Int {
        return mColorsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view: View

        init {
            itemView.tag = this
            view = itemView.findViewById<View>(R.id.view_item)
        }
    }
}