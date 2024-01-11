package im.wangyan.network

import im.wangyan.network.data.PopMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int):PopMovies

}