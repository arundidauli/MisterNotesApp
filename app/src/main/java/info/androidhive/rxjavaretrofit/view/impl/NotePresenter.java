package info.androidhive.rxjavaretrofit.view.impl;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

import java.util.HashMap;

import info.androidhive.rxjavaretrofit.repository.CallBack;
import info.androidhive.rxjavaretrofit.repository.NotesRepositoryImpl;

public class NotePresenter extends CallBack {

    private ProgressBar progressBar;
    private Activity activity;

    public NotePresenter(ProgressBar progressBar, Activity activity) {
        this.progressBar = progressBar;
        this.activity = activity;
    }

    public void createNote(String note) {
        progressBar.setVisibility(View.VISIBLE);
        new NotesRepositoryImpl(activity).createNote(note, this);
    }

    public void getNotes() {
        progressBar.setVisibility(View.VISIBLE);
        new NotesRepositoryImpl(activity).getNotes(this);
    }

    public void delNotes(String id) {
        progressBar.setVisibility(View.VISIBLE);
        new NotesRepositoryImpl(activity).deleteNote(id, this);
    }

    public void delNotes(String id, HashMap hashMap) {
        progressBar.setVisibility(View.VISIBLE);
        new NotesRepositoryImpl(activity).updateNote(id, hashMap, this);
    }

    @Override
    public void onSuccess(Object object) {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onError(Object object) {
        progressBar.setVisibility(View.GONE);

    }
}
