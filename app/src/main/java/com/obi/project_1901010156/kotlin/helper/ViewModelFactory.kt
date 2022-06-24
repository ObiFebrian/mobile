package com.obi.project_1901010156.kotlin.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.obi.project_1901010156.kotlin.viewmodel.MainViewModel
import com.obi.project_1901010156.kotlin.viewmodel.NoteAddViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(private val mAplication : Application) : ViewModelProvider.Factory{
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(mAplication) as T
        }
        if (modelClass.isAssignableFrom(NoteAddViewModel::class.java)){
            return NoteAddViewModel(mAplication) as T
        }

        throw IllegalArgumentException("view Model note found")
    }
}