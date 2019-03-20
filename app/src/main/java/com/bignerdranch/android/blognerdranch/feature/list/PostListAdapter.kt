package com.bignerdranch.android.blognerdranch.feature.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.data.blog.model.PostMetadata
import com.bignerdranch.android.blognerdranch.feature.detail.PostDetailActivity
import kotlinx.android.synthetic.main.activity_post_list_row.view.*
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class PostListAdapter(var postMetadata: List<PostMetadata>) : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {
    override fun getItemCount(): Int = postMetadata.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_post_list_row, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postMetadata[position]
        holder.bind(post)
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var postMetadata: PostMetadata? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(postMetadata: PostMetadata) {
            this.postMetadata = postMetadata
            itemView.title_textView.text = postMetadata.title
            itemView.author_textView.text = postMetadata.author?.name
            itemView.date_textView.text = postMetadata.publishDate?.let { publishDate ->
                // TODO: pull into common utility/extension function
                DateTimeFormatter.ofPattern("MMM dd, u")
                    .withZone(ZoneId.systemDefault())
                    .format(Instant.parse(publishDate))
            }
            itemView.summary_textView.text = postMetadata.summary
        }

        override fun onClick(v: View) {
            postMetadata?.postId?.also { id -> v.context.startActivity(PostDetailActivity.newIntent(v.context, id)) }
        }
    }
}
