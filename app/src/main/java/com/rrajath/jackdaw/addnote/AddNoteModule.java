package com.rrajath.jackdaw.addnote;

import com.rrajath.jackdaw.service.RealmService;
import com.rrajath.jackdaw.util.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AddNoteModule {

    AddNoteContract.View addNoteView;

    public AddNoteModule(AddNoteContract.View addNoteView) {
        this.addNoteView = addNoteView;
    }

    @Provides
    @ActivityScope
    AddNoteActivity provideAddNoteActivity() {
        return new AddNoteActivity();
    }

    @Provides
    @ActivityScope
    AddNoteContract.View provideAddNoteView() {
        return addNoteView;
    }

    @Provides
    @ActivityScope
    AddNotePresenter provideAddNotePresenter(RealmService realmService) {
        return new AddNotePresenter(addNoteView, realmService);
    }
}
