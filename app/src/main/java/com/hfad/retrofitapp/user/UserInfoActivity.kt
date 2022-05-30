package com.hfad.retrofitapp.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hfad.retrofitapp.databinding.ActivityUserInfoBinding
import com.hfad.retrofitapp.followers.FollowersActivity
import com.hfad.retrofitapp.followings.FollowingActivity
import com.hfad.retrofitapp.model.Users
import com.hfad.retrofitapp.services.Api
import com.hfad.retrofitapp.user.impl.UserContract
import com.hfad.retrofitapp.user.impl.UserPresenterImpl
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoActivity : AppCompatActivity(), UserContract.View {

    private lateinit var presenter: UserPresenterImpl
    var login: String = " "
    lateinit var binding: ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras
        login = args?.get("login").toString()

        presenter = UserPresenterImpl()
        presenter.attachView(this)
        presenter.responseData(login)

        binding.followersBtn.setOnClickListener {
            val intent = Intent(this, FollowersActivity::class.java)
            intent.putExtra("login", login)
            startActivity(intent)
        }

        binding.followingBtn.setOnClickListener {
            val intent = Intent(this, FollowingActivity::class.java)
            intent.putExtra("login", login)
            startActivity(intent)
        }
    }

    override fun loadUsers(users: Users) {
        Picasso.get().load(users.avatar_url).into(binding.imageView)
        binding.loginTextView.text = users.login
        binding.idTextView.text = users.id.toString()
        binding.urlTextView.text = users.url
    }

    override fun progressBar(show: Boolean) {
        if (show) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun error() {
        Toast.makeText(this, "Ошибка интренет соединения", Toast.LENGTH_SHORT).show()
    }
}