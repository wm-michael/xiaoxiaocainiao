package im.wangyan.module_retrofit

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import im.wangyan.network.MovieService
import im.wangyan.network.Network
import im.wangyan.network.data.PopMovies
import retrofit2.Call
import retrofit2.Response

class Retrofit1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit1)

        val service = Network.getRetrofit().create(MovieService::class.java)

        val mybtn =  findViewById<Button>(R.id.mybtn)
        val tv =  findViewById<TextView>(R.id.tv)
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        mybtn.setOnClickListener {
            Log.d("XIAOXIAO", "button clicked.")
            val abc = service.getPopularMovie_Call(1)
            abc.enqueue(object : retrofit2.Callback<PopMovies> {
                override fun onResponse(call: Call<PopMovies>, response: Response<PopMovies>) {
                    Log.d("XIAOXIAO", "response: ${response.body()}")
                    tv.text = "没有数据"
                    response.let {
                        val pm = it.body()
                        tv.text = "count: " + pm?.results?.size.toString() + "\n" + pm.toString()
                    }
                }

                override fun onFailure(call: Call<PopMovies>, t: Throwable) {
                    Log.d("XIAOXIAO", "failed: ${t.toString()}")
                }

            })
        }
    }
}
