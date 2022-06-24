package com.obi.project_1901010156.kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.obi.project_1901010156.kotlin.database.Note
import com.obi.project_1901010156.kotlin.repository.NoteRepository

class NoteAddViewModel (application: Application) : ViewModel() {
    private val mNoteRepository : NoteRepository = NoteRepository(application)

    fun insert(note : Note){
        mNoteRepository.insert(note)
    }
    fun delete(note: Note){
        mNoteRepository.delete(note)
    }
    fun update(note: Note){
        mNoteRepository.update(note)
    }
}