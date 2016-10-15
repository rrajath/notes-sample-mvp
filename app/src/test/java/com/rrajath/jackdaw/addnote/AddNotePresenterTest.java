package com.rrajath.jackdaw.addnote;

import com.rrajath.jackdaw.data.model.Note;
import com.rrajath.jackdaw.service.RealmService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddNotePresenterTest {
    @Mock
    private AddNoteContract.View addNoteViewMock;
    @Mock
    private RealmService realmServiceMock;

    private AddNotePresenter addNotePresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        addNotePresenter = new AddNotePresenter(addNoteViewMock, realmServiceMock);
    }

    @Test
    public void start() throws Exception {
        addNotePresenter.start();
        verify(addNoteViewMock).showAddNote();
    }

    @Test
    public void saveNote() throws Exception {
        Note note = new Note("title", "desc");
        addNotePresenter.saveNote(note);
        verify(realmServiceMock).saveNote(note);
        verify(addNoteViewMock).showNoteAddedToast();
    }

    @Test
    public void closeRealm() throws Exception {
        addNotePresenter.closeRealm();
        verify(realmServiceMock).closeRealm();
    }

}