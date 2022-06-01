package com.hfad.retrofitapp.mvp

class BaseContract {
    interface Presenter<in T>{
        fun attachView(view:T)  //подписка, Т принимает интерфейс View
        fun detachView()        //отписка
    }

    interface View{

    }
}