package com.jaya.app.store.core.models.responses

import com.jaya.app.store.core.entities.SectionData

data class SubSectionListResponse(
    val status: Boolean,
    val message: String,
    val sectionData: List<SectionData>,
)
