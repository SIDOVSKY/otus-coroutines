package com.example.otus_kotlin_coroutines

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun Call.enqueueSuspendable(): Response {
    return suspendCancellableCoroutine {
        enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                it.resume(response)
            }

            override fun onFailure(call: Call, e: IOException) {
                it.resumeWithException(e)
            }
        })
    }
}