package com.jaya.app.store.core.models.responses

import com.jaya.app.store.core.entities.Plant
import com.jaya.app.store.core.entities.StockData

data class PlantListResponse(
    val status: Boolean,
    val message: String,
    val plant: List<Plant>,
)
