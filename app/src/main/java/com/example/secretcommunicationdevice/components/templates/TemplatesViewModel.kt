package com.example.secretcommunicationdevice.components.templates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.secretcommunicationdevice.services.ICommunicationService
import com.example.secretcommunicationdevice.services.static.ReactiveService
import kotlinx.coroutines.launch

class TemplatesViewModel(
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
                return TemplatesViewModel(communicationService) as T
            }
        }
    }
}