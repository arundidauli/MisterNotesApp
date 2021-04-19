package info.androidhive.rxjavaretrofit.repository;

import java.util.HashMap;

public interface NotesRepository {

    void createNote(String note, CallBack callback);

    void getNotes(CallBack callback);

    void deleteNote(String id, CallBack callback);

    void updateNote(String id, HashMap map, CallBack callback);

}
