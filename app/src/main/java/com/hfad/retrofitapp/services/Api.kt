package com.hfad.retrofitapp.services

import com.hfad.retrofitapp.model.Followers
import com.hfad.retrofitapp.model.Following
import com.hfad.retrofitapp.model.Users
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("users")
    fun getUsers(): Call<ArrayList<Users>>

    @GET("users/{login}")
    fun getUser(
        @Path("login") login:String
    ): Call<Users>

    @GET("users/{login}/followers")
    fun getFollowers(
        @Path("login") login:String
    ): Call<ArrayList<Followers>>

    @GET("users/{login}/following")
    fun getFollowing(
        @Path("login") login:String
    ): Call<ArrayList<Following>>

    companion object {
        var BASE_URL = "https://api.github.com/"
        fun create(): Api {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}