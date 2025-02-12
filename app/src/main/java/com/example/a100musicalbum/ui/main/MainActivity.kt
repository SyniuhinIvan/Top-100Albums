package com.example.a100musicalbum.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a100musicalbum.databinding.ActivityMainBinding
import com.example.a100musicalbum.ui.detail.DetailActivity
import com.example.a100musicalbum.viewmodel.AlbumViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var myViewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myViewModel = AlbumViewModel()

        albumAdapter = AlbumAdapter { album ->
            val intent = DetailActivity.newIntent(this, album)
            startActivity(intent)
        }

        binding.recyclerViewAlbums.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewAlbums.adapter = albumAdapter

        lifecycleScope.launch {
            myViewModel.albums.collect { feedWrapper ->
                feedWrapper.feed?.results?.let { albumAdapter.submitList(it) }
            }
        }
    }
}
