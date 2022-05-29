package com.hfad.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hfad.retrofitapp.adapter.AdapterFollowing
import com.hfad.retrofitapp.databinding.ActivityFollowingBinding
import com.hfad.retrofitapp.model.Following
import com.hfad.retrofitapp.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFollowingBinding
    private lateinit var login: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras
        login = args?.get("login").toString()

        val api = Api.create()
        binding.progressBar.visibility = View.VISIBLE
        api.getFollowing(login).enqueue(object :Callback<ArrayList<Following>>{
            override fun onResponse(
                call: Call<ArrayList<Following>>,
                response: Response<ArrayList<Following>>
            ) {
                if (response.isSuccessful){
                    binding.progressBar.visibility = View.INVISIBLE
                    response.body()?.let { following ->
                        val adapterFollowing = AdapterFollowing(following)
                        binding.recyclerView.adapter = adapterFollowing
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Following>>, t: Throwable) {
                binding.progressBar.visibility = View.VISIBLE
                Toast.makeText(FollowingActivity(), "Проверьте подключение к интернету", Toast.LENGTH_SHORT).show()
            }
        })
    }



}