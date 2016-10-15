package com.rrajath.jackdaw.notes;

import com.rrajath.jackdaw.data.model.Note;
import com.rrajath.jackdaw.service.RealmService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.realm.RealmList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NotesPresenterTest {
    @Mock
    private NotesContract.View notesViewMock;
    @Mock
    private RealmService realmServiceMock;

    private NotesPresenter notesPresenter;
    private RealmList<Note> notes;

    @Before
    public void setUp() throws Exception {
        notesPresenter = new NotesPresenter(notesViewMock, realmServiceMock);
        notes = new RealmList<>();
        notes.add(new Note("title", "desc"));
        when(realmServiceMock.getAllNotes()).thenReturn(notes);
    }

    @Test
    public void loadNotes() throws Exception {
        notesPresenter.loadNotes();
        verify(notesViewMock).showLoadingIndicator();
        verify(notesViewMock).showNotes(notes);
        verify(notesViewMock).hideLoadingIndicator();
    }

    @Test
    public void loadNotes_noNotes() {
        RealmList<Note> notes = new RealmList<>();
        when(realmServiceMock.getAllNotes()).thenReturn(notes);
        notesPresenter.loadNotes();
        verify(notesViewMock).showNoNotesMessage();
    }

    @Test
    public void addNote() throws Exception {
        notesPresenter.addNote();
        verify(notesViewMock).showAddNote();
    }

    @Test
    public void removeNote() throws Exception {
        int position = 0;
        Note note = notes.get(position);
        notesPresenter.removeNote(position);
        verify(realmServiceMock).deleteNote(note);
        verify(notesViewMock).updateListItems();
    }

    @Test
    public void isListEmpty_false() throws Exception {
        assertFalse(notesPresenter.isListEmpty());
    }

    @Test
    public void isListEmpty_true() {
        when(realmServiceMock.getAllNotes()).thenReturn(new RealmList<Note>());
        assertTrue(notesPresenter.isListEmpty());
    }

    @Test
    public void onNoteClick() throws Exception {
        Note note = notes.get(0);
        notesPresenter.onNoteClick(note);
        verify(notesViewMock).showNoteDetails(note);
    }

    @Test
    public void start() throws Exception {
        notesPresenter.start();
        verify(notesViewMock).showNotes(notes);
    }

}