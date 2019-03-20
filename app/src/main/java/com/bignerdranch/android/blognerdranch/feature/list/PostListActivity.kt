package com.bignerdranch.android.blognerdranch.feature.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bignerdranch.android.blognerdranch.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_post_list.*
import javax.inject.Inject

class PostListActivity : DaggerAppCompatActivity() {
    lateinit var viewModel: PostListViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        post_recyclerview.layoutManager = LinearLayoutManager(this)
        post_recyclerview.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostListViewModel::class.java).apply {
            postMetadataList.observe(this@PostListActivity, Observer {
                progressBar.visibility = View.GONE
                post_recyclerview.visibility = View.VISIBLE
                post_recyclerview.adapter = PostListAdapter(it!!)
            })
        }
    }
}
