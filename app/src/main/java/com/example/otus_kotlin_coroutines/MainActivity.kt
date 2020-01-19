package com.example.otus_kotlin_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var fetchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hostName = findViewById<TextView>(R.id.host_name)
        val locationInfo = findViewById<TextView>(R.id.location_info)
        val fetchBtn = findViewById<Button>(R.id.fetch_btn)
        val progress = findViewById<ProgressBar>(R.id.progressBar)

        fetchBtn.setOnClickListener {
            fetchJob?.cancel()
            fetchJob = lifecycleScope.launch {
                progress.visibility = ProgressBar.VISIBLE

                try {
                    val geoInfo = FreeGeoIpAPI.geoInfo(hostName.text.toString())

                    val infoString = buildGeoInfoDescription(geoInfo)

                    locationInfo.text = infoString
                } catch (e: Exception) {
                    locationInfo.text = "Error:\n$e"
                } finally {
                    progress.visibility = ProgressBar.GONE
                }
            }
        }
    }

    private fun buildGeoInfoDescription(geoInfo: GeoInfo): String {
        val regionDesc = if (!geoInfo.regionName.isNullOrEmpty())
            "${geoInfo.countryName}, ${geoInfo.regionName}"
        else
            geoInfo.countryName

        return """
            IP: ${geoInfo.ip}
            Region: $regionDesc
            Timezone: ${geoInfo.timeZone}
            Lat, Lon: (${geoInfo.latitude}, ${geoInfo.longitude})
            """.trimIndent()
    }
}
