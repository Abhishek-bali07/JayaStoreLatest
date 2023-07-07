package com.jaya.app.store.core.utils


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jaya.app.store.core.common.constants.Data
import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.common.enums.EmitType
import kotlinx.coroutines.flow.FlowCollector


inline fun <reified T> T.encodeJson(): String = Gson().toJson(this, object : TypeToken<T>() {}.type)

inline fun <reified T> String.decodeJson(): T =
    Gson().fromJson(this, object : TypeToken<T>() {}.type)

suspend inline fun <reified R> FlowCollector<Data>.handleFailedResponse(
    response: Resource<R>,
    message: String?,
    emitType: EmitType
) {
    when (message != null) {
        true -> {
            emit(Data(emitType, message))
        }
        else -> {
            emit(Data(emitType, response.message))
        }
    }
}