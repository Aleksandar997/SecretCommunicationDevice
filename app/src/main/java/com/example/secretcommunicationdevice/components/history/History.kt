package com.example.secretcommunicationdevice.components.history

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.secretcommunicationdevice.DependencyInjector

var History: @Composable () -> Unit = {
    val communicationService = DependencyInjector.getInstance().services.communicationService
    val historyViewModel: HistoryViewModel = viewModel(
        factory = HistoryViewModel.provideFactory(communicationService)
    )
    _History(historyViewModel)
}

private var _History: @Composable (historyViewModel: HistoryViewModel) -> Unit = {
    Text(text = "Templates")
}