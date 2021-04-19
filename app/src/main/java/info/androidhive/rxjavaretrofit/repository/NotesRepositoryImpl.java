package info.androidhive.rxjavaretrofit.repository;

import android.app.Activity;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import info.androidhive.rxjavaretrofit.firebase.FirebaseRepository;
import info.androidhive.rxjavaretrofit.network.model.Note;
import info.androidhive.rxjavaretrofit.utils.Constant;

public class NotesRepositoryImpl extends FirebaseRepository implements NotesRepository {
    DatabaseReference databaseReference;
    private Activity activity;

    public NotesRepositoryImpl(Activity activity) {
        this.activity = activity;
        String uniqueId = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        databaseReference = FirebaseDatabase.getInstance().getReference(Constant.Note_MODEL).child(uniqueId);
    }

    @Override
    public void createNote(String note, final CallBack callback) {
        String key = databaseReference.push().getKey();
        Note note1 = new Note();
        note1.setId(key);
        note1.setNote(note);
        note1.setTimestamp(String.valueOf(System.currentTimeMillis()));

        assert key != null;
        databaseReference.child(key).setValue(note1, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                callback.onSuccess(Constant.SUCCESS);
            }
        });
    }

    @Override
    public void getNotes(final CallBack callback) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                callback.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError(databaseError);
            }
        });

    }

    @Override
    public void deleteNote(String id, final CallBack callback) {
        databaseReference.child(id).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                callback.onSuccess(Constant.SUCCESS);
            }
        });

    }

    @Override
    public void updateNote(String id, @NonNull HashMap map, final CallBack callback) {
        databaseReference.child(id).updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                callback.onSuccess(Constant.SUCCESS);
            }
        });
    }
}
