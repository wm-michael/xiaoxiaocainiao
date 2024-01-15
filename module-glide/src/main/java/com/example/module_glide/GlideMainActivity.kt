package com.example.module_glide

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib_glide.setUrl
import com.example.module_glide.databinding.ActivityGlideMainBinding

class GlideMainActivity : AppCompatActivity() {

    lateinit var binding : ActivityGlideMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGlideMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imagelist = arrayOf("加载网络图片，开启缓存", "加载本地图片，开启缓存", "加载网络图片，不开启缓存", "加载圆形图片", "加载边框圆形图片", "加载圆角图片", "设置错误图片", "加载后回调","加载Gif图片", "设置图片高斯模糊", "设置高度自适应")
        val iv0 = findViewById<ImageView>(R.id.iv0)
        iv0.setUrl("https://visualhunt.com/photos/11/sun-face-wall-plaque.jpg?s=wh5")

        binding.rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        val adapter = CustomAdapter(this, imagelist)
        binding.rv.adapter = adapter
    }
}