package com.jss.notesmanager.framework

import android.content.Context
import com.jss.core.data.Note
import com.jss.core.repository.NoteDataSource
import com.jss.notesmanager.framework.db.DatabaseService
import com.jss.notesmanager.framework.db.NoteEntity

class RoomNoteDataSource (context: Context): NoteDataSource {
    private val noteDao = DatabaseService.getInstance(context).noteDao()
    override suspend fun add(note: Note) = noteDao.addNodeEntity(NoteEntity.fromNote(note))
    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()
    override suspend fun getAll(): List<Note> = noteDao.getAllNotesEntity().map { it.toNote() }
    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}