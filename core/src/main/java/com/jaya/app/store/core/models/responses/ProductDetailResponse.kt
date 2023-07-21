package com.jaya.app.store.core.models.responses

import com.jaya.app.store.core.entities.ItemProduct

data class ProductDetailResponse(
    val status: Boolean,
    val message: String,
    val product: List<ItemProduct>,
)
