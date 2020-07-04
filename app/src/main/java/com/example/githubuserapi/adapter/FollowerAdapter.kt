package com.example.githubuserapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapi.R
import com.example.githubuserapi.UserItems
import kotlinx.android.synthetic.main.user_items.view.*

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {

    private var mData = ArrayList<UserItems>()

    fun setData(items: ArrayList<UserItems>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FollowerViewHolder {
        val mView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.user_items, viewGroup, false)
        return FollowerViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class FollowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userItems: UserItems) {
            with(itemView) {
                userItems.avatar.apply {
                    Glide.with(itemView).load(userItems.avatar).into(avatar)
                }
                tv_username.text = userItems.username
            }
        }
    }
}