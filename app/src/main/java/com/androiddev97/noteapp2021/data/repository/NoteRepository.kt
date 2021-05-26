package com.androiddev97.noteapp2021.data.repository


import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import com.androiddev97.noteapp2021.data.dao.NoteDao
import com.androiddev97.noteapp2021.data.model.Note


class NoteRepository(private var noteDao: NoteDao) {

    suspend fun insert(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun update(note: Note) {
        noteDao.updateNote(note)
    }

    fun getCount(): LiveData<Int?>? {
        return noteDao.getCount()
    }

    suspend fun delete(note: Note) {
        noteDao.deleteNote(note)
    }

    fun searchData(searchQuery: String): Flow<List<Note>> {
        return noteDao.searchNoteList(searchQuery)
    }


    val readNote: LiveData<List<Note>> = noteDao.getAllNotes()


}
