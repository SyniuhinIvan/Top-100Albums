package com.example.a100musicalbum.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.a100musicalbum.databinding.ItemAlbumBinding
import com.example.a100musicalbum.data.model.Album

//   https://medium.com/geekculture/android-listadapter-a-better-implementation-for-the-recyclerview-1af1826a7d21
//   https://www.thedroidsonroids.com/blog/difference-between-listview-recyclerview
class MainAdapter(private val onClick: (Album) -> Unit) :
    ListAdapter<Album, MainAdapter.AlbumViewHolder>(AlbumDiffCallback()) {

    inner class AlbumViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.tvAlbumName.text = album.name
            binding.ivAlbumCover.load(album.artworkUrl100)
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
    class AlbumDiffCallback : DiffUtil.ItemCallback<Album>(){
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }
}
