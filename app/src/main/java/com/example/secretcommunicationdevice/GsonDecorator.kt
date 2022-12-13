package com.example.secretcommunicationdevice

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GsonDecorator {
    companion object {
        inline fun <reified T> fromJson(json: String?) = Gson().fromJson<T>(json, object: TypeToken<T>() {}.type)
    }
}