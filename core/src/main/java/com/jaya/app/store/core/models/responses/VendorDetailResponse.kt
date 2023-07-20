package com.jaya.app.store.core.models.responses

import com.jaya.app.store.core.entities.Vendor

data class VendorDetailResponse(

    val status: Boolean,
    val message: String,
    val vendor: List<Vendor>
)
