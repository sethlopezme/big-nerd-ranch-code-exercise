package com.bignerdranch.android.blognerdranch.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.bignerdranch.android.blognerdranch.BlogService
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostDetailActivity : AppCompatActivity() {

    private var postId: Int = 0

    private var postTitle: TextView? = null
    private var postAuthor: TextView? = null
    private var postBody: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        postTitle = findViewById(R.id.title_textview)
        postAuthor = findViewById(R.id.author_textView)
        postBody = findViewById(R.id.body_textView)

        postId = intent.getIntExtra(EXTRA_POST_ID, 0)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8106/") // "localhost" is the emulator's host. 10.0.2.2 goes to your computer
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val blogService = retrofit.create(BlogService::class.java)

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
        postTitle?.text = post.metadata?.title
        postAuthor?.text = post.metadata?.author?.name
        postBody?.text = post.body
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
