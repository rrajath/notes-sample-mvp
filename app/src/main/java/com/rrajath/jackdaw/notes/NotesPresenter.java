package com.rrajath.jackdaw.notes;

import com.rrajath.jackdaw.data.model.Note;
import com.rrajath.jackdaw.service.RealmService;

import javax.inject.Inject;

import io.realm.RealmResults;

public class NotesPresenter implements NotesContract.Presenter {

    private NotesContract.View notesView;
    private RealmService realmService;

    @Inject
    public NotesPresenter(NotesContract.View notesView, RealmService realmService) {
        this.notesView = notesView;
        this.realmService = realmService;
    }

    @Override
    public void loadNotes() {
        notesView.showLoadingIndicator();
        RealmResults<Note> notes = realmService.getAllNotes();
        if (notes.size() == 0) {
            notesView.showNoNotesMessage();
        } else {
            notesView.showNotes(notes);
        }
        notesView.hideLoadingIndicator();
    }

    @Override
    public void addNote() {
        notesView.showAddNote();
    }

    @Override
    public void removeItem(int position) {
        RealmResults<Note> notes = realmService.getAllNotes();
        Note note = notes.get(position);
        realmService.deleteNote(note);
        notesView.updateListItems();
    }

    @Override
    public boolean isListEmpty() {
        RealmResults<Note> notes = realmService.getAllNotes();
        return notes.isEmpty();
    }

    @Override
    public void onNoteClick(Note note) {
        notesView.showNoteDetails(note);
    }

    @Override
    public void start() {
        notesView.showNotes(realmService.getAllNotes());
    }
}
