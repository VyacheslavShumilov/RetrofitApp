package com.hfad.retrofitapp.followings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.retrofitapp.databinding.ItemFollowingsBinding
import com.hfad.retrofitapp.model.Following
import com.squareup.picasso.Picasso

class AdapterFollowing(var followings: ArrayList<Following>):RecyclerView.Adapter<AdapterFollowing.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFollowingsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindView(followings[position])

    override fun getItemCount(): Int = followings.size

    inner class ViewHolder(var binding: ItemFollowingsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(following: Following) {
            Picasso.get().load(following.avatar_url).into(binding.followingImageView)
            binding.loginTextView.text = following.login
        }
    }
}