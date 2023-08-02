package com.jaya.app.store.core.common.constants


import com.jaya.app.store.core.common.enums.EmitType


data class Data(
    val type: EmitType = EmitType.NONE,
    val value: Any?
)

