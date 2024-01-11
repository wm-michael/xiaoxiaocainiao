package im.wangyan.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Network {

    fun getRetrofit(): Retrofit {

        val logInterceptor = HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { message ->
                println("LOG-APP: $message")
            }).apply {
            level= HttpLoggingInterceptor.Level.BODY
        }

        val builder = OkHttpClient.Builder()
            // 全局的读取超时时间
            .readTimeout(READ_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            // 全局的写入超时时间
            .writeTimeout(WRITE_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            // 全局的连接超时时间
            .connectTimeout(CONNECT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .addInterceptor(TMDBAPIInterceptor())
            .addInterceptor(logInterceptor)


        val retrofit = Retrofit.Builder()
            .baseUrl(DEBUG_URL_FEED_STREAM) //设置网络请求的Url地址
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
            .build()

        return retrofit
    }


}