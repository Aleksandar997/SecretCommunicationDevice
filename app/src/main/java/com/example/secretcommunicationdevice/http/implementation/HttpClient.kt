package com.example.secretcommunicationdevice.http.implementation

import com.example.secretcommunicationdevice.GsonDecorator
import com.example.secretcommunicationdevice.http.IHttpClient
import com.example.secretcommunicationdevice.models.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject

class HttpClient(url: String): IHttpClient {
    private var _url: String
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    init {
        _url = url
    }

    class HttpResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("data") val data: Object
    )

    override suspend fun post(body: Map<String, String>, route: String): Flow<Response> = flow {
        coroutineScope.launch() {
            var url: URL = URL(_url + route)
            val response = StringBuffer()
            try {
                var reqParam = ""
                body.keys.forEach {
                    reqParam = URLEncoder.encode(it, "UTF-8") + "=" + URLEncoder.encode(
                        body.getValue(it),
                        "UTF-8"
                    )
                }

                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"
                    setRequestProperty("Content-Type", "application/json")
                    val wr = outputStream.writer()
                    wr.write(reqParam);
                    wr.flush();

                    BufferedReader(InputStreamReader(inputStream)).use {
                        var inputLine = it.readLine()
                        while (inputLine != null) {
                            response.append(inputLine)
                            inputLine = it.readLine()
                        }

                        val result = Gson().fromJson<Response>(inputLine)
                        emit(result)
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    override suspend fun get(route: String): Flow<JSONObject>  {
        return flow {
            var url: URL = URL(_url + route)
            try {
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "GET"
                    doInput = true
                    doOutput = false
                    setRequestProperty("Content-Type", "application/json")

                    val responseStr = inputStream.bufferedReader()
                        .use {
                            it.readText()
                        }

                    //val result = GsonDecorator.fromJson<HttpResponse>(responseStr)

                    //val result = ResponseData<T>(httpResult.success, Gson().fromJson<T>(httpResult.data))
                    val test = JSONObject(responseStr)
                    emit(test)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    private inline fun <reified T> Gson.fromJson(json: String?) = fromJson<T>(json, object: TypeToken<T>() {}.type)

}
