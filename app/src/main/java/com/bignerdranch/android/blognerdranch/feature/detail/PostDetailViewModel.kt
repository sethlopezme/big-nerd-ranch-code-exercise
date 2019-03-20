package com.bignerdranch.android.blognerdranch.feature.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.bignerdranch.android.blognerdranch.data.blog.BlogService
import com.bignerdranch.android.blognerdranch.data.blog.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostDetailViewModel @Inject constructor(
    private val blogService: BlogService
) : ViewModel() {
    val post = MutableLiveData<Post>()

    fun load(id: Int) {
        if (post.value?.id == id) return
        blogService.getPost(id).enqueue(object : Callback<Post?> {
            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Log.e(TAG, "Failed to load post", t)
            }

            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                Log.i(TAG, "Loaded post $response")
                post.value = response.body()
            }
        })
    }

    companion object {
        const val TAG = "PostDetailViewModel"
    }
}
