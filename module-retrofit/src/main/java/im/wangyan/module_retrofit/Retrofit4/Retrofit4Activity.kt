package im.wangyan.module_retrofit.Retrofit4

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import im.wangyan.module_retrofit.R
import im.wangyan.network.ApiResult

class Retrofit4Activity : AppCompatActivity() {

    private lateinit var viewModel: Retrofit4ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit4)

        viewModel = ViewModelProvider(this).get(Retrofit4ViewModel::class.java)

        val mybtn =  findViewById<Button>(R.id.mybtn)
        val tv =  findViewById<TextView>(R.id.tv)
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        mybtn.setOnClickListener {
            tv.text = "loading..."
            viewModel.fetchUser()
        }

        viewModel.moviesData.observe(this) {
            Log.d("XIAOXIAO", it.toString())
            when(it) {
                is ApiResult.Loading -> {
                    tv.text = "Loading! loading..."
                }
                is ApiResult.Success -> {
                    tv.text = "Success! count: " + it.data.moviesList.size.toString() + "\n" + it.data.toString()
                }
                is ApiResult.Error -> {
                    tv.text = "Error! error: " + it.code + " " + it.message
                }
            }
        }
    }
}