package com.example.secretcommunicationdevice.services.implementation

import android.content.Context
import com.example.secretcommunicationdevice.models.Template
import com.example.secretcommunicationdevice.services.ITemplateService
import com.example.secretcommunicationdevice.services.static.FileService

class TemplateService: ITemplateService {
    private var loadedTemplate: Template? = null
    override fun initializeTemplates(context: Context) {
        val isWritten = FileService.writeFileIfNotExists(defaultTemplateFileName, defaultTemplate, context)

        if (isWritten) {
            FileService.writeFileIfNotExists(loadedTemplateFileName, defaultTemplateFileName, context)
        }
        val loadedTemplateName = FileService.readFile<String>(loadedTemplateFileName, context)

        loadedTemplate = FileService.readFile<Template>(loadedTemplateName, context)
    }

    override fun getLoadedTemplate(): Template? {
        return loadedTemplate
    }
}

private const val loadedTemplateFileName = "loadedTemplate"
private const val defaultTemplateFileName = "defaultTemplate"
private const val defaultTemplate = "{  \"name\": \"defaultTemplate\",  \"signalConfig\": {    \"0\": {      \"milliseconds\": 500,      \"symbol\": \"•\"    },    \"1\": {      \"milliseconds\": 1000,      \"symbol\": \"━\"    }  },  \"sentenceConfig\": {    \"endOfWordPause\": 1000,    \"betweenLettersPattern\": [200, 100, 200, 100, 200]  },  \"data\": {    \"phraseData\": {},    \"letterData\": {      \"A\": [0, 1],      \"B\": [1, 0, 0, 0],      \"C\": [1, 0, 1, 0],      \"E\": [0],      \"F\": [0, 0, 1, 0],      \"G\": [1, 1, 0],      \"H\": [0, 0, 0, 0],      \"I\": [0, 0],      \"J\": [0, 1, 1, 1],      \"K\": [1, 0, 1],      \"L\": [0, 1, 0, 0],      \"M\": [1, 1],      \"N\": [1, 0],      \"O\": [1, 1, 1],      \"P\": [0, 1, 1, 0],      \"Q\": [1, 1, 0 ,1],      \"R\": [0, 1, 0],      \"S\": [0, 0, 0],      \"T\": [1],      \"U\": [0, 0, 1],      \"V\": [0, 0, 0, 1],      \"W\": [0, 1, 1],      \"X\": [1, 0, 0, 1],      \"Y\": [1, 0, 1, 1],      \"Z\": [1, 1, 0, 0],        \"1\": [0, 1, 1, 1, 1],      \"2\": [0, 0, 1, 1, 1],      \"3\": [0, 0, 0, 1, 1],      \"4\": [0, 0, 0, 0, 1],      \"5\": [0, 0, 0, 0, 0],      \"6\": [1, 0, 0, 0, 0],      \"7\": [1, 1, 0, 0, 0],      \"8\": [1, 1, 1, 0, 0],      \"9\": [1, 1, 1, 1, 0],      \"0\": [1, 1, 1, 1, 1]    }  }}"