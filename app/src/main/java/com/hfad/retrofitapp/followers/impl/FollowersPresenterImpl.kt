package com.hfad.retrofitapp.followers.impl

import com.hfad.retrofitapp.model.Followers
import com.hfad.retrofitapp.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersPresenterImpl: FollowersContract.Presenter {

    private var mvpView: FollowersContract.View? = null
    private var api = Api.create()

    override fun responseData(login: String) {
        mvpView?.let { view ->
            view.progressBar(true)
            api.getFollowers(login).enqueue(object :Callback<ArrayList<Followers>>{
                override fun onResponse(
                    call: Call<ArrayList<Followers>>,
                    response: Response<ArrayList<Followers>>
                ) {
                    if (response.isSuccessful){
                        view.progressBar(false)
                        response.body()?.let { data->
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

    override fun detachView() {
       mvpView = null
    }
}