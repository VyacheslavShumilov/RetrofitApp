package com.hfad.retrofitapp.followers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hfad.retrofitapp.followers.adapter.AdapterFollowers
import com.hfad.retrofitapp.databinding.ActivityFollowersBinding
import com.hfad.retrofitapp.followers.impl.FollowersContract
import com.hfad.retrofitapp.followers.impl.FollowersPresenterImpl
import com.hfad.retrofitapp.model.Followers

//класс получает данные, обработанные в FollowersPresenterImpl, и отображает на экране
class FollowersActivity : AppCompatActivity(), FollowersContract.View {

    //подписка
    private lateinit var presenter: FollowersPresenterImpl
    private lateinit var binding: ActivityFollowersBinding
    private lateinit var login: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras
        login = args?.get("login").toString()

        presenter = FollowersPresenterImpl()        //инициализация
        presenter.attachView(this)             //подписка текущей активности
        presenter.responseData(login)

    }

    //из FollowersPresenter в готовом виде заполняю recyclerView списком Followers'ов
    override fun loadFollowers(followers: ArrayList<Followers>) {
        val adapterFollowers = AdapterFollowers(followers)
        binding.recyclerView.adapter = adapterFollowers
    }


    override fun progressBar(show: Boolean) {
        if (show) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun error() {
        Toast.makeText(this, "Проверьте подключение к интеренту", Toast.LENGTH_SHORT).show()
    }
}