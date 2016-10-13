package com.rrajath.jackdaw;

import android.app.Application;

import com.rrajath.jackdaw.addnote.AddNoteComponent;
import com.rrajath.jackdaw.addnote.AddNoteModule;
import com.rrajath.jackdaw.main.MainActivityComponent;
import com.rrajath.jackdaw.main.MainActivityModule;
import com.rrajath.jackdaw.notes.NotesComponent;
import com.rrajath.jackdaw.notes.NotesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (
        modules = {
                AppModule.class,
                StorageModule.class
        }
)
public interface AppComponent {
    MainActivityComponent plus(MainActivityModule mainActivityModule);
    NotesComponent plus(NotesModule notesModule);
    AddNoteComponent plus(AddNoteModule addNoteModule);
    Application application();
}
