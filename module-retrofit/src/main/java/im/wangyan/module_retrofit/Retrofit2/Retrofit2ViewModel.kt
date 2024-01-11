package im.wangyan.module_retrofit.Retrofit2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import im.wangyan.network.MovieService
import im.wangyan.network.Network
import im.wangyan.network.data.PopMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class Retrofit2ViewModel : ViewModel() {

    private val service = Network.getRetrofit().create(MovieService::class.java)

    var moviesData = MutableLiveData<PopMovies>()

    fun fetchUser() {

        viewModelScope.launch {

            flow {
                val data = service.getPopularMovie_Suspend(1)
                emit(data)
            }.flowOn(Dispatchers.IO)
                .catch { e -> e.printStackTrace() }
                .collect {
                    moviesData.value = it
                }
        }

    }

}
