package com.example.wanted.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageLinks(
    var smallThumbnail: String? = null,
    var thumbnail: String? = null
): Parcelable
