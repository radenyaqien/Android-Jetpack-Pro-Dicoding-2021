package id.radenyaqien.jetpackdicoding.retrofit

import id.radenyaqien.jetpackdicoding.utils.Status

data class ApiResponse<R>(
    val status: Status?,
    val message: String?,
    val data: R?
)
