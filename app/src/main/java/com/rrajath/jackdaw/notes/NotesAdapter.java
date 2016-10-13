package com.rrajath.jackdaw.notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rrajath.jackdaw.R;
import com.rrajath.jackdaw.data.model.Note;

import io.realm.RealmResults;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private RealmResults<Note> notes;
    private NotesActivity.NotesItemListener notesItemListener;

    public NotesAdapter(NotesActivity.NotesItemListener notesItemListener) {
        this.notesItemListener = notesItemListener;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.title.setText(note.title);
        holder.description.setText(note.description);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(final RealmResults<Note> notes) {
        this.notes = notes;
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        public NotesViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note note = notes.get(getAdapterPosition());
                    NotesAdapter.this.notesItemListener.onNoteClick(note);
                }
            });
        }
    }
}
