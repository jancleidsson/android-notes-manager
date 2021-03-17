package com.jss.notesmanager.framework.di

import android.app.Application
import com.jss.core.repository.NoteRepository
import com.jss.notesmanager.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}