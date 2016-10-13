package com.rrajath.jackdaw.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rrajath.jackdaw.BaseActivity;
import com.rrajath.jackdaw.JackdawApplication;
import com.rrajath.jackdaw.R;
import com.rrajath.jackdaw.addnote.AddNoteActivity;
import com.rrajath.jackdaw.data.model.Note;
import com.rrajath.jackdaw.notedetail.NoteDetailActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;

public class NotesActivity extends BaseActivity implements NotesContract.View {

    @Inject
    NotesPresenter presenter;

    @Bind(R.id.notes_list)
    RecyclerView rvNotesList;

    @Bind(R.id.add_note)
    FloatingActionButton fabAddNote;

    @Bind(R.id.notes_swipe_refresh)
    SwipeRefreshLayout srlNotes;

    @Bind(R.id.no_notes_message)
    TextView tvNoNotesMessage;

    private NotesAdapter adapter;
    private NotesItemListener notesItemListener = new NotesItemListener() {
        @Override
        public void onNoteClick(Note clickedNote) {
            presenter.onNoteClick(clickedNote);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);

        srlNotes.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    refreshContent();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                presenter.removeItem(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvNotesList);

        presenter.start();
    }

    private void refreshContent() throws InterruptedException {
        presenter.loadNotes();
    }

    @Override
    protected void setupActivityComponent() {
        JackdawApplication
                .get(this)
                .getAppComponent()
                .plus(new NotesModule(this))
                .inject(this);
    }

    @OnClick(R.id.add_note)
    public void onAddNoteClick() {
        presenter.addNote();
    }

    @Override
    public void showNotes(RealmResults<Note> notes) {
        hideNoNotesMessage();
        adapter = new NotesAdapter(notesItemListener);
        adapter.setNotes(notes);
        rvNotesList.setLayoutManager(new LinearLayoutManager(this));
        rvNotesList.setAdapter(adapter);
    }

    @Override
    public void showDebugToast(String text) {
        Toast.makeText(NotesActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoteDetails(Note note) {
        Intent intent = new Intent(this, NoteDetailActivity.class);
        intent.putExtra("noteTitle", note.title);
        intent.putExtra("noteDesc", note.description);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        presenter.loadNotes();
        super.onResume();
    }

    @Override
    public void showAddNote() {
        startActivity(new Intent(this, AddNoteActivity.class));
    }

    @Override
    public void showNoNotesMessage() {
        tvNoNotesMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoNotesMessage() {
        tvNoNotesMessage.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingIndicator() {
        srlNotes.setRefreshing(true);
    }

    @Override
    public void hideLoadingIndicator() {
        srlNotes.setRefreshing(false);
    }

    @Override
    public void updateListItems() {
        adapter.notifyDataSetChanged();
        if (presenter.isListEmpty()) {
            showNoNotesMessage();
        }
    }

    @Override
    public void setPresenter(NotesContract.Presenter presenter) {
        this.presenter = (NotesPresenter) presenter;
    }

    public interface NotesItemListener {
        void onNoteClick(Note clickedNote);
    }
}
