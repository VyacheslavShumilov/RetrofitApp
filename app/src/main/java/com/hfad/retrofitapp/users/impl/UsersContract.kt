package com.hfad.retrofitapp.users.impl

import com.hfad.retrofitapp.model.Users
import com.hfad.retrofitapp.mvp.BaseContract

interface UsersContract {

    interface View: BaseContract.View {
        fun loadUsers(users: ArrayList<Users>)
        fun progressBar(show: Boolean)
        fun error()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun responseData()
    }
}