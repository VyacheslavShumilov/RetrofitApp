package com.hfad.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.hfad.retrofitapp.databinding.ActivitySecondBinding
import com.hfad.retrofitapp.users.RetrofitFragment

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(RetrofitFragment(), supportFragmentManager)

    }

    private fun loadFragment(fragment: Fragment, fragmentManager: FragmentManager) {
        val ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }
}