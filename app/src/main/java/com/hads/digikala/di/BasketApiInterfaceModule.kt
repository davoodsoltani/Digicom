package com.hads.digikala.di

import com.hads.digikala.data.remote.BasketApiInterface
import com.hads.digikala.data.remote.CategoryApiInterface
import com.hads.digikala.data.remote.HomeApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BasketApiInterfaceModule {

    @Provides
    @Singleton
    fun provideCategoryApiInterface(retrofit: Retrofit) : BasketApiInterface =
        retrofit.create(BasketApiInterface::class.java)
}