package com.example.secretcommunicationdevice.services.implementation

import android.content.Context
import com.example.secretcommunicationdevice.repository.IIdentifierRepository
import com.example.secretcommunicationdevice.services.IIdentifierService
import com.example.secretcommunicationdevice.services.static.ReactiveService

class IdentifierService(
    _identifierRepository: IIdentifierRepository
): IIdentifierService {
    private val identifierRepository: IIdentifierRepository
    init {
        identifierRepository = _identifierRepository
    }

    override fun initializeIdentity(context: Context) {
        ReactiveService.id.value = identifierRepository.getIdentity(context)
    }
}