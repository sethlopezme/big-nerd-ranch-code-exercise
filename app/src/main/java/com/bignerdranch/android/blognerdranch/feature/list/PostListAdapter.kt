package com.bignerdranch.android.blognerdranch.feature.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bignerdranch.android.blognerdranch.feature.detail.PostDetailActivity
import com.bignerdranch.android.blognerdranch.model.PostMetadata

class PostListAdapter(var postMetadata: List<PostMetadata>) : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {
    override fun getItemCount(): Int = postMetadata.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postMetadata[position]
        holder.bind(post)
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var postMetadata: PostMetadata? = null
        val titleTextView: TextView = itemView.findViewById(android.R.id.text1)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(postMetadata: PostMetadata) {
            this.postMetadata = postMetadata
            titleTextView.text = postMetadata.title
        }

        override fun onClick(v: View) {
            v.context.startActivity(PostDetailActivity.newIntent(v.context, postMetadata!!.postId!!))
        }
    }
}
