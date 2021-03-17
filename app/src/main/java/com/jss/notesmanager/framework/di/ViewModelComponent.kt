package com.jss.notesmanager.framework.di

import com.jss.notesmanager.framework.vm.NoteViewModel
import com.jss.notesmanager.framework.vm.NotesListViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UsesCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(notesListViewModel: NotesListViewModel)
}