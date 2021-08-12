package com.emdp.pruebatecnica.mobgen.common

import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import com.emdp.pruebatecnica.mobgen.R
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
        fun getDateFormat(date: String?): String? {
            var dateFormat: String? = ""

            if(null != date && date.isNotEmpty()) {
                val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                dateFormat = LocalDate.parse(date, format).toString()
            }
            return dateFormat
        }

        fun showHideViewItems(tv1: TextView, tv2: TextView, data: String?) {
            if (null != data && data.isNotEmpty()) {
                tv1.visibility = View.VISIBLE
                tv2.visibility = View.VISIBLE
                tv2.text = data
            } else {
                tv1.visibility = View.GONE
                tv2.visibility = View.GONE
            }
        }

        fun showHideViewItems(tv: TextView, ll: LinearLayout, dataList: List<String>?,
                              context: Context) {
            if (null != dataList && dataList.isNotEmpty()) {
                tv.visibility = View.VISIBLE
                ll.visibility = View.VISIBLE
                createDynamicLists(ll, dataList, context)
            } else {
                tv.visibility = View.GONE
                ll.visibility = View.GONE
            }
        }

        private fun createDynamicLists(linearLayout: LinearLayout, itemList: List<String>,
                                       context: Context) {
            linearLayout.removeAllViews()

            itemList.forEach { item ->
                val tvItem = TextView(context)
                tvItem.setTextColor(
                    ContextCompat.getColor(context, R.color.primary_light)
                )
                tvItem.textSize = 16.0F
                tvItem.text = item
                tvItem.ellipsize = TextUtils.TruncateAt.END
                tvItem.isSingleLine = false
                tvItem.maxLines = 2

                val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                tvItem.layoutParams = lp

                linearLayout.addView(tvItem)
            }
        }
    }
}