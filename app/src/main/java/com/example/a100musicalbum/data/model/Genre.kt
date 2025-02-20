package com.example.a100musicalbum.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Genre(
    val name: String
) : Parcelable
