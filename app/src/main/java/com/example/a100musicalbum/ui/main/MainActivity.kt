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

// TODO: useless annotation
@OptIn(InternalSerializationApi::class)
class MainActivity : AppCompatActivity() {
    // TODO: https://developer.android.com/topic/libraries/view-binding
    private lateinit var binding: ActivityMainBinding
    // TODO: don't use lateinit in your code, better use 'by lazy' delegate
    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var myViewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // TODO: don't create ViewModel like this, use "by viewModels()" or koin "by viewModel()"
        //  https://developer.android.com/topic/libraries/architecture/viewmodel
        myViewModel = AlbumViewModel()

        albumAdapter = AlbumAdapter { album ->
            val intent = DetailActivity.newIntent(this, album)
            startActivity(intent)
        }

        // TODO: IMHO: you can try to move this setting to XML, imho it is better to keep as much as possible in xml. We need Kotlin code for something dynamic more often
        binding.recyclerViewAlbums.layoutManager = GridLayoutManager(this, 2)

        binding.recyclerViewAlbums.adapter = albumAdapter

        lifecycleScope.launch {
            myViewModel.albums.collect { feedWrapper ->
                feedWrapper.feed?.results?.let { albumAdapter.submitList(it) }
            }
        }
    }
}
