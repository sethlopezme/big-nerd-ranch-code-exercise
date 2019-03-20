package com.bignerdranch.android.blognerdranch.data

import com.bignerdranch.android.blognerdranch.BuildConfig
import com.bignerdranch.android.blognerdranch.data.blog.BlogService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BNR_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun provideBlogService(retrofit: Retrofit): BlogService = retrofit.create(BlogService::class.java)
}
