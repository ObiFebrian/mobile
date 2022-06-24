package com.obi.project_1901010156.kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.obi.project_1901010156.kotlin.database.Note
import com.obi.project_1901010156.kotlin.repository.NoteRepository

class MainViewModel(application: Application) : ViewModel() {
    private val mRepository : NoteRepository = NoteRepository(application)
    fun getNOte () :LiveData<List<Note>> = mRepository.getAllNotes()

}