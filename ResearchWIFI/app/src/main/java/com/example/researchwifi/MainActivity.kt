package com.example.researchwifi

import android.Manifest
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {

    companion object {
        const val NETWORK_NAME = "Nokia G20"
        const val NETWORK_PASS = "???"
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCheckInternetConnection = findViewById<Button>(R.id.btnCheck)
        val network = findViewById<TextView>(R.id.net)
        val networkNumber = findViewById<TextView>(R.id.networksInRange)
        val wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager


        ActivityCompat.requestPermissions(
            this, arrayOf(
                android.Manifest.permission.ACCESS_WIFI_STATE,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ), PackageManager.PERMISSION_GRANTED
        )

        var scan = wifiManager.scanResults

        if (scan.size > 0) {
            Toast.makeText(
                applicationContext, "You are in WIFI range", Toast.LENGTH_SHORT
            ).show()

            showToastIfInRangeOf(scan)

        }

        if (!wifiManager.isWifiEnabled) {
            Toast.makeText(
                applicationContext, "Please switch on WIFI", Toast.LENGTH_SHORT
            ).show()
        } else {
            if (scan.size == 0) {
                Toast.makeText(
                    applicationContext, "You are not in WIFI range", Toast.LENGTH_SHORT
                ).show()
            }
        }

        buttonCheckInternetConnection.setOnClickListener {
            scan = wifiManager.scanResults

            if (wifiManager.isWifiEnabled) {
                networkNumber.text = "Networks number in device's range is ${scan.size}"

                val wifiInfo = wifiManager.connectionInfo
                val ssid = wifiInfo.ssid
                Toast.makeText(
                    applicationContext, "CONNECTED - $ssid NETWORK", Toast.LENGTH_SHORT
                ).show()

                network.text = "Connected to " + ssid + " wifi"

                for (scanResult in scan) {
                    showToastIfInRangeOf(scan)
                    if (scanResult.SSID == NETWORK_NAME) {
//                        wifiManager.disconnect()
                        connectUsingNetworkSuggestion(
                            ssid = NETWORK_NAME, password = NETWORK_PASS, wifiManager
                        )
                        wifiManager.reconnect()
                    }
                }
            } else {
                network.text = "N/A"
                wifiManager.isWifiEnabled = true
                val scan = if (ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return@setOnClickListener
                } else {
                }
                wifiManager.scanResults
                connectUsingNetworkSuggestion(
                    ssid = NETWORK_NAME, password = NETWORK_PASS, wifiManager
                )
                Toast.makeText(applicationContext, "Internet Connected NOT", Toast.LENGTH_SHORT)
                    .show()
            }

        }


    }

    private fun showToastIfInRangeOf(scan: List<ScanResult>) {
        for (scanResult in scan) {
            if (scanResult.SSID == NETWORK_NAME) {
                Toast.makeText(
                    applicationContext, "WIFI DETECTED - YOU ARE IN THE RANGE OF $NETWORK_NAME", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun connectUsingNetworkSuggestion(
        ssid: String, password: String, wifiManager: WifiManager
    ) {
        val wifiConfig = WifiConfiguration()
        wifiConfig.SSID = String.format("\"%s\"", ssid);
        wifiConfig.preSharedKey = String.format("\"%s\"", password);
        val netId = wifiManager.addNetwork(wifiConfig)
        wifiManager.enableNetwork(netId, true)
        wifiManager.reconnect()
    }
}