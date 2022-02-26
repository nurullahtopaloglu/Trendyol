package com.example.trendyol.network

import android.content.Context
import com.example.trendyol.BuildConfig
import com.example.trendyol.network.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitProvider
@Inject constructor(
    private val context: Context,
    private val domain: String
) {

    companion object {
        private const val HEADER_CONTENT_TYPE = "Content-Type"
        private const val HEADER_BUILD = "Build"
        private const val TAG = "RetrofitProvider"
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(domain )
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    private val httpLoggingInterceptor: HttpLoggingInterceptor by lazy {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        return@lazy logging
    }



    private val headerInterceptor: Interceptor by lazy {
        Interceptor { it ->
            val requestBuilder = it.request()
                .newBuilder()
                .addHeader(HEADER_CONTENT_TYPE, "application/json")
                .addHeader(HEADER_BUILD, "500")

            return@Interceptor it.proceed(requestBuilder.build())
        }
    }

    fun <A> create(apiClass: Class<A>): A {
        return retrofit.create(apiClass)
    }
}
