package com.jetpack.submission1.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "TvEntity")
@Parcelize
data class TvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: Int,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "posterPath")
    val posterPath: String,

    @ColumnInfo(name = "originalName")
    val originalName: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
):Parcelable
