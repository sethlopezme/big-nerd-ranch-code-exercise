package com.bignerdranch.android.blognerdranch

import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Scope
import javax.inject.Singleton

/**
 * The component is the root of our dependency graph
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<BlogNerdRanch> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BlogNerdRanch>()
}

@Scope
annotation class PerActivity
