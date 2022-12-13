package com.example.secretcommunicationdevice.repository.implementation

import android.content.Context
import com.example.secretcommunicationdevice.repository.IIdentifierRepository
import java.util.*

class IdentifierRepository : IIdentifierRepository {
    private val defaultTranslation = "identity"

    override fun getIdentity(context: Context): UUID {
        if (!context.fileList().contains(defaultTranslation)) {
            context.openFileOutput(defaultTranslation, Context.MODE_PRIVATE).use {
                it.write(UUID.randomUUID().toString().toByteArray())
            }
        }
        return UUID.fromString(context.openFileInput(defaultTranslation).bufferedReader().readLine())
    }
}