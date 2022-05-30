package com.hfad.retrofitapp.user.impl

import com.hfad.retrofitapp.model.Users
import com.hfad.retrofitapp.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPresenterImpl: UserContract.Presenter {

    private var mvpView: UserContract.View? = null
    private var api = Api.create()

    override fun responseData(login: String) {
        mvpView?.let { view ->
            view.progressBar(true)
            api.getUser(login).enqueue(object :Callback<Users>{
                override fun onResponse(
                    call: Call<Users>,
                    response: Response<Users>) {
                    if (response.isSuccessful) {
                        view.progressBar(false)
                        response.body()?.let { data->
                            view.loadUsers(data)
                        }
                    }
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    view.progressBar(false)
                    view.error()
                }

            })
        }
    }

    override fun attachView(view: UserContract.View) {
        mvpView = view
    }

    override fun detachView() {
        mvpView = null
    }
}