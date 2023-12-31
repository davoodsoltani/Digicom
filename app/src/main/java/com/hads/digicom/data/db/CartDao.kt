package com.hads.digicom.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hads.digicom.data.model.basket.CartItem
import com.hads.digicom.data.model.basket.CartStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem(cart: CartItem)

    @Query("select * from shopping_cart where cartStatus=:status")
    fun getAllItems(status: CartStatus): Flow<List<CartItem>>

    @Delete
    suspend fun removeFromCart(item: CartItem)

    @Query("update shopping_cart set count=:newCount where itemId=:id")
    suspend fun changeCountCartItem(id: String, newCount: Int)


    @Query("update shopping_cart set cartStatus=:newCartStatus where itemId=:id")
    suspend fun changeStatusCart(id: String, newCartStatus: CartStatus)


    @Query("select total(count) as count from shopping_cart where cartStatus=:status")
    fun getCartItemsCount(status: CartStatus): Flow<Int>

    @Query("DELETE FROM shopping_cart where cartStatus=:status")
    fun deleteAllItems(status: CartStatus)
}