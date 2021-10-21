package com.example.noted.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: Flow<List<Note>> = noteDao.getAlphabetizedNotes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert (note: Note){
        noteDao.insert(note)
    }
}