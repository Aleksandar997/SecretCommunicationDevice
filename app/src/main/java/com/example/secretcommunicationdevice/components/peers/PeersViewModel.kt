package com.example.secretcommunicationdevice.components.peers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.secretcommunicationdevice.models.Peer
import com.example.secretcommunicationdevice.services.ICommunicationService
import com.example.secretcommunicationdevice.services.static.ReactiveService
import kotlinx.coroutines.launch

class PeersViewModel(
    communicationService: ICommunicationService
) : ViewModel() {
    var peers: List<Peer> = emptyList()
    init {
        //communicationService.getPeers(ReactiveService.id.value)

//        viewModelScope.launch {
//            communicationService.peers.collect {
//                peers = it.connections
//            }
//        }
    }

    companion object {
        fun provideFactory(
            communicationService: ICommunicationService
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PeersViewModel(communicationService) as T
            }
        }
    }
}