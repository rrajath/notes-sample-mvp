package com.rrajath.jackdaw.notes;

import com.rrajath.jackdaw.service.RealmService;
import com.rrajath.jackdaw.util.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class NotesModule {

    private final NotesContract.View notesView;

    public NotesModule(NotesContract.View view) {
        this.notesView = view;
    }

    @Provides
    NotesContract.View provideNotesContractView() {
        return notesView;
    }

    @Provides
    @ActivityScope
    NotesActivity provideNotesActivity() {
        return new NotesActivity();
    }

    @Provides
    @ActivityScope
    NotesPresenter provideNotesActivityPresenter(NotesContract.View view, RealmService realmService) {
        return new NotesPresenter(view, realmService);
    }
}
