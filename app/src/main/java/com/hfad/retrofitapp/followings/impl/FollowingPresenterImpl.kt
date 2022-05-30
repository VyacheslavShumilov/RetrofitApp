package com.hfad.retrofitapp.followings.impl

import com.hfad.retrofitapp.model.Following
import com.hfad.retrofitapp.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingPresenterImpl: FollowingContract.Presenter {

    private var mvpView: FollowingContract.View? = null
    private var api = Api.create()

    override fun responseData(login: String) {
        mvpView?.let { view ->
            view.progressBar(true)
            api.getFollowing(login).enqueue(object : Callback<ArrayList<Following>> {
                override fun onResponse(
                    call: Call<ArrayList<Following>>,
                    response: Response<ArrayList<Following>>
                ) {
                    if (response.isSuccessful){
                        view.progressBar(false)
                        response.body()?.let { data ->
                            view.loadFollowing(data)
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<Following>>, t: Throwable) {
                    view.progressBar(false)
                    view.error()
                }


            })
        }
    }

    override fun attachView(view: FollowingContract.View) {
        mvpView = view
    }

    override fun detachView() {
        mvpView = null
    }
}