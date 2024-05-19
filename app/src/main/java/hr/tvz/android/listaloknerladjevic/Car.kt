package hr.tvz.android.listaloknerladjevic

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val model: String,
    val manufacturer: String,
    val year: Int,
    val description: String,
    val imageURL: String,
    val websiteURL: String
) : Parcelable
