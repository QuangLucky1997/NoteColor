package com.androiddev97.noteapp2021.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddev97.noteapp2021.R
import com.androiddev97.noteapp2021.data.model.Note
import com.androiddev97.noteapp2021.ui.adapter.NoteAdapter
import com.androiddev97.noteapp2021.ui.main.viewmodel.NoteViewModel
import com.example.awesomedialog.*
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    NoteAdapter.OnItemClickListener {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        setUpRecycleView()
        getAllNotes()
        searchControl()
        // setSupportActionBar(toolbarSearch)
    }


    private fun setUpRecycleView() {
        noteAdapter = NoteAdapter(this)
        recycleViewSearch.adapter = noteAdapter
        recycleViewSearch.layoutManager = LinearLayoutManager(
            this
        )
    }

    private fun getAllNotes() {
        noteViewModel.readAllNotes.observe(this, { notes ->
            noteAdapter.setData(notes)
        })
    }

    private fun searchControl() {
        val searchView = searchNote
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        noteViewModel.searchNote(searchQuery).observe(this, { list ->
            list.let {
                noteAdapter.setData(it)
            }
        })
    }

    override fun CLickItem(notes: Note) {
        AwesomeDialog.build(this).title("Delete Note!!").body(
            "\n" +
                    "Are you sure to delete this note?\n"
        ).icon(R.drawable.bin)
            .onPositive("Yes") {
                noteViewModel.deleteData(notes)
            }.onNegative("Cancel")
            {

            }
    }

    override fun ClickCard(notes: Note) {
        TODO("Not yet implemented")
    }
}