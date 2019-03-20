package com.bignerdranch.android.blognerdranch.feature.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.data.blog.model.Post
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_post.*
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class PostDetailActivity : DaggerAppCompatActivity() {
    lateinit var viewModel: PostDetailViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostDetailViewModel::class.java).apply {
            load(intent.getIntExtra(EXTRA_POST_ID, 0))
            post.observe(this@PostDetailActivity, Observer { updateUI(it!!) })
        }
    }

    private fun updateUI(post: Post) {
        progressBar.visibility = View.GONE
        article_nestedScrollView.visibility = View.VISIBLE
        title_textView.text = post.metadata?.title
        author_textView.text = post.metadata?.author?.name
        date_textView.text = post.metadata?.publishDate?.let { publishDate ->
            // TODO: pull into common utility/extension function
            DateTimeFormatter.ofPattern("MMM dd, u")
                .withZone(ZoneId.systemDefault())
                .format(Instant.parse(publishDate))
        }
        body_textView.text = post.body
    }

    companion object {
        const val EXTRA_POST_ID = "postID"

        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, PostDetailActivity::class.java)
            intent.putExtra(EXTRA_POST_ID, id)
            return intent
        }
    }
}
