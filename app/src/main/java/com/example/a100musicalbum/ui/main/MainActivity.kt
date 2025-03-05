package com.example.a100musicalbum.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.a100musicalbum.databinding.ActivityMainBinding
import com.example.a100musicalbum.ui.detail.DetailActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    // TODO: https://developer.android.com/topic/libraries/view-binding
    /* TODO: либо либу юзай либо наллабл var с занулением в onDestroy */
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter { album ->
            val intent = DetailActivity.newIntent(this, album)
            startActivity(intent)
        }
    }
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerViewAlbums.adapter = mainAdapter

        lifecycleScope.launch {
            viewModel.albums.collect { albumList ->
               mainAdapter.submitList(albumList) }
            }
        }
    }
