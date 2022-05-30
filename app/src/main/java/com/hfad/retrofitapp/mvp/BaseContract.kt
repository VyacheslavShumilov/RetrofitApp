package com.hfad.retrofitapp.mvp

class BaseContract {
    interface Presenter<in T>{
        fun attachView(view:T)
        fun detachView()
    }

    interface View{

    }
}