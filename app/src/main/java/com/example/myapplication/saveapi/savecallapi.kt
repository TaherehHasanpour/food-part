package ir.partsoftware.programmingquote.network.common

import android.util.Log
import ir.partsoftware.programmingquote.ui.common.Result
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import retrofit2.Response

suspend fun <T> safeApi(
    call: suspend () -> Response<T>,
    onDataReady: (T) -> Unit
): Flow<Result> {
    return flow {
        emit(Result.Loading)
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    if (currentCoroutineContext().isActive) {
                        Log.d("tag","_subcategoryResponse else null$body")
                        onDataReady(body)
                    }
                    emit(Result.Success)
                } else {
                    Log.d("tag","_subcategoryResponse else aval")
                    emit(Result.Error("whoops body was empty"))
                }
            } else {
                Log.d("tag","_subcategoryResponse else dovom ${response.code()}")
                emit(Result.Error("whoops: got ${response.code()} code!"))
            }
        } catch (t: Throwable) {
            Log.d("tag","_subcategoryResponse Throwable ${t}")
            emit(Result.Error("whoops: ${t.message}"))
        }
    }.cancellable()
}

