package com.ahsan.gitrepos.data.remote.models

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val data: T): ResponseWrapper<T>()
    data class Error(val message: String): ResponseWrapper<Nothing>()
    data class Loading(val loading: Boolean): ResponseWrapper<Nothing>()
}
