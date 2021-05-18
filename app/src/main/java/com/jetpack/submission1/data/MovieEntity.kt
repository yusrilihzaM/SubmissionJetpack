package com.jetpack.submission1.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    var titleMovie: String,
    var imgMovie: String,
    var overviewMovie: String
):Parcelable
