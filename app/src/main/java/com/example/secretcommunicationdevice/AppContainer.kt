package com.example.secretcommunicationdevice

import android.content.Context
import com.example.secretcommunicationdevice.http.IHttpClient
import com.example.secretcommunicationdevice.http.implementation.HttpClient
import com.example.secretcommunicationdevice.repository.IIdentifierRepository
import com.example.secretcommunicationdevice.repository.implementation.IdentifierRepository
import com.example.secretcommunicationdevice.services.ICommunicationService
import com.example.secretcommunicationdevice.services.IIdentifierService
import com.example.secretcommunicationdevice.services.IMorseCodeParser
import com.example.secretcommunicationdevice.services.ITemplateService
import com.example.secretcommunicationdevice.services.implementation.CommunicationService
import com.example.secretcommunicationdevice.services.implementation.IdentifierService
import com.example.secretcommunicationdevice.services.implementation.MorseCodeParser
import com.example.secretcommunicationdevice.services.implementation.TemplateService

interface IAppContainer {
    val identifierRepository: IIdentifierRepository
    val templateService: ITemplateService
    val morseCodeParser: IMorseCodeParser
    val identifierService: IIdentifierService
    val communicationService: ICommunicationService
    val httpClient: IHttpClient
}

class AppContainer(private val applicationContext: Context) : IAppContainer {
    override val identifierRepository: IIdentifierRepository by lazy {
        IdentifierRepository()
    }

    override val templateService: ITemplateService by lazy {
        TemplateService()
    }

    override val morseCodeParser: IMorseCodeParser by lazy {
        MorseCodeParser(templateService)
    }

    override val identifierService: IIdentifierService by lazy {
        IdentifierService(identifierRepository)
    }

    override val communicationService: ICommunicationService by lazy {
        CommunicationService(httpClient)
    }

    override val httpClient: IHttpClient by lazy {
        HttpClient("http://192.168.0.50:5001/")
    }
}
