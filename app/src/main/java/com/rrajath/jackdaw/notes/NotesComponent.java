package com.rrajath.jackdaw.notes;

import com.rrajath.jackdaw.util.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = {
                NotesModule.class
        }
)
public interface NotesComponent {
    void inject(NotesActivity notesActivity);
}
