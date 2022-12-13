package com.example.secretcommunicationdevice.services.static

import android.content.Context
import com.example.secretcommunicationdevice.GsonDecorator

class FileService {
    companion object {
        fun writeFileIfNotExists(fileName: String, fileContent: String, context: Context): Boolean {
            if (!context.fileList().contains(fileName) || fileName == "defaultTemplate") {
                context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                    it.write(fileContent.toByteArray())
                }
                return true
            }
            return false
        }

        inline fun <reified T> readFile(fileName: String, context: Context): T = GsonDecorator.fromJson(context.openFileInput(fileName).bufferedReader().readLine())
        //inline fun <reified T> readFile(fileName: String, context: Context): T = Gson().fromJson(context.openFileInput(fileName).bufferedReader().readLine(), object: TypeToken<T>() {}.type)
    }
}