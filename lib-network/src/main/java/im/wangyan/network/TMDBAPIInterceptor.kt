package im.wangyan.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TMDBAPIInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder: Request.Builder = request.newBuilder()
        val oldHttpUrl: HttpUrl = request.url
        val httpUrl = oldHttpUrl.newBuilder().apply {
            addQueryParameter("api_key", TMDB_API_KEY)
            // addQueryParameter language...
        }
        return chain.proceed(builder.url(httpUrl.build()).build())
    }
}