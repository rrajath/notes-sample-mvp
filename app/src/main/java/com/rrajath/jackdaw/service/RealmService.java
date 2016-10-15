package com.rrajath.jackdaw.service;

import com.rrajath.jackdaw.data.model.Note;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmService {

    private final Realm realm;

    public RealmService(Realm realm) {
        this.realm = realm;
    }

    public void closeRealm() {
        realm.close();
    }

    public RealmList<Note> getAllNotes() {
        RealmResults<Note> results = realm.where(Note.class).findAll();
        RealmList<Note> notes = new RealmList<>();
        notes.addAll(results.subList(0, results.size()));
        return notes;
    }

    public void saveNote(Note note) {
        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
    }

    public void deleteNote(Note note) {
        realm.beginTransaction();
        final RealmResults rows = realm.where(Note.class).equalTo("id", note.id).findAll();
        rows.deleteFromRealm(0);
        realm.commitTransaction();
    }
}
