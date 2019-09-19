package com.example.datematch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.datematch.R
import com.example.datematch.models.Users

class CardStackAdapter(
    private var users: List<Users> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_users, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.name.text = "${user.id}. ${user.name}"
        holder.city.text = user.city

        // image
        Glide.with(holder.image)
            .load(user.url)
            .into(holder.image)

        holder.itemView.setOnClickListener { v ->
            Toast.makeText(v.context, user.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setUsers(users: List<Users>) {
        this.users = users
    }

    fun getUsers(): List<Users> {
        return users
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.item_name)
        var city: TextView = view.findViewById(R.id.item_city)
        var image: ImageView = view.findViewById(R.id.item_image)
    }
}
