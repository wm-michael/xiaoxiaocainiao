package com.example.module_paging.demo0

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.module_paging.databinding.ActivityPagingDemo0Binding
import com.velmurugan.paging3android.MainRepository
import com.velmurugan.paging3android.MainViewModel
import com.velmurugan.paging3android.MoviePagerAdapter
import com.velmurugan.paging3android.MyViewModelFactory
import com.velmurugan.paging3android.RetrofitService
import kotlinx.coroutines.launch

class PagingDemo0Activity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val adapter = MoviePagerAdapter()
    lateinit var binding: ActivityPagingDemo0Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingDemo0Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        adapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                binding.progressDialog.isVisible = true
            else {
                binding.progressDialog.isVisible = false
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }


        lifecycleScope.launch {
            viewModel.getMovieList().observe(this@PagingDemo0Activity) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
        }
    }
}