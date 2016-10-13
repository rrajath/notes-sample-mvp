package com.rrajath.jackdaw.addnote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

import com.rrajath.jackdaw.BaseActivity;
import com.rrajath.jackdaw.JackdawApplication;
import com.rrajath.jackdaw.R;
import com.rrajath.jackdaw.data.model.Note;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNoteActivity extends BaseActivity implements AddNoteContract.View {

    @Inject
    AddNotePresenter presenter;

    @Bind(R.id.et_new_note_title)
    EditText etNewNoteTitle;
    @Bind(R.id.et_new_note_description)
    EditText etNewNoteDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addnote);
        ButterKnife.bind(this);

        presenter.start();
    }

    @Override
    protected void setupActivityComponent() {
        JackdawApplication.get(this)
                .getAppComponent()
                .plus(new AddNoteModule(this))
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showAddNote() {

    }

    @Override
    public void showNoteAddedToast() {
        Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void setPresenter(AddNoteContract.Presenter presenter) {
        this.presenter = (AddNotePresenter) presenter;
    }

    @OnClick(R.id.fabSaveNote)
    public void saveNote() {
        Note note = new Note();
        note.title = etNewNoteTitle.getText().toString();
        note.description = etNewNoteDescription.getText().toString();
        presenter.saveNote(note);
    }
}
