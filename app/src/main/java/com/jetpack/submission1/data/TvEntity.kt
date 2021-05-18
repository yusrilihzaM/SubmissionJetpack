package com.jetpack.submission1.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvEntity(
    var titleTv: String,
    var imgTv: String,
    var overviewTv: String
):Parcelable
