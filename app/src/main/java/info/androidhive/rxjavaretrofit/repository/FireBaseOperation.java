package info.androidhive.rxjavaretrofit.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import info.androidhive.rxjavaretrofit.utils.Constant;


public abstract class FireBaseOperation {


    /**
     * Create data in firebase
     *
     * @param databaseReference
     * @param model
     * @param callBack
     */
    protected void insertData(final DatabaseReference databaseReference, final Object model, final CallBack callBack) {
        databaseReference.keepSynced(true);
        databaseReference.setValue(model, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    callBack.onSuccess(Constant.SUCCESS);
                } else {
                    callBack.onError(databaseError);
                }
            }
        });
    }

    /**
     * Offline Update data in firebase
     *
     * @param databaseReference
     * @param pushKey
     * @param model
     */
    protected final void fireBaseOfflineUpdate(final DatabaseReference databaseReference, final String pushKey, final Object model) {
        try {
            databaseReference.keepSynced(true);
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put(pushKey, model);
            databaseReference.updateChildren(stringObjectMap);
        } catch (Exception e) {
            e.printStackTrace();
            //ExceptionUtil.errorMessage("Method: fireBaseOfflineCreateAndUpdate", "Class: FirebaseTemplateRepository", e);
        }
    }

    /**
     * Insert offline data on FireBase
     *
     * @param databaseReference
     * @param model
     */
    protected final void fireBaseOfflineCreate(final DatabaseReference databaseReference, final Object model) {
        try {
            databaseReference.keepSynced(true);
            databaseReference.setValue(model);
        } catch (Exception e) {
            e.printStackTrace();
            // ExceptionUtil.errorMessage("Method: fireBaseOfflineCreateAndUpdate", "Class: FirebaseTemplateRepository", e);
        }
    }

    /**
     * Read data from firebase
     *
     * @param query
     * @param callback
     * @return
     */
    protected final ValueEventListener fireBaseDataChangeListener(final Query query, final CallBack callback) {
        query.keepSynced(true);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                callback.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError(databaseError);
            }
        };
        query.addValueEventListener(valueEventListener);
        return valueEventListener;
    }

    /**
     * Read data from firebase
     *
     * @param query
     * @param callback
     */
    protected final void fireBaseReadData(final Query query, final CallBack callback) {
        query.keepSynced(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
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

    /**
     * Delete data from firebase
     *
     * @param databaseReference
     * @param callback
     */
    protected final void fireBaseDelete(final DatabaseReference databaseReference, final CallBack callback) {
        databaseReference.keepSynced(true);
        databaseReference.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    callback.onSuccess(null);
                } else {
                    callback.onError(databaseError);
                }
            }
        });
    }
}
