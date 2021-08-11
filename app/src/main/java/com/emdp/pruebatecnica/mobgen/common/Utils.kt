package com.emdp.pruebatecnica.mobgen.common

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class Utils {

    companion object {
        private var httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        private var okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://private-anon-f141a44db8-androidtestmobgen.apiary-mock.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @RequiresApi(Build.VERSION_CODES.O)
        fun getDate(date: String): String {
            val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            return LocalDate.parse(date, format).toString()
        }
    }
}