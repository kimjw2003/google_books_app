package com.example.wanted.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VolumeInfo(
    var title: String? = null,
    var subTitle: String? = null,
    var publisher: String? = null,
    var publishedDate: String? = null,
    var description: String? = null,
    var printType: String? = null,
    var pageCount: Int? = null,
    var imageLinks: ImageLinks? = null,
    var previewLink: String? = null,
): Parcelable
