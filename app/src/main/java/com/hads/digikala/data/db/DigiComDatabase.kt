package com.hads.digikala.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hads.digikala.data.model.basket.CartItem

@Database(entities = [CartItem::class], version = 1, exportSchema = true)
abstract class DigiComDatabase: RoomDatabase() {
    abstract fun cartDao(): CartDao
}