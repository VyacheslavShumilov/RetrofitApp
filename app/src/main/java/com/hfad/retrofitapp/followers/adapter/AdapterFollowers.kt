package com.hfad.retrofitapp.followers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.retrofitapp.databinding.ItemFollowersBinding
import com.hfad.retrofitapp.model.Followers
import com.squareup.picasso.Picasso

class AdapterFollowers(var followers: ArrayList<Followers>): RecyclerView.Adapter<AdapterFollowers.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindView(followers[position])

    override fun getItemCount(): Int = followers.size

    inner class ViewHolder(var binding: ItemFollowersBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(followers: Followers) {
            Picasso.get().load(followers.avatar_url).into(binding.followerImageView)
            binding.loginTextView.text = followers.login
        }
    }

}