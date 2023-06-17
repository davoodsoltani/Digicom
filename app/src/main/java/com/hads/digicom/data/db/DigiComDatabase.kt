package com.hads.digicom.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hads.digicom.data.model.basket.CartItem

@Database(entities = [CartItem::class], version = 1, exportSchema = true)
abstract class DigiComDatabase: RoomDatabase() {
    abstract fun cartDao(): CartDao
}