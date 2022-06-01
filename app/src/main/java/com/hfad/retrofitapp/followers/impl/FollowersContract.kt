package com.hfad.retrofitapp.followers.impl

import com.hfad.retrofitapp.model.Followers
import com.hfad.retrofitapp.mvp.BaseContract

interface FollowersContract {

    interface View:BaseContract.View{
        fun loadFollowers(followers: ArrayList<Followers>)
        fun progressBar(show:Boolean)
        fun error()
    }

    interface Presenter:BaseContract.Presenter<View>{
        fun responseData(login:String)
    }
}