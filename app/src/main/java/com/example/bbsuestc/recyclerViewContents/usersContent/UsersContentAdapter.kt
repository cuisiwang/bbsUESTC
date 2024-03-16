package com.example.bbsuestc.recyclerViewContents.usersContent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R

class UsersContentAdapter(private val data : ArrayList<UsersItem>) : RecyclerView.Adapter<UsersContentAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //寻找view
        var userIcon : ImageView
        var userID : TextView
        var userDescription : TextView

        init {
            userIcon = itemView.findViewById(R.id.users_icon_iv)
            userID = itemView.findViewById(R.id.users_id_tv)
            userDescription = itemView.findViewById(R.id.users_description_tv)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersContentAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_users,parent,false)
        )
    }

    override fun onBindViewHolder(holder: UsersContentAdapter.ViewHolder, position: Int) {
        val currentItem = data[position]
//        holder.userIcon.setImageURI(currentItem.posterIcon)  设置icon的url
        holder.userID.text = currentItem.id
        holder.userDescription.text = currentItem.description
    }

    override fun getItemCount() = data.size


}