package com.hads.digicom.di

import com.hads.digicom.data.remote.CheckoutApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CheckoutApiInterfaceModule {

    @Singleton
    @Provides
    fun provideCheckoutApiService(retrofit: Retrofit) : CheckoutApiInterface =
        retrofit.create(CheckoutApiInterface::class.java)

}
