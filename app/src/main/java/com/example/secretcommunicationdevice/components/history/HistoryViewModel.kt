package com.example.secretcommunicationdevice.components.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.secretcommunicationdevice.services.ICommunicationService
import com.example.secretcommunicationdevice.services.static.ReactiveService
import kotlinx.coroutines.launch

class HistoryViewModel(
    communicationService: ICommunicationService
) : ViewModel() {
    init {
        //communicationService.getPeers(ReactiveService.id.value)

        //viewModelScope.launch {
//            communicationService.peers.collect {
//                peers = it.connections
//            }
        //}
    }

    companion object {
        fun provideFactory(
            communicationService: ICommunicationService
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HistoryViewModel(communicationService) as T
            }
        }
    }
}