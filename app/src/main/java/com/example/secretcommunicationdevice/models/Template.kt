package com.example.secretcommunicationdevice.models

import com.google.gson.annotations.SerializedName

data class SentenceConfig(
    @SerializedName("endOfWordPause") val endOfWordPause: Long,
    @SerializedName("betweenLettersPattern") val betweenLettersPattern: List<Long>
)

data class SignalConfig(
    @SerializedName("milliseconds") val milliseconds: Long,
    @SerializedName("symbol") val symbol: String
)

data class Data(
    @SerializedName("phraseData") val phraseData: Map<String, Array<String>>,
    @SerializedName("letterData") val letterData: Map<String, Array<String>>
)

data class Template(
    @SerializedName("name") val name: String,
    @SerializedName("signalConfig") val signalConfig: Map<String, SignalConfig>,
    @SerializedName("sentenceConfig") val sentenceConfig: SentenceConfig,
    @SerializedName("data") val data: Data
)
