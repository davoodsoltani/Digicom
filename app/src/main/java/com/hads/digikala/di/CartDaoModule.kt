package com.hads.digikala.di

import com.hads.digikala.data.db.CartDao
import com.hads.digikala.data.db.DigiComDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartDaoModule {

    @Provides
    @Singleton
    fun provideCartDao(database: DigiComDatabase): CartDao = database.cartDao()
}