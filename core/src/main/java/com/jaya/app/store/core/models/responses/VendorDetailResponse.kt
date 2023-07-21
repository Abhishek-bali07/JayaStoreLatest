package com.jaya.app.store.core.models.responses

import com.jaya.app.store.core.entities.Supplier

data class SupplierDetailResponse(

    val status: Boolean,
    val message: String,
    val supplier: List<Supplier>
)
