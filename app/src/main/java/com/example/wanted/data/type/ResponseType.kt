/*
 * Create by Im-Tae.
 *
 * Copyright (c) 2021. Im-Tae. All rights reserved.
 */

package com.example.wanted.data.type

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ResponseType : Parcelable {
    SUCCESS,
    FAIL
}