package com.hads.digicom.di

import android.content.Context
import androidx.room.Room
import com.hads.digicom.data.db.DigiComDatabase
import com.hads.digicom.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabsaeModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            DigiComDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
}