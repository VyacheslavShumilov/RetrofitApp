package com.hfad.retrofitapp.users.impl

import com.hfad.retrofitapp.model.Users
import com.hfad.retrofitapp.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersPresentersImpl: UsersContract.Presenter {

    private var mvpView: UsersContract.View? = null
    private var api = Api.create()

    override fun responseData() {
        mvpView?.let { view ->
            view.progressBar(true)
            api.getUsers().enqueue(object : Callback<ArrayList<Users>>{
                override fun onResponse(
                    call: Call<ArrayList<Users>>,
                    response: Response<ArrayList<Users>>
                ) {
                    if (response.isSuccessful) {
                        view.progressBar(false)
                        response.body()?.let { data ->
                            view.loadUsers(data)
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) {
                    view.progressBar(false)
                    view.error()
                }
            })
        }
    }


    override fun attachView(view: UsersContract.View) {
        mvpView = view
    }

    override fun detachView() {
        mvpView = null
    }
}