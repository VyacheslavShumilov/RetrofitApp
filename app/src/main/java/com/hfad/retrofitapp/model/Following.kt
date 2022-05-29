package com.hfad.retrofitapp.model

import com.google.gson.annotations.SerializedName

data class Following(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("avatar_url")
    val avatar_url: String
)