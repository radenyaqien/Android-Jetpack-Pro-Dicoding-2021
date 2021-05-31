package id.radenyaqien.jetpackdicoding.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShows(
    val id: Int,
    val name: String,
    val Year: String,
    val genre: String,
    val overview: String,
    val image: Int
) : Parcelable
