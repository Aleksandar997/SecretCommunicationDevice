package com.example.secretcommunicationdevice.repository

import android.content.Context
import java.util.*

interface IIdentifierRepository {
    fun getIdentity(context: Context): UUID
}