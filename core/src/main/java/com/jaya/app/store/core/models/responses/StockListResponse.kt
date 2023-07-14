package com.jaya.app.store.core.models.responses

import com.jaya.app.store.core.entities.StockData
import com.jaya.app.store.core.entities.Vendor

data class StockListResponse(
    val status: Boolean,
    val message: String,
    val stockData: List<StockData>
)
