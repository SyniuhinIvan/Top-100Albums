package com.example.a100musicalbum.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.a100musicalbum.databinding.ItemAlbumBinding
import com.example.a100musicalbum.ui.component.AlbumUI

//   https://medium.com/geekculture/android-listadapter-a-better-implementation-for-the-recyclerview-1af1826a7d21
//   https://www.thedroidsonroids.com/blog/difference-between-listview-recyclerview
class MainAdapter(private val onClick: (AlbumUI) -> Unit) :
    ListAdapter<AlbumUI, MainAdapter.AlbumViewHolder>(AlbumUI.diffCallback) {

    inner class AlbumViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: AlbumUI) {
            binding.tvAlbumName.text = album.title
            binding.ivAlbumCover.load(album.image)
            binding.root.setOnClickListener { onClick(album) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
