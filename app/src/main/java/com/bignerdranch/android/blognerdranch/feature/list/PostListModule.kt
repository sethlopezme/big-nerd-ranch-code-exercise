package com.bignerdranch.android.blognerdranch.feature.list

import android.arch.lifecycle.ViewModel
import com.bignerdranch.android.blognerdranch.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PostListModule {
    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindPostListViewModel(viewModel: PostListViewModel): ViewModel
}
