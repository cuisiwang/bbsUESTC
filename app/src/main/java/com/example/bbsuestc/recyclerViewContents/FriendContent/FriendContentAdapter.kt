package com.example.bbsuestc.recyclerViewContents.FriendContent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R

class FriendContentAdapter(private val data:ArrayList<FriendItem>):
    RecyclerView.Adapter<FriendContentAdapter.SearchHolder>() {
    inner class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userIcon:ImageView
        var userName:TextView
        init{
            this.userIcon=itemView.findViewById(R.id.friend_userIcon_iv)
            this.userName=itemView.findViewById(R.id.friend_userName_tv)
        }
        public fun setData(position:Int){
            userName.setText(data[position].userName)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendContentAdapter.SearchHolder {
        val view:View=View.inflate(parent.context,R.layout.item_friend_content,null)
        return SearchHolder(view)
    }

    override fun onBindViewHolder(holder: FriendContentAdapter.SearchHolder, position: Int) {
        holder.setData(position)

    }

    override fun getItemCount(): Int {
        return data.size

    }
}