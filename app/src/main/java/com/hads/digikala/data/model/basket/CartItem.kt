package com.hads.digikala.data.model.basket

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hads.digikala.utils.Constants

@Entity(tableName = Constants.SHOPPING_CART_TABLE)
data class CartItem(
    @PrimaryKey
    val itemId: String,
    val name: String,
    val seller: String,
    val price: Long,
    val discountPercent: Int,
    val image: String,
    val count: Int,
    val cartStatus: CartStatus

)
