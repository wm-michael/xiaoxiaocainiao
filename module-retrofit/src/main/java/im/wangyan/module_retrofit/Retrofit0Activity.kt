package im.wangyan.module_retrofit

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import im.wangyan.network.MovieService
import im.wangyan.network.Network
import kotlinx.coroutines.launch

class Retrofit0Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit0)

        val service = Network.getRetrofit().create(MovieService::class.java)

        val mybtn =  findViewById<Button>(R.id.mybtn)
        val tv =  findViewById<TextView>(R.id.tv)
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        mybtn.setOnClickListener {
            Log.d("XIAOXIAO", "button clicked.")
            lifecycleScope.launch {
                val abc = service.getPopularMovie_Suspend(1)
                Log.d("XIAOXIAO", abc.toString())
                Log.d("XIAOXIAO", "count: $abc.results.size")
                tv.text = "count: " + abc.results.size.toString() + "\n" + abc.toString()

            }
        }
    }
}