package info.androidhive.rxjavaretrofit.app;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;

public class MySingleton extends Application {
    public static final String TAG = MySingleton.class.getSimpleName();
    private static MySingleton instance;

    private static Context ctx;
    private static MySingleton mInstance;

    public MySingleton() {

    }

    public static synchronized MySingleton getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        mInstance = this;

    }


}
