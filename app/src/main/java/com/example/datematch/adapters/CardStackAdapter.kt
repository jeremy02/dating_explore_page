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
import android.widget.TextView.BufferType
import android.text.Html
import android.os.Build
import android.text.SpannableString
import android.text.Spanned





class CardStackAdapter(
    private var users: List<Users> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_users, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.name.text = "${user.name}"
        holder.city.text = user.city

        val text = "Cinema,<font color=\"#bdbdbd\">Photo,</font>Tattoo,<font color=\"#bdbdbd\">Bicycle,</font>Cooking"
        holder.hobbies.setText(fromHtml(text), BufferType.SPANNABLE)

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
        var hobbies: TextView = view.findViewById(R.id.item_hobbies)
    }

    fun fromHtml(html: String?): Spanned {
        return if (html == null) {
            // return an empty spannable if the html is null
            SpannableString("")
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            // we are using this flag to give a consistent behaviour
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }
}
