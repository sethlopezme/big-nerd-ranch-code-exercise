package com.bignerdranch.android.blognerdranch.data.blog

import com.bignerdranch.android.blognerdranch.data.blog.model.Post
import com.bignerdranch.android.blognerdranch.data.blog.model.PostMetadata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogService {
    @GET("post-metadata")
    fun getPostMetadata(): Call<List<PostMetadata>>

    @GET("post/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>
}
