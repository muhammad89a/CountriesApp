package com.mdev.countriesapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Countries")
data class CountryDTO(
    @PrimaryKey
    @SerializedName("alpha3Code")
    val alpha3Code: String,
    val name: String?,
    @SerializedName("nativeName")
    val nativeName: String?,
    val area: Double?,
    @SerializedName("flag")
    val flagUrl: String?,
    val borders: List<String>?,
): Parcelable
