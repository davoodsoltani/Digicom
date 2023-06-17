package com.hads.digikala.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hads.digikala.data.db.DigiComDatabase
import com.hads.digikala.utils.Constants
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