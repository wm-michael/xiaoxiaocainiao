package im.wangyan.network

import im.wangyan.network.data.PopMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovie_Suspend(@Query("page") page: Int):PopMovies

    @GET("/3/movie/popular")
    fun getPopularMovie_Call(@Query("page") page: Int): Call<PopMovies>

}