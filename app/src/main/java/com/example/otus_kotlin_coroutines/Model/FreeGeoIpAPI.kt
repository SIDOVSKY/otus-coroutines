package com.example.otus_kotlin_coroutines

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.*

object FreeGeoIpAPI {
    private const val HTTP_TYPE_JSON = "application/json"

    private var httpClient = OkHttpClient()
    private var requestBuilder = Request.Builder()

    suspend fun geoInfo(hostName: String?): GeoInfo {
        val url = "https://freegeoip.app/json/$hostName"

        val request = requestBuilder
            .get()
            .url(url)
            .addHeader("accept", HTTP_TYPE_JSON)
            .addHeader("content-type", HTTP_TYPE_JSON)
            .build()

        var response = httpClient.newCall(request).enqueueSuspendable()

        return Json(JsonConfiguration.Stable)
            .parse(GeoInfo.serializer(), response.body!!.string())
    }
}
