package com.example.a100musicalbum.ui.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.a100musicalbum.databinding.ActivityDetailBinding
import com.example.a100musicalbum.network.model.Album
import kotlinx.serialization.InternalSerializationApi

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val album: Album? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("album", Album::class.java)
        } else {
            intent.getParcelableExtra("album")
        }
        if (album != null) {
            binding.tvAlbumName.text = album.name
            binding.tvArtistName.text = album.artistName
            binding.tvReleaseDate.text = album.releaseDate
            binding.tvCopyright.text = "© Copyright"

            binding.ivAlbumCoverLarge.load(album.artworkUrl100.replace("100x100", "1080x1920"))

            binding.btnOpenInStore.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(album.url))
                startActivity(intent)
            }
            binding.btnBack.setOnClickListener {
                finish()
            }

        }
    }

    companion object {
        fun newIntent(context: Context, album: Album): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra("album", album)
            }
        }
    }
}
