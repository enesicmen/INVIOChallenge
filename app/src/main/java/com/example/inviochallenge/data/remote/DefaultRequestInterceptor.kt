package com.example.inviochallenge.data.remote

import com.example.inviochallenge.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class DefaultRequestInterceptor: Interceptor {

    companion object {
        private const val CONTENT_TYPE = "application/json"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()

        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.API_KEY)
            .build()

        original.newBuilder().apply {
            addHeader("Content-Type", CONTENT_TYPE)
            url(url)
            return chain.proceed(build())
        }
    }
}