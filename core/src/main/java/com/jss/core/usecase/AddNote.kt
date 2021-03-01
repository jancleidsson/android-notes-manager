package com.jss.core.usecase

import com.jss.core.data.Note
import com.jss.core.repository.NoteRepository

class AddNote (private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}