package com.example.a100musicalbum.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.a100musicalbum.databinding.ItemAlbumBinding
import com.example.a100musicalbum.network.model.Result
import kotlinx.serialization.InternalSerializationApi

// TODO: useless annotation
@OptIn(InternalSerializationApi::class)
// TODO: use ListAdapter instead of RecyclerView.Adapter
//   https://medium.com/geekculture/android-listadapter-a-better-implementation-for-the-recyclerview-1af1826a7d21
//   https://www.thedroidsonroids.com/blog/difference-between-listview-recyclerview
class AlbumAdapter(private val onClick: (Result) -> Unit) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private val albums = mutableListOf<Result>()

    fun submitList(newList: List<Result>) {
        albums.clear()
        albums.addAll(newList)
        notifyDataSetChanged()
    }

    inner class AlbumViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Result) {
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
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int = albums.size
}
