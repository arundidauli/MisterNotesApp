package info.androidhive.rxjavaretrofit.network;

import info.androidhive.rxjavaretrofit.network.model.Note;

public interface NoteListener {
    void delete(String note);

    void update(Note note);
}
