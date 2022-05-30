package com.hfad.retrofitapp.followings.impl

import com.hfad.retrofitapp.model.Following
import com.hfad.retrofitapp.mvp.BaseContract

interface FollowingContract {

    interface View: BaseContract.View{
        fun loadFollowing(followings: ArrayList<Following>)
        fun progressBar(show: Boolean)
        fun error()
    }

    interface Presenter: BaseContract.Presenter<View>{
        fun responseData(login: String)
    }
}