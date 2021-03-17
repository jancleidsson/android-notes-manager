package com.jss.notesmanager.framework.di

import com.jss.core.repository.NoteRepository
import com.jss.core.usecase.*
import com.jss.notesmanager.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UsesCasesModule {

    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )
}