package com.example.lib_common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.lib_common.R

class CommonListAdapter(private val dataset: Array<String>): RecyclerView.Adapter<CommonListAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener ) {
        this.onItemClickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView = itemView.findViewById(R.id.tv)
    }

    @LayoutRes
    fun getLayoutView():Int {
        return R.layout.common_list_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(getLayoutView(), parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tv = holder.tv
        tv.text = dataset[position]
        tv.setBackgroundResource(R.drawable.common_selector_item_background);

        tv.setOnClickListener {
            onItemClickListener?.onItemClick(tv, position)
        }
    }
}
