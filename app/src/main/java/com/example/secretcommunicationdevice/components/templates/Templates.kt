package com.example.secretcommunicationdevice.components.templates

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.secretcommunicationdevice.DependencyInjector

var Templates: @Composable () -> Unit = {
    val communicationService = DependencyInjector.getInstance().services.communicationService
    val templatesViewModel: TemplatesViewModel = viewModel(
        factory = TemplatesViewModel.provideFactory(communicationService)
    )
    _Templates(templatesViewModel)
}

private var _Templates: @Composable (templatesViewModel: TemplatesViewModel) -> Unit = {
    Text(text = "Templates")
}