package com.bignerdranch.android.blognerdranch.controller.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bignerdranch.android.blognerdranch.model.PostMetadata
import com.bignerdranch.android.blognerdranch.controller.post.PostActivity

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
        val context = v.context
        context.startActivity(PostActivity.newIntent(v.context, postMetadata!!.postId!!))
    }

}