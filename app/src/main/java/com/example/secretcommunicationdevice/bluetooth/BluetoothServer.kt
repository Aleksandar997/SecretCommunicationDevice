package com.example.secretcommunicationdevice.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.secretcommunicationdevice.MainActivity
import java.io.IOException
import java.util.*

class BluetoothServer(context: Context, id: UUID) : Thread() {
    private var cancelled: Boolean
    private val serverSocket: BluetoothServerSocket?
    private val context = context

    init {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            throw Exception("Bluetooth permission not granted")
        }

        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val btAdapter = bluetoothManager.adapter
        if (btAdapter != null) {
            this.serverSocket = btAdapter.listenUsingRfcommWithServiceRecord("test", id)
            this.cancelled = false
        } else {
            this.serverSocket = null
            this.cancelled = true
        }

    }

    override fun run() {
        var socket: BluetoothSocket

        while(true) {
                if (this.cancelled) {
                break
            }

            try {
                socket = serverSocket!!.accept()  // 2
            } catch(e: IOException) {
                break
            }

            if (!this.cancelled && socket != null) {
                Log.i("server", "Connecting")
                _BluetoothServer(socket).start() // 3
            }
        }
    }

    fun cancel() {
        this.cancelled = true
        this.serverSocket!!.close()
    }
}

class _BluetoothServer(private val socket: BluetoothSocket): Thread() {
    private val inputStream = this.socket.inputStream
    private val outputStream = this.socket.outputStream

    override fun run() {
        try {
            val available = inputStream.available()
            val bytes = ByteArray(available)
            Log.i("server", "Reading")
            inputStream.read(bytes, 0, available)
            val text = String(bytes)
            Log.i("server", "Message received")
            Log.i("server", text)
        } catch (e: Exception) {
            Log.e("client", "Cannot read data", e)
        } finally {
            inputStream.close()
            outputStream.close()
            socket.close()
        }
    }
}

