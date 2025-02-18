package com.example.a100musicalbum.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a100musicalbum.databinding.ActivityMainBinding
import com.example.a100musicalbum.ui.detail.DetailActivity
import com.example.a100musicalbum.viewmodel.AlbumViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin



class MainActivity : AppCompatActivity() {
    // TODO: https://developer.android.com/topic/libraries/view-binding
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val albumAdapter: AlbumAdapter by lazy {
        AlbumAdapter { album ->
            val intent = DetailActivity.newIntent(this, album)
            startActivity(intent)
        }
    }

    private val myViewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //  https://developer.android.com/topic/libraries/architecture/viewmodel
        binding.recyclerViewAlbums.adapter = albumAdapter
        lifecycleScope.launch {
            myViewModel.albums.collect { feedWrapper ->
                feedWrapper.feed?.albums?.let { albumAdapter.submitList(it) }
            }
        }
    }
}
