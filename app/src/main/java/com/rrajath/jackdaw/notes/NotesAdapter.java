package com.rrajath.jackdaw.notes;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rrajath.jackdaw.R;
import com.rrajath.jackdaw.data.model.Note;
import com.rrajath.jackdaw.util.ColorConstants;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import io.realm.RealmList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private RealmList<Note> notes;
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
        holder.icon.setText(getFirstChar(note));
        holder.icon.setBackgroundResource(R.drawable.circle);
        Drawable background = holder.icon.getBackground();
        GradientDrawable shapeDrawable = (GradientDrawable) background;
        shapeDrawable.setColor(Color.parseColor(getRandomColor()));
    }

    @NonNull
    private String getFirstChar(Note note) {
        return note.title.substring(0,1);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(final RealmList<Note> notes) {
        this.notes = notes;
    }

    private String getRandomColor() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add(ColorConstants.LIST_ITEM_COLOR_1);
        colors.add(ColorConstants.LIST_ITEM_COLOR_2);
        colors.add(ColorConstants.LIST_ITEM_COLOR_3);
        colors.add(ColorConstants.LIST_ITEM_COLOR_4);
        colors.add(ColorConstants.LIST_ITEM_COLOR_5);
        int random = ThreadLocalRandom.current().nextInt(0, colors.size());
        return colors.get(random);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView icon;

        public NotesViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            icon = (TextView) itemView.findViewById(R.id.list_icon);
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
