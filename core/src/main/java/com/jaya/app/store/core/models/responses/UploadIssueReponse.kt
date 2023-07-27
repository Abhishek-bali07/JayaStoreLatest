package com.jaya.app.store.core.models.responses

data class UploadIssueResponse(
    val status : Boolean,
    val message:String,
    val isSubmitted: Boolean
)
