package com.example.module_workmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val datalist: Array<String>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    fun interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv: TextView = view.findViewById(R.id.tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.module_workmanage_custom_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tv = holder.tv
        tv.text = datalist[position]
        tv.setBackgroundResource(R.drawable.custom_list_item_bg)
        tv.setOnClickListener {
            onItemClickListener.onItemClick(tv, position)
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

}