package com.bignerdranch.android.blognerdranch.feature.detail

import android.arch.lifecycle.ViewModel
import com.bignerdranch.android.blognerdranch.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PostDetailModule {
    @Binds
    @IntoMap
    @ViewModelKey(PostDetailViewModel::class)
    abstract fun bindPostDetailViewModel(viewModel: PostDetailViewModel): ViewModel
}
