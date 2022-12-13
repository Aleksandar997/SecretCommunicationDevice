package com.example.secretcommunicationdevice.services

import com.example.secretcommunicationdevice.models.SignalConfig

interface IMorseCodeParser {
    fun textToCode(textParam: String): List<SignalConfig>
}