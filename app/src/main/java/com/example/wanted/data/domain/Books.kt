package com.example.wanted.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Books(
    var kind: String? = null,
    var totalItems: Int? = null,
    var items: BookInfo? = null
): Parcelable
