package com.hfad.retrofitapp.followers.impl

import com.hfad.retrofitapp.model.Followers
import com.hfad.retrofitapp.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//класс для обработки данных и дальнейшей передаче в Активити (View) для отображения
class FollowersPresenterImpl: FollowersContract.Presenter {

    private var mvpView: FollowersContract.View? = null
    private var api = Api.create()

//логика вывода данных
    override fun responseData(login: String) {
        mvpView?.let { view ->
            view.progressBar(true)
//запрос из interface Api
            api.getFollowers(login).enqueue(object :Callback<ArrayList<Followers>>{
//переопределение двух функций Callback'а
                override fun onResponse(
                    call: Call<ArrayList<Followers>>,
                    response: Response<ArrayList<Followers>>
                ) {
                    if (response.isSuccessful){
                        view.progressBar(false)
                        response.body()?.let { data->
//через view заполняю список
                            view.loadFollowers(data)
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<Followers>>, t: Throwable) {
                    view.progressBar(false)
                    view.error()
                }
            })
        }
    }

    override fun attachView(view: FollowersContract.View) {
        mvpView = view
    }

    //как только активити onDestroy сработает эта функция - подписка закроется
    override fun detachView() {
       mvpView = null
    }
}