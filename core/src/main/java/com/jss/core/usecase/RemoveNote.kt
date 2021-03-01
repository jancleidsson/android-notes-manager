package com.jss.core.usecase

import com.jss.core.data.Note
import com.jss.core.repository.NoteRepository

class RemoveNote (private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}