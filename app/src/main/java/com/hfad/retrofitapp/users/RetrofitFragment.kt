package com.hfad.retrofitapp.users

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hfad.retrofitapp.SecondActivity
import com.hfad.retrofitapp.databinding.FragmentRetrofitBinding
import com.hfad.retrofitapp.model.Users
import com.hfad.retrofitapp.user.UserInfoActivity
import com.hfad.retrofitapp.users.adapter.AdapterApiUsers
import com.hfad.retrofitapp.users.impl.UsersContract
import com.hfad.retrofitapp.users.impl.UsersPresentersImpl


class RetrofitFragment : Fragment(), AdapterApiUsers.OnClickListener, UsersContract.View {

    private lateinit var binding: FragmentRetrofitBinding
    private lateinit var presenter: UsersPresentersImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = UsersPresentersImpl()
        presenter.attachView(this)
        presenter.responseData()

        binding.toolbar.clickBackBtn.setOnClickListener {
            (requireActivity() as SecondActivity).clickBack()
        }
    }

    override fun onClickUser(login: String) {
        val intent = Intent(requireContext(), UserInfoActivity::class.java)
        intent.putExtra("login", login)
        startActivity(intent)
    }

    override fun loadUsers(users: ArrayList<Users>) {
        val adapterUsers = AdapterApiUsers(users,this)
        binding.recyclerView.adapter = adapterUsers
    }

    override fun progressBar(show: Boolean) {
        if (show) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun error() {
        Toast.makeText(requireContext(), "Проверьте подключение к интернету", Toast.LENGTH_SHORT).show()
    }
}
