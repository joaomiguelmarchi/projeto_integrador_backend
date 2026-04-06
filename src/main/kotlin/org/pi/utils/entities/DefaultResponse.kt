package org.pi.utils.entities

data class DefaultResponse<T>(
    val data: T? = null,
    val error: DefaultError? = null
)