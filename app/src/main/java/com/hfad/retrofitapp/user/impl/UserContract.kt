package com.hfad.retrofitapp.user.impl

import com.hfad.retrofitapp.model.Users
import com.hfad.retrofitapp.mvp.BaseContract

interface UserContract {

    interface View: BaseContract.View{
        fun loadUsers(users: Users)
        fun progressBar(show: Boolean)
        fun error()
    }

    interface Presenter: BaseContract.Presenter<View>{
        fun responseData(login: String)
    }
}