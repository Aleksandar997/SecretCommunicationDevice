package com.example.secretcommunicationdevice.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.core.os.HandlerCompat.postDelayed
import kotlinx.coroutines.flow.MutableStateFlow

class BluetoothService(bluetoothManager: BluetoothManager) {
    private val bluetoothManager: BluetoothManager
    var devicesList = MutableStateFlow<List<BluetoothDevice>>(emptyList())

    init {
        this.bluetoothManager = bluetoothManager
    }

    fun getPairedDevices(context: Context): Unit {
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
        if (bluetoothAdapter != null &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            if (!bluetoothAdapter.isEnabled) {
                bluetoothAdapter.enable()
            }
            Handler(Looper.getMainLooper()).postDelayed({
                devicesList.value = bluetoothAdapter.bondedDevices.toList()
            }, 1)

        }
    }

//    val receiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context, intent: Intent) {
//            val action = intent.action
//            if (BluetoothDevice.ACTION_FOUND == action) {
//                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
//                if (device != null) {
//                    devicesList.add(device)
//                }
//
//            }
//        }
//    }

//    private fun discoverDevices(
//        context: Context,
//        registerReceiver: (receiver: BroadcastReceiver, filter: IntentFilter) -> Intent,
//        unregisterReceiver: (receiver: BroadcastReceiver) -> Unit) {
//        if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.BLUETOOTH_SCAN
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        if (bluetoothManager.adapter!!.isDiscovering) {
//            // Bluetooth is already in mode discovery mode, we cancel to restart it again
//            bluetoothManager.adapter!!.cancelDiscovery()
//        }
//        val bool = bluetoothManager.adapter?.startDiscovery()
//        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
//        registerReceiver(receiver, filter)
//
//
//        linearLayoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = linearLayoutManager
//
//        adapter = RecyclerAdapter(devicesList)
//        recyclerView.adapter = adapter
//        findViewById<SwipeRefreshLayout>(R.id.swipeContainer).isRefreshing = false
//        unregisterReceiver(mReceiver)
//    }

    companion object {
        private var instance: BluetoothService? = null

        fun getInstance(): BluetoothService {
            return instance ?: throw IllegalStateException("BluetoothService instance is null. Call initialize method before getInstance method")
        }

        fun initialize(context: Context, bluetoothManager: BluetoothManager) {
            if (instance == null) {
                instance = BluetoothService(bluetoothManager)
            }
        }
    }
}