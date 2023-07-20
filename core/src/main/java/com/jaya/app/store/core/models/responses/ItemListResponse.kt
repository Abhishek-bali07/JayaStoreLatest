package com.jaya.app.store.core.models.responses

import com.jaya.app.store.core.entities.StockData

data class ItemListResponse(

    val status: Boolean,
    val message: String,
    val stockData: List<StockData>,
)
