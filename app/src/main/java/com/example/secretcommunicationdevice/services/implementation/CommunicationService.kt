package com.example.secretcommunicationdevice.services.implementation

import com.example.secretcommunicationdevice.http.IHttpClient
import com.example.secretcommunicationdevice.models.Peer
import com.example.secretcommunicationdevice.services.ICommunicationService
import com.example.secretcommunicationdevice.GsonDecorator
import com.example.secretcommunicationdevice.models.ResponseData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking

class CommunicationService(_httpClient: IHttpClient): ICommunicationService {
    private val httpClient: IHttpClient
//    private val _peers = MutableStateFlow(Peer.emptyPeer())
//    override val peers: StateFlow<Peer> = _peers
    init {
        httpClient = _httpClient
    }

    override fun registerId(id: String) = runBlocking {
        httpClient.post(mapOf("id" to id), "connect_peer").collect { res ->
            val test = res
        }
    }

    override fun getPeers(id: String) = runBlocking {
        httpClient.get("peer_data/$id").collect { resSerialized ->
            var res = GsonDecorator.fromJson<ResponseData<Peer>>(resSerialized.toString())
//            if (res.success) {
//                _peers.value = res.data
//            }
        }
    }
}