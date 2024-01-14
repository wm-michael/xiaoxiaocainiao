package im.wangyan.module_retrofit.Retrofit4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import im.wangyan.network.ApiResult
import im.wangyan.network.MovieServiceConvert
import im.wangyan.network.Network
import im.wangyan.network.data_convert.PopMoviesConvert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class Retrofit4ViewModel  : ViewModel() {

    private val service = Network.getRetrofit().create(MovieServiceConvert::class.java)

    var moviesData = MutableLiveData<ApiResult<PopMoviesConvert>>()

    fun fetchUser() {
        viewModelScope.launch {
            flow {
                val data = service.getPopularMovie_Suspend(1)
                emit(data)
            }.flowOn(Dispatchers.IO)
                .onStart {
                    moviesData.value = ApiResult.Loading
                    delay(1000)
                }
                .catch { e -> e.printStackTrace() }
                .collect {
                    moviesData.value = ApiResult.Success(it)
                }
        }

    }
}