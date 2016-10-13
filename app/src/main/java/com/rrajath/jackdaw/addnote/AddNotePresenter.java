package com.rrajath.jackdaw.addnote;

import com.rrajath.jackdaw.data.model.Note;
import com.rrajath.jackdaw.service.RealmService;

public class AddNotePresenter implements AddNoteContract.Presenter {

    private AddNoteContract.View addNoteView;
    private RealmService realmService;

    public AddNotePresenter(AddNoteContract.View addNoteView, RealmService realmService) {
        this.addNoteView = addNoteView;
        this.realmService = realmService;
    }

    @Override
    public void start() {
        addNoteView.showAddNote();
    }

    @Override
    public void saveNote(Note note) {
        realmService.saveNote(note);
        addNoteView.showNoteAddedToast();
    }

    @Override
    public void closeRealm() {
        realmService.closeRealm();
    }


}
