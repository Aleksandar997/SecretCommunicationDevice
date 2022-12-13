package com.example.secretcommunicationdevice.services

import android.content.Context
import com.example.secretcommunicationdevice.models.Template

interface ITemplateService {
    fun initializeTemplates(context: Context)
    fun getLoadedTemplate(): Template?
}