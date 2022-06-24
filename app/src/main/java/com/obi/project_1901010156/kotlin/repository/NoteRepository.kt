package com.obi.project_1901010156.kotlin.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.obi.project_1901010156.kotlin.database.Note
import com.obi.project_1901010156.kotlin.database.NoteDao
import com.obi.project_1901010156.kotlin.database.NoteDatabase
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNoteDao: NoteDao
    private val exectorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteDatabase.getDatabase(application)
        mNoteDao = db.noteDao()
    }

    fun getAllNotes(): LiveData<List<Note>> = mNoteDao.getNote()

    fun insert(note: Note) {
        exectorService.execute { mNoteDao.insert(note) }
    }
    fun delete(note: Note) {
        exectorService.execute { mNoteDao.delete(note) }
    }
    fun update(note: Note) {
        exectorService.execute { mNoteDao.update(note) }
    }
}