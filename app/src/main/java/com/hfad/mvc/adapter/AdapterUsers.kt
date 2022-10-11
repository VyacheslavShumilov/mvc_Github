package com.hfad.mvc.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.mvc.databinding.ItemUsersBinding
import com.hfad.mvc.model.Users
import com.hfad.mvc.view.user.UserActivity
import com.squareup.picasso.Picasso

class AdapterUsers(private val users: ArrayList<Users>) : RecyclerView.Adapter<AdapterUsers.ViewHolder>() {

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = users[position]
        holder.binding.userIdTextView.text = users.id.toString()
        holder.binding.userLoginTextView.text = users.login
        Picasso.get().load(users.avatar_url).into(holder.binding.usersImageView)

        holder.binding.aboutUserButton.setOnClickListener {
            val intent = Intent(holder.binding.aboutUserButton.context, UserActivity::class.java)
            intent.putExtra("LOGIN", users.login)
            it.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(var binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root)
}