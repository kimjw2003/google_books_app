package com.example.google_books_app.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookInfo(
    var kinds: String? = null,
    var id: String? = null,
    var etag: String? = null,
    var selfLink: String? = null,
    var volumeInfo: VolumeInfo? = null,
): Parcelable
