package com.example.module_glide

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lib_glide.setBlurView
import com.example.lib_glide.setScanImage
import com.example.lib_glide.setUrl
import com.example.lib_glide.setUrlCircle
import com.example.lib_glide.setUrlGif
import com.example.lib_glide.setUrlRound
import com.example.module_glide.databinding.WokaoCustomRecycleviewItemBinding

class CustomAdapter(private val context: Context, private val dataSet: Array<String>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: WokaoCustomRecycleviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WokaoCustomRecycleviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.tv.text = dataSet[position]
            when (position) {
                0 -> binding.iv.setUrl("https://photo.669pic.com/show_photos/97/42/46/47/28b6d0da7c142e55cbceb0a255198cab.jpg")
                1 -> binding.iv.setUrl("https://img1.baidu.com/it/u=688639570,3955579301&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=704")
//                2 -> binding.iv.setUrlNoCache(imageString)
                3 -> binding.iv.setUrlCircle("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeUeZQS6V7xRUTn6EuH90qR41FMgBycCF5vA&usqp=CAU")
//                4 -> binding.iv.setUrlCircleBorder(imageString, 5.0f, context.resources.getColor(R.color.module_glide_pink))
                5 -> binding.iv.setUrlRound("https://i0.wp.com/picjumbo.com/wp-content/uploads/beautiful-foggy-autumn-morning-nature-scenery-free-photo.jpg?w=600&quality=80", 8)
//                6 -> binding.iv.setUrl(imageString)
//                7 -> binding.iv.setUrl(imageString)
                8 -> binding.iv.setUrlGif("https://img.zcool.cn/community/0144b05c8ce84ea801214168d91598.gif")
                9 -> binding.iv.setBlurView("https://photo.16pic.com/00/34/59/16pic_3459379_b.jpg")
                10 -> binding.iv.setScanImage("https://img13.51tietu.net/pic/20200118/zkyhgaztxn4zkyhgaztxn4.jpg", 500)
            }

        }

    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.binding.iv.setImageBitmap(null)
    }

}