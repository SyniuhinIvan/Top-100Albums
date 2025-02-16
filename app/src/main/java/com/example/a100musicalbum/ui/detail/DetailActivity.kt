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
import com.example.a100musicalbum.network.model.Result
import kotlinx.serialization.InternalSerializationApi

// TODO: useless annotation
@OptIn(InternalSerializationApi::class)
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    // TODO: remove this annotation, it crashes the app
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val album = intent.getParcelableExtra("album", Result::class.java)
        if (album != null) {
            binding.tvAlbumName.text = album.name
            binding.tvArtistName.text = album.artistName
            binding.tvReleaseDate.text = album.releaseDate
            //binding.tvCopyright.text = album.copyright

            binding.ivAlbumCoverLarge.load(album.artworkUrl100.replace("100x100", "600x600"))

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
        fun newIntent(context: Context, album: Result): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra("album", album)
            }
        }
    }
}
