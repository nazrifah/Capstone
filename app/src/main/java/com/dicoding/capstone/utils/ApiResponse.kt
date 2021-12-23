package com.dicoding.capstone.utils

class ApiResponse<T> (val statusResponse: Status, val body: T, val message: String?){
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(Status.SUCCESS, body, null)

        fun <T> error(msg : String, body: T): ApiResponse<T> = ApiResponse(Status.ERROR, body, msg)
    }
}