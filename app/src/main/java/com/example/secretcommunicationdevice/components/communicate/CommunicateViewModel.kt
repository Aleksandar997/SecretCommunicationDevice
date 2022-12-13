package com.example.secretcommunicationdevice.components.communicate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.secretcommunicationdevice.models.Peer
import com.example.secretcommunicationdevice.services.ICommunicationService
import com.example.secretcommunicationdevice.services.IMorseCodeParser
import com.example.secretcommunicationdevice.services.static.ReactiveService
import kotlinx.coroutines.launch

class CommunicateViewModel(
    communicationService: ICommunicationService,
    _morseCodeParse: IMorseCodeParser
) : ViewModel() {
    var peers: List<Peer> = emptyList()
    var morseCodeParse = _morseCodeParse
    init {
//        communicationService.getPeers(ReactiveService.id.value)
//
//        viewModelScope.launch {
//            communicationService.peers.collect {
//                peers = it.connections
//            }
//        }
    }

    companion object {
        fun provideFactory(
            communicationService: ICommunicationService,
            morseCodeParse: IMorseCodeParser
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CommunicateViewModel(communicationService, morseCodeParse) as T
            }
        }
    }
}

