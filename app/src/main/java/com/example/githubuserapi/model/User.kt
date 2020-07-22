package com.example.githubuserapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("login")
    var login: String? = null,

    @SerializedName("avatar_url")
    var avatar: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("company")
    var company: String? = null,

    @SerializedName("location")
    var location: String? = null,

    @SerializedName("followers")
    var followers: Int = 0,

    @SerializedName("following")
    var following: Int = 0
) : Parcelable