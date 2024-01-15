package im.wangyan.module_retrofit.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import im.wangyan.module_retrofit.R

class CustomRecycleViewAdapter(private val dataSet: Array<String>): RecyclerView.Adapter<CustomRecycleViewAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tv: TextView = v.findViewById(R.id.tv)
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_list_item, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tv = holder.tv
        tv.text = dataSet[position]
        tv.setOnClickListener {
            onItemClickListener?.onItemClick(tv, position)
        }
    }


}