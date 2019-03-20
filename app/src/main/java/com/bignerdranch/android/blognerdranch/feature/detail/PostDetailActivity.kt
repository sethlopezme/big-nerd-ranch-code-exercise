package com.bignerdranch.android.blognerdranch.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.data.blog.BlogService
import com.bignerdranch.android.blognerdranch.data.blog.model.Post
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostDetailActivity : DaggerAppCompatActivity() {
    private var postId: Int = 0

    @Inject
    lateinit var blogService: BlogService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        postId = intent.getIntExtra(EXTRA_POST_ID, 0)

        val postRequest = blogService.getPost(postId)
        postRequest.enqueue(object: Callback<Post?> {
            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Log.e(TAG, "Failed to load post", t)
            }

            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                Log.i(TAG, "Loaded post $response")
                updateUI(response.body()!!)
            }
        })
    }

    private fun updateUI(post: Post) {
        title_textview.text = post.metadata?.title
        author_textView.text = post.metadata?.author?.name
        body_textView.text = post.body
    }

    companion object {
        const val TAG = "PostDetailActivity"
        const val EXTRA_POST_ID = "postID"

        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, PostDetailActivity::class.java)
            intent.putExtra(EXTRA_POST_ID, id)
            return intent
        }
    }
}
