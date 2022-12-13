package com.example.secretcommunicationdevice.bluetooth

import android.bluetooth.BluetoothDevice
import android.util.Log
import kotlinx.coroutines.NonCancellable

//class BluetoothClient(device: BluetoothDevice, connectionId: UUID): Thread() {
//    private val socket = device.createRfcommSocketToServiceRecord(uuid)
//
//    override fun run() {
//        Log.i("client", "Connecting")
//        this.socket.connect()
//
//        Log.i("client", "Sending")
//        val outputStream = this.socket.outputStream
//        val inputStream = this.socket.inputStream
//        try {
//            outputStream.write(NonCancellable.message.toByteArray())
//            outputStream.flush()
//            Log.i("client", "Sent")
//        } catch(e: Exception) {
//            Log.e("client", "Cannot send", e)
//        } finally {
//            outputStream.close()
//            inputStream.close()
//            this.socket.close()
//        }
//    }
//}