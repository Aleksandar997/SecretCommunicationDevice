package com.example.secretcommunicationdevice

import android.app.Application

class SecretCommunicationDeviceApplication : Application() {
    lateinit var container: IAppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}