package com.example.gatherhall.helper

import com.example.gatherhall.auth.language.model.Language

class ModelData {
    companion object{
        fun getListOfLanguage(): List<Language>{
            val listOfLanguage = mutableListOf<Language>()
            listOfLanguage.add(Language(Constant.english))
            listOfLanguage.add(Language(Constant.中文))
            return listOfLanguage
        }
    }
}