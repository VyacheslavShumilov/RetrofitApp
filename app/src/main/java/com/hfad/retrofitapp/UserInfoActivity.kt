package com.hfad.retrofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hfad.retrofitapp.databinding.ActivityUserInfoBinding
import com.hfad.retrofitapp.model.Users
import com.hfad.retrofitapp.services.Api
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoActivity : AppCompatActivity() {

    var login: String = " "

    lateinit var binding: ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras
        login = args?.get("login").toString()

        val api = Api.create()
        api.getUser(login).enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    response.body()?.let { users ->
                        binding.idTextView.text = users.id.toString()
                        binding.loginTextView.text = users.login
                        binding.urlTextView.text = users.url
                        Picasso.get().load(users.avatar_url).into(binding.imageView)
                    }
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Toast.makeText(this@UserInfoActivity, "Нет соединения с интернетом", Toast.LENGTH_SHORT).show()
            }
        })

        binding.followersBtn.setOnClickListener {
            val intent = Intent(this,FollowersActivity::class.java)
            intent.putExtra("login", login)
            startActivity(intent)
        }

        binding.followingBtn.setOnClickListener {
            val intent = Intent(this, FollowingActivity::class.java)
            intent.putExtra("login", login)
            startActivity(intent)
        }
    }
}