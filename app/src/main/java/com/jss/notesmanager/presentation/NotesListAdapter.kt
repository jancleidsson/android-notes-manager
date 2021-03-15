package com.jss.notesmanager.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jss.core.data.Note
import com.jss.notesmanager.R
import com.jss.notesmanager.databinding.NoteItemBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesListAdapter(private val notes: ArrayList<Note>, private val action: ListAction) : RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = NoteItemBinding.bind(view)
        private val context = view.context

        fun bind(note: Note) {
            val lastUpdateFormat = SimpleDateFormat(context.getString(R.string.last_updated_date_format))
            val lastUpdateDate = Date(note.updateTime)
            binding.noteItemLastUpdated.text = "${context.getString(R.string.last_updated)} ${lastUpdateFormat.format(lastUpdateDate)}"
            binding.noteItemTitle.text = note.title
            binding.noteItemContent.text = note.content
            binding.noteItem.setOnClickListener {
                action.onClick(note.id)
            }
        }
    }
}