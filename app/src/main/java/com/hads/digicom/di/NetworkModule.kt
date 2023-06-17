package com.hads.digicom.di

import com.hads.digicom.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun interceptor(): HttpLoggingInterceptor{
        var logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(Constants.TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        .readTimeout(Constants.TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        .writeTimeout(Constants.TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        .addInterceptor {chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key",Constants.API_KEY)
                .addHeader("lang",Constants.USER_LANGUAGE)
            chain.proceed(request.build())
        }
        .addInterceptor(interceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

}