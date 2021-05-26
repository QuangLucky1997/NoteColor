package com.androiddev97.noteapp2021.ui.main.view

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.androiddev97.noteapp2021.ui.main.viewmodel.NoteViewModel

class NoteViewModelFactory(noteViewModel: NoteViewModel,application: Application): ViewModelProvider.AndroidViewModelFactory(application){

}