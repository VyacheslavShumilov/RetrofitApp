package com.hfad.retrofitapp.followings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hfad.retrofitapp.followings.adapter.AdapterFollowing
import com.hfad.retrofitapp.databinding.ActivityFollowingBinding
import com.hfad.retrofitapp.followings.impl.FollowingContract
import com.hfad.retrofitapp.followings.impl.FollowingPresenterImpl
import com.hfad.retrofitapp.model.Following

class FollowingActivity : AppCompatActivity(), FollowingContract.View {

    private lateinit var presenter: FollowingPresenterImpl
    private lateinit var binding: ActivityFollowingBinding
    private lateinit var login: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val args = intent.extras
        login = args?.get("login").toString()

        presenter = FollowingPresenterImpl()
        presenter.attachView(this)
        presenter.responseData(login)

        binding.toolbar.clickBackBtn.setOnClickListener {
            finish()
        }

    }

    override fun loadFollowing(followings: ArrayList<Following>) {
        val adapterFollowing = AdapterFollowing(followings)
        binding.recyclerView.adapter = adapterFollowing
    }

    override fun progressBar(show: Boolean) {
        if (show) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun error() {
        Toast.makeText(this, "Проверьте подключение к интернету", Toast.LENGTH_SHORT).show()
    }


}