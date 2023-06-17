package com.hads.digicom.di

import com.hads.digicom.data.remote.BasketApiInterface
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