package im.wangyan.xiaoxiaocainiao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.custom_recycleview_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val tv = viewHolder.tv
        tv.text = dataSet[position]
        tv.setBackgroundResource(R.drawable.selector_item_background);

        tv.setOnClickListener {
            onItemClickListener?.onItemClick(tv, position)
        }
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tv: TextView = v.findViewById(R.id.tv)
    }
}