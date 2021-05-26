package com.androiddev97.noteapp2021.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddev97.noteapp2021.R
import com.androiddev97.noteapp2021.data.model.Note
import com.androiddev97.noteapp2021.ui.fragment.FragmentNote
import com.androiddev97.noteapp2021.ui.fragment.FragmentNoteDirections
import kotlinx.android.synthetic.main.custom_list_data.view.*
import kotlin.random.Random


class NoteAdapter(var clickListener: OnItemClickListener) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var note = emptyList<Note>()
    private var random = Random(5)

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_list_data, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val notesList = note[position]
        holder.itemView.text_view_title.text = notesList.title
        holder.itemView.text_view_date_time.text = notesList.date
        holder.itemView.text_view_des.text = notesList.description
        val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        holder.itemView.cardViewNotes.setCardBackgroundColor(color)
        holder.itemView.cardViewNotes.setOnClickListener {
            clickListener.ClickCard(notesList)
        }
        holder.itemView.imgTrash.setOnClickListener {
            clickListener.CLickItem(notesList)

        }
    }

    fun setData(notes: List<Note>) {
        this.note = notes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return note.size
    }

    interface OnItemClickListener {
        fun CLickItem(notes: Note)
        fun ClickCard(notes: Note)
    }


}