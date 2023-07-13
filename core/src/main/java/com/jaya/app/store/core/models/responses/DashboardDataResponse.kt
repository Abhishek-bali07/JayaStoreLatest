package com.jaya.app.store.core.models.responses



import com.jaya.app.store.core.entities.Product

data class DashboardDataResponse(
    val status: Boolean,
    val message: String,
    val totalItem:String,
    val issuedItem:String,
    val products: List<Product>
)
