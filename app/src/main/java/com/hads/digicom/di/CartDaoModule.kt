package com.hads.digicom.di

import com.hads.digicom.data.db.CartDao
import com.hads.digicom.data.db.DigiComDatabase
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