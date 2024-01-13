package im.wangyan.module_ui_demo

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Random

class MainAdapter(private val mContext: Context, private val mList: List<String>) : RecyclerView.Adapter<MainAdapter.ViewHolder>(),
    View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false)
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 去掉随机背景色，跟随系统dynamic color
//        holder.mItemTextView.setBackgroundColor(randomColor())
        holder.mItemTextView.text = mList[position]
        holder.mItemTextView.tag = position
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItemTextView: TextView = itemView.findViewById(R.id.item_textView)
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onClick(v: View) {
        if (mListener != null) {
            mListener?.onItemClick(v, v.tag as Int)
        }
    }

    /**
     * 随机Color 避免纯色没有从255取值
     */
    private fun randomColor(): Int {
        Random().run {
            val red = nextInt(210)
            val green = nextInt(210)
            val blue = nextInt(210)
            return Color.rgb(red, green, blue)
        }
    }
}