package com.jaya.app.store.core.models.responses

import com.jaya.app.store.core.entities.Section

data class SectionListResponse(

    val status: Boolean,
    val message: String,
    val section: List<Section>,
)
