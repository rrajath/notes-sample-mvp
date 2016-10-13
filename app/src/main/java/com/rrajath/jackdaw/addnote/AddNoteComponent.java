package com.rrajath.jackdaw.addnote;

import com.rrajath.jackdaw.util.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent (
        modules = {
                AddNoteModule.class
        }
)
public interface AddNoteComponent {
    void inject(AddNoteActivity addNoteActivity);
}
