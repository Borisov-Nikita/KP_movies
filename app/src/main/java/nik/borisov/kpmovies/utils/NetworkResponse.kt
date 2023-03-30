package nik.borisov.kpmovies.utils

import retrofit2.Response

abstract class NetworkResponse {

    suspend fun <T> safeNetworkCall(call: suspend () -> Response<T>): DataResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return DataResult.Success(body)
                } ?: return errorMessage("Response body is empty")
            } else {
                return errorMessage("${response.code()}: ${response.message()}")
            }
        } catch (e: Exception) {
            return errorMessage(e.message.toString())
        }
    }

    private fun <T> errorMessage(error: String): DataResult.Error<T> =
        DataResult.Error(message = "Network call failed: $error")
}