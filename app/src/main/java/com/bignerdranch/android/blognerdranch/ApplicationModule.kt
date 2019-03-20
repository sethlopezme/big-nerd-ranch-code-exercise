package com.bignerdranch.android.blognerdranch

import android.arch.lifecycle.ViewModelProvider
import com.bignerdranch.android.blognerdranch.data.DataModule
import com.bignerdranch.android.blognerdranch.feature.detail.PostDetailActivity
import com.bignerdranch.android.blognerdranch.feature.detail.PostDetailModule
import com.bignerdranch.android.blognerdranch.feature.list.PostListActivity
import com.bignerdranch.android.blognerdranch.feature.list.PostListModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class, DataModule::class])
abstract class ApplicationModule {
    @Binds
    abstract fun bindViewModelInjectionFactory(factory: ViewModelInjectionFactory): ViewModelProvider.Factory

    @PerActivity
    @ContributesAndroidInjector(modules = [PostListModule::class])
    abstract fun contributePostListActivityInjector(): PostListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [PostDetailModule::class])
    abstract fun contributePostDetailActivityInjector(): PostDetailActivity
}
