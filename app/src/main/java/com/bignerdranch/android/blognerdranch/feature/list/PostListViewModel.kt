package com.bignerdranch.android.blognerdranch.feature.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.bignerdranch.android.blognerdranch.data.blog.BlogService
import com.bignerdranch.android.blognerdranch.data.blog.model.PostMetadata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostListViewModel @Inject constructor(
    private val blogService: BlogService
) : ViewModel() {
    val postMetadataList = MutableLiveData<List<PostMetadata>>()

    init {
        blogService.getPostMetadata().enqueue(object : Callback<List<PostMetadata>?> {
            override fun onFailure(call: Call<List<PostMetadata>?>, t: Throwable) {
                Log.e(TAG, "Failed to load postMetadata", t)
            }

            override fun onResponse(call: Call<List<PostMetadata>?>, response: Response<List<PostMetadata>?>) {
                Log.i(TAG, "Loaded postMetadata $response")
                postMetadataList.value = response.body()
            }
        })
    }

    companion object {
        const val TAG = "PostListViewModel"
    }
}
