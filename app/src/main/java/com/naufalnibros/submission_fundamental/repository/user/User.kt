package com.naufalnibros.submission_fundamental.repository.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    @SerializedName("avatar_url")
    val avatar: String = "",

    @SerializedName("company")
    val company: String = "",

    @SerializedName("login")
    val username: String = "",

    @SerializedName("html_url")
    val htmlUrl: String = "",

    @SerializedName("url")
    val url: String = "",

    @SerializedName("location")
    val alamat: String? = "",

    @SerializedName("name")
    val name: String = ""): Parcelable