package com.example.secretcommunicationdevice.communication

import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import kotlin.concurrent.thread

enum class ThreadState {
    NotStarted, FindingPeer, PeerNotFound, Error, Test
}

class Communication(activity: ComponentActivity, id: UUID) {
    var threadState = MutableStateFlow(ThreadState.NotStarted)
    var ip = ""
    var sport = 0
    var dport = 0
    var id = UUID(0L, 0L).toString()
    private val scope = CoroutineScope(Dispatchers.Default)
    private val activity: ComponentActivity
    init {
        this.activity = activity
        this.id = id.toString()
    }

    suspend fun initializeClient() {
        var job = scope.launch(Dispatchers.IO) {
            try {
                fun isReady(result: String): Boolean {
                    var isValid = true
                    "ready".asIterable().forEachIndexed  { i, c ->
                        isValid = result[i] == c
                    }
                    return isValid
                }

                threadState.emit(ThreadState.FindingPeer)
                val clientToRendezvousServer = UdpClient("0.0.0.0", 41819)
                //clientToRendezvousServer.sendPacket(id, "34.125.197.205", 55555)
                clientToRendezvousServer.sendPacket("0", "34.125.197.205", 55555)
                var isRead = false
                while (!isRead) {
                    val data = clientToRendezvousServer.recv(1024)
                    if (isReady(data)) {
                        isRead = true
                    }
                    val routeData = data.split(' ')
                    if (routeData.size == 3) {
                        ip = routeData[0]
                        sport = routeData[1].toInt()
                        dport = String(routeData[2].toByteArray().filter { it > 0 }.toByteArray()).toInt()
                        id = String(routeData[3].toByteArray().filter { it > 0 }.toByteArray())
                        clientToRendezvousServer.close()

                        val clientHolePunch = UdpClient("0.0.0.0", sport)
                        clientHolePunch.sendPacket("0", ip, dport)
                        clientHolePunch.close()
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        delay(5000L)
        if (!job.isCompleted) {
            job.cancel()
            threadState.emit(ThreadState.PeerNotFound)
        }
    }

    fun _listenForMessages() {
        val clientListen = UdpClient("0.0.0.0", sport)
        while (true) {
            val receivedMessage = clientListen.recv(1024)
            println(receivedMessage)
        }
        clientListen.close()
    }

    fun sendMessage() {
        val clientSendMessage = UdpClient("0.0.0.0", dport)
        clientSendMessage.sendPacket("msg", ip, sport)
        clientSendMessage.close()
    }
}