package com.hads.digicom.data.model.checkout

data class ConfirmPurchase(
    val orderId: String,
    val token: String,
    val transactionId: String
)