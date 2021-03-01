package com.jss.core.usecase

import com.jss.core.repository.NoteRepository

class GetNote (private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}