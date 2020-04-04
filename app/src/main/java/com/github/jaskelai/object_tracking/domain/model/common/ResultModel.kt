package com.github.jaskelai.object_tracking.domain.model.common

sealed class Result<out SUCCESS : Any, out ERROR : Any> {
    data class Success<out SUCCESS : Any>(val data: SUCCESS? = null) : Result<SUCCESS, Nothing>()
    data class Error<out ERROR : Any>(val data: ERROR? = null) : Result<Nothing, ERROR>()
}

fun <SUCCESS : Any, ERROR : Any> Result<SUCCESS, ERROR>.resultCallback(
    onSuccess: (dataSuccess: SUCCESS) -> Unit,
    onError: (dataError: ERROR) -> Unit
) {
    when (this) {
        is Result.Success -> onSuccess.invoke(this.data!!)
        is Result.Error -> onError.invoke(this.data!!)
    }
}
