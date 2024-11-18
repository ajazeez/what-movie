package com.aj.whatmovie.data.models

sealed class APIResponse<out T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T?): APIResponse<T>(data)
    class Error<T>(data: T?, message: String?): APIResponse<T>(data, message)
    class Loading<T> : APIResponse<T>()
}
