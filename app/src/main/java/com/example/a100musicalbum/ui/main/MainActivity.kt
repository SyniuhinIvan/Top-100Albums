package com.example.a100musicalbum.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.a100musicalbum.databinding.ActivityMainBinding
import com.example.a100musicalbum.ui.detail.DetailActivity
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    // TODO: https://developer.android.com/topic/libraries/view-binding
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainAdapter: MainAdapter by lazy {
        MainAdapter { album ->
            val intent = DetailActivity.newIntent(this, album)
            startActivity(intent)
        }
    }

    private val myViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //  https://developer.android.com/topic/libraries/architecture/viewmodel
        binding.recyclerViewAlbums.adapter = mainAdapter
        lifecycleScope.launch {
            myViewModel.albums.collect { feedWrapper ->
                feedWrapper?.feed?.albums?.let { mainAdapter.submitList(it) }
            }
        }
    }
}
