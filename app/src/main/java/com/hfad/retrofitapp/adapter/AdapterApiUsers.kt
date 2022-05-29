package com.hfad.retrofitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.retrofitapp.databinding.ItemListApiUsersBinding
import com.hfad.retrofitapp.model.Users
import com.squareup.picasso.Picasso

class AdapterApiUsers(
    var users: ArrayList<Users>,
    var listener:OnClickListener
    ): RecyclerView.Adapter<AdapterApiUsers.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterApiUsers.ViewHolder {
        return ViewHolder(ItemListApiUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AdapterApiUsers.ViewHolder, position: Int) = holder.bindView(users[position])

    override fun getItemCount() = users.size

    inner class ViewHolder(var binding: ItemListApiUsersBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(users: Users) {
            Picasso.get().load(users.avatar_url).into(binding.usersImageView)
            binding.userIdTextView.text = users.id.toString()
            binding.userLoginTextView.text = users.login

            binding.abutUserButton.setOnClickListener {
                listener.onClickUser(users.login)
            }
        }
    }

    interface OnClickListener{
        fun onClickUser(login:String)
    }
}