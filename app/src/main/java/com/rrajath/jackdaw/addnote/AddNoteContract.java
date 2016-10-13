package com.rrajath.jackdaw.addnote;

import com.rrajath.jackdaw.BasePresenter;
import com.rrajath.jackdaw.BaseView;
import com.rrajath.jackdaw.data.model.Note;

public interface AddNoteContract {

    interface View extends BaseView<Presenter> {
        void showAddNote();
        void showNoteAddedToast();
    }

    interface Presenter extends BasePresenter {
        void saveNote(Note note);
        void closeRealm();
    }


}
