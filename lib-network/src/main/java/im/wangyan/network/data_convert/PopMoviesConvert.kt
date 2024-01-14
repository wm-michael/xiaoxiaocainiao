package im.wangyan.network.data_convert

import com.google.gson.annotations.SerializedName

data class PopMoviesConvert (
    val page: Int,
    @SerializedName("results")
    val moviesList: List<ResultToMovieList>,
    val total_pages: Int,
    val total_results: Int
)