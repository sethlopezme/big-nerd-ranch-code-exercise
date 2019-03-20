package com.bignerdranch.android.blognerdranch

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BlogNerdRanch : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder().create(this)
}
