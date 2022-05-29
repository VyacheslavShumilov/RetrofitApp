package com.hfad.retrofitapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.hfad.retrofitapp.adapter.AdapterApiUsers
import com.hfad.retrofitapp.databinding.FragmentRetrofitBinding
import com.hfad.retrofitapp.model.Users
import com.hfad.retrofitapp.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log


class RetrofitFragment : Fragment(),AdapterApiUsers.OnClickListener {

    lateinit var binding: FragmentRetrofitBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //работа с retrofit
        val api = Api.create()
        binding.progressBar.visibility = View.VISIBLE
        api.getUsers().enqueue(object : Callback<ArrayList<Users>> {
            override fun onResponse(call: Call<ArrayList<Users>>, response: Response<ArrayList<Users>>) {
                if (response.isSuccessful) {
                    binding.progressBar.visibility = View.INVISIBLE
                    response.body()?.let { result ->
                        val adapterApiUsers = AdapterApiUsers(result,this@RetrofitFragment)
                        binding.recyclerView.adapter = adapterApiUsers
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) {
                binding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireContext(), "Проверьте подключение к интернету", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onClickUser(login: String) {
        val intent = Intent(requireContext(), UserInfoActivity::class.java)
        intent.putExtra("login", login)
        startActivity(intent)
    }
}
