package com.rrajath.jackdaw.notedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rrajath.jackdaw.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NoteDetailActivity extends AppCompatActivity {

    private String noteTitle;
    private String noteDescription;

    @Bind(R.id.title_text)
    TextView tvNoteTitle;
    @Bind(R.id.desc_text)
    TextView tvNoteDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        ButterKnife.bind(this);

        noteTitle = getIntent().getStringExtra("noteTitle");
        noteDescription = getIntent().getStringExtra("noteDesc");

        tvNoteTitle.setText(noteTitle);
        tvNoteDesc.setText(noteDescription);
    }


}
