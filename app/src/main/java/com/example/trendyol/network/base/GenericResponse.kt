package com.turkcell.android.network.base

data class GenericResponse<T>(
    val status : Status,
    val content: T?,
    // val error: GenericError?
)
