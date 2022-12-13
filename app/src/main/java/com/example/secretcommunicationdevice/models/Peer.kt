package com.example.secretcommunicationdevice.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Peer(
//    @SerializedName("name") val name: String,
    @SerializedName("id") val id: UUID,
    @SerializedName("ip") val ip: String,
//    @SerializedName("connections") val connections: List<Peer>,
    @SerializedName("morse_templates") val morse_templates: List<String>
) {
//    companion object {
//        fun emptyPeer(): Peer {
//            return Peer(UUID(0L, 0L), "", emptyList())
//        }
//    }
}
