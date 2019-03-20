package com.bignerdranch.android.blognerdranch.feature.list

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bignerdranch.android.blognerdranch.R
import com.bignerdranch.android.blognerdranch.data.blog.BlogService
import com.bignerdranch.android.blognerdranch.data.blog.model.PostMetadata
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_post_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostListActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var blogService: BlogService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        post_recyclerview.layoutManager = LinearLayoutManager(this)
        post_recyclerview.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        val postMetadataRequest = blogService.getPostMetadata()
        postMetadataRequest.enqueue(object: Callback<List<PostMetadata>?> {
            override fun onFailure(call: Call<List<PostMetadata>?>, t: Throwable) {
                Log.e(TAG, "Failed to load postMetadata", t)
            }

            override fun onResponse(call: Call<List<PostMetadata>?>, response: Response<List<PostMetadata>?>) {
                Log.i(TAG, "Loaded postMetadata $response")
                post_recyclerview.adapter = PostListAdapter(response.body()!!)
            }
        })
    }

    companion object {
        const val TAG = "PostListActivity"
    }
}
