package com.jaya.app.store.core.entities

data class UploadData(
    val productName : String,
    val brandName: String,
    val selectedSupplier:String,
    val basic:String,
    val selectGst:String,
    val rate: String,
    val quantity:String,
    val state:String,
)
