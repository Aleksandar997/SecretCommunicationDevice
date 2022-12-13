package com.example.secretcommunicationdevice.services.implementation

import com.example.secretcommunicationdevice.models.SignalConfig
import com.example.secretcommunicationdevice.services.IMorseCodeParser
import com.example.secretcommunicationdevice.services.ITemplateService

class MorseCodeParser(_templateService: ITemplateService): IMorseCodeParser {
    private val templateService: ITemplateService
    init {
        templateService = _templateService
    }

    override fun textToCode(textParam: String): List<SignalConfig> {
        val text = textParam.uppercase()
        val res: MutableList<SignalConfig> = arrayListOf()
        var loadedTemplate = templateService.getLoadedTemplate() ?: return res

        if (loadedTemplate.data.phraseData.isNotEmpty()) {
            val textCopy = text.toString()
            textCopy.split(" ").forEach {
                var phrase = loadedTemplate.data.phraseData.getValue(it)
                res.addAll(phrase.map { p -> loadedTemplate.signalConfig.getValue(p) })
                res.add(SignalConfig(
                    loadedTemplate.sentenceConfig.endOfWordPause,
                    "|"
                ))
                text.replace(it, "")
            }
        }

        for (letter: Char in text) {
            if (letter == ' ') {
                res.add(SignalConfig(
                    loadedTemplate.sentenceConfig.endOfWordPause,
                    "|"
                ))
                continue
            }
            val vibrationTimes = loadedTemplate.data.letterData.getValue(letter.toString())
            if (vibrationTimes != null) {
                vibrationTimes.iterator().forEach { v ->
                    val vibrationTime = loadedTemplate.signalConfig.getValue(v)
                    res.add(vibrationTime)
                    res.addAll(loadedTemplate.sentenceConfig.betweenLettersPattern.map {x -> SignalConfig(x, "")})
                }
            }

        }
        return res;
    }
}