package com.jss.core.usecase

import com.jss.core.repository.NoteRepository

class GetAllNotes (private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAllNotes()
}