package com.example.google_books_app.data.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ResponseType : Parcelable {
    SUCCESS,
    FAIL
}