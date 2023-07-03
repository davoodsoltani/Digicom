package com.hads.digicom.data.model.product_detail

data class NewComment(
    val token: String,
    val productId: String,
    val star: Int,
    val title: String,
    val description: String,
    val userName: String
)
