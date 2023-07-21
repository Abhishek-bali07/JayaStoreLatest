package com.jaya.app.store.core.models.responses

import com.jaya.app.store.core.entities.Gst

data class GstListResponse(

    val status: Boolean,
    val message: String,
    val gst: List<Gst>,
)
