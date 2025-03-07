package com.example.a100musicalbum.ui.component

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.example.a100musicalbum.data.network.dto.AlbumDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumUI(
    val title: String,
    val subtitle: String,
    val image: String,
    val id: String
) : Parcelable {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<AlbumUI>() {
            override fun areItemsTheSame(oldItem: AlbumUI, newItem: AlbumUI): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AlbumUI, newItem: AlbumUI): Boolean {
                return oldItem == newItem
            }
        }
    }
}

fun AlbumDto.toAlbumUI() = AlbumUI(
    title = this.name,
    subtitle = this.artistName,
    image = this.artworkUrl100,
    id = this.id
)