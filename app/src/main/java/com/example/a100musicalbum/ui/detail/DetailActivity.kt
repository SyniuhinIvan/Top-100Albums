package com.example.a100musicalbum.ui.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.a100musicalbum.databinding.ActivityDetailBinding
import com.example.a100musicalbum.data.network.dto.AlbumDto
import com.example.a100musicalbum.ui.component.AlbumUI

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val album: AlbumDto? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("album", AlbumDto::class.java)
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
        fun newIntent(context: Context, album: AlbumUI): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra("album", album)
            }
        }
    }
}
