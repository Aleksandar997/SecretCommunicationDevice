package com.example.secretcommunicationdevice.services.static

import androidx.compose.material.DrawerValue
import androidx.compose.runtime.mutableStateOf
import com.example.secretcommunicationdevice.models.Peer
import kotlinx.coroutines.flow.*
import java.util.*

class ReactiveService {
    companion object {
        var formTitle = MutableStateFlow("")
        var text = mutableStateOf("")
        var activeRoute = MutableStateFlow("")

        var id = MutableStateFlow(UUID(0L, 0L))
        var peers = MutableStateFlow<List<Peer>>(emptyList())
        var snackbarMessage = MutableStateFlow("")
//        private val _onFloatingActionButtonClick = MutableSharedFlow<String>()
//        val onFloatingActionButtonClick: SharedFlow<String> = _onFloatingActionButtonClick
//
//        suspend fun floatingActionButtonClick() = _onFloatingActionButtonClick.emit(activeRoute.value)
    }
}