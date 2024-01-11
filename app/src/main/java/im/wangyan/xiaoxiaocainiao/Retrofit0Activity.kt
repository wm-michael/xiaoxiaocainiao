package im.wangyan.xiaoxiaocainiao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import im.wangyan.network.MovieService
import im.wangyan.network.Network
import kotlinx.coroutines.launch

class Retrofit0Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit2)

        val service = Network.getRetrofit().create(MovieService::class.java)

        val mybtn =  findViewById<Button>(R.id.mybtn)
        val tv =  findViewById<TextView>(R.id.tv)
        mybtn.setOnClickListener {
            Log.d("XIAOXIAO", "button clicked.")
            lifecycleScope.launch {
                val abc = service.getPopularMovie(1)
                Log.d("XIAOXIAO", abc.toString())
                Log.d("XIAOXIAO", "count: $abc.results.size")
                tv.text = abc.toString() + "\n" + "count: " + abc.results.size.toString()

            }
        }
    }
}