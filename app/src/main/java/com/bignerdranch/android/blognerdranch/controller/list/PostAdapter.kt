package com.bignerdranch.android.blognerdranch.controller.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bignerdranch.android.blognerdranch.model.PostMetadata

class PostAdapter(var postMetadata: List<PostMetadata>) : RecyclerView.Adapter<PostViewHolder>() {

    override fun getItemCount(): Int {
        return postMetadata.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postMetadata[position]
        holder.bind(post)
    }

}