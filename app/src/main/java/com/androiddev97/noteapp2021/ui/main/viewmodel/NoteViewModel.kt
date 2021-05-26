package com.androiddev97.noteapp2021.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev97.noteapp2021.data.dao.NoteDao
import com.androiddev97.noteapp2021.data.model.Note
import com.androiddev97.noteapp2021.data.repository.NoteRepository
import com.androiddev97.noteapp2021.data.room.NoteDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch


class NoteViewModel(application: Application) :
    AndroidViewModel(application) {
    private val noteRepository: NoteRepository
    val readAllNotes: LiveData<List<Note>>


    init {
        val noteDao = NoteDataBase.getDatabase(application).noteDao()
        noteRepository = NoteRepository(noteDao)
        readAllNotes = noteRepository.readNote


    }
    fun searchNote(searchQuery:String):LiveData<List<Note>>{
        return noteRepository.searchData(searchQuery).asLiveData()
    }


    fun insertData(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insert(note)
        }
    }

    fun deleteData(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.delete(note)
        }

    }

    fun updateData(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.update(note)
        }
    }

    fun getCount(): LiveData<Int?>? {
        return noteRepository.getCount()
    }


}