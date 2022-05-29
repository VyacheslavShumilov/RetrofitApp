package com.hfad.retrofitapp.model

import com.google.gson.annotations.SerializedName

data class Followers (
    @SerializedName("login")
    var login: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("avatar_url")
    var avatar_url: String
)