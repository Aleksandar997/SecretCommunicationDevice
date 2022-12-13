package com.example.secretcommunicationdevice

class DependencyInjector(appContainer: IAppContainer) {

    var services: IAppContainer

    init {
        services = appContainer
    }

    companion object {
        private var instance: DependencyInjector? = null

        fun getInstance(): DependencyInjector {
            return instance ?: throw IllegalStateException("DependencyInjector instance is null. Call initialize method before getInstance method")
        }

        fun initialize(appContainer: IAppContainer) {
            if (instance == null) {
                instance = DependencyInjector(appContainer)
            }
        }
    }
}