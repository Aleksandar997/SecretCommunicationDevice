package com.example.secretcommunicationdevice.http

import com.example.secretcommunicationdevice.http.implementation.HttpClient
import com.example.secretcommunicationdevice.models.Response
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject

interface IHttpClient {
    suspend fun post(body: Map<String, String>, route: String): Flow<Response>
    suspend fun get(route: String): Flow<JSONObject>
}