package com.rrajath.jackdaw.notes;

import com.rrajath.jackdaw.BasePresenter;
import com.rrajath.jackdaw.BaseView;
import com.rrajath.jackdaw.data.model.Note;

import io.realm.RealmResults;

public interface NotesContract {

    interface View extends BaseView<Presenter> {

        void showNotes(RealmResults<Note> notes);

        void showAddNote();

        void showNoNotesMessage();

        void hideNoNotesMessage();

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void updateListItems();

        void showDebugToast(String text);

        void showNoteDetails(Note note);
    }

    interface Presenter extends BasePresenter {

        void loadNotes();

        void addNote();

        void removeItem(int position);

        boolean isListEmpty();

        void onNoteClick(Note note);
    }

}
