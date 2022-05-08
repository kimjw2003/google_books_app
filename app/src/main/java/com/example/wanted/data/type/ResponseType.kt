package com.example.wanted.data.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ResponseType : Parcelable {
    SUCCESS,
    FAIL
}