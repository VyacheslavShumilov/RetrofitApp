package com.hfad.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hfad.retrofitapp.adapter.AdapterFollowers
import com.hfad.retrofitapp.databinding.ActivityFollowersBinding
import com.hfad.retrofitapp.databinding.ItemFollowersBinding
import com.hfad.retrofitapp.model.Followers
import com.hfad.retrofitapp.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFollowersBinding
    private lateinit var login:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowersBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val args = intent.extras
        login = args?.get("login").toString()

        val api = Api.create()
        binding.progressBar.visibility = View.VISIBLE
        api.getFollowers(login).enqueue(object :Callback<ArrayList<Followers>>{
            override fun onResponse(
                call: Call<ArrayList<Followers>>,
                response: Response<ArrayList<Followers>>
            ) {
                if (response.isSuccessful){
                    binding.progressBar.visibility = View.INVISIBLE
                    response.body()?.let { followers->
                        val adapterFollowers = AdapterFollowers(followers)
                        binding.recyclerView.adapter = adapterFollowers
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Followers>>, t: Throwable) {
                binding.progressBar.visibility = View.VISIBLE
                Toast.makeText(FollowersActivity(), "Проверьте подключение к интернету", Toast.LENGTH_SHORT).show()
            }
        })
    }
}