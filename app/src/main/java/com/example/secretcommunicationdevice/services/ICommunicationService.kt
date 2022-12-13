package com.example.secretcommunicationdevice.services

import com.example.secretcommunicationdevice.models.Peer
import kotlinx.coroutines.flow.StateFlow

interface ICommunicationService {
//    val peers: StateFlow<Peer>
    fun registerId(id: String)
    fun getPeers(id: String)
}