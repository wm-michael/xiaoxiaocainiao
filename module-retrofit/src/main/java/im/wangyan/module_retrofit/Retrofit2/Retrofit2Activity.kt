package im.wangyan.module_retrofit.Retrofit2

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import im.wangyan.module_retrofit.R

class Retrofit2Activity : AppCompatActivity() {

    private lateinit var viewModel: Retrofit2ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit2)

        viewModel = ViewModelProvider(this).get(Retrofit2ViewModel::class.java)

        val mybtn =  findViewById<Button>(R.id.mybtn)
        val tv =  findViewById<TextView>(R.id.tv)
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        mybtn.setOnClickListener {
            tv.text = "loading..."
            viewModel.fetchUser()
        }

        viewModel.moviesData.observe(this) {
            Log.d("XIAOXIAO", it.toString())
            tv.text = "count: " + it.results.size.toString() + "\n" + it.toString()
        }
    }
}