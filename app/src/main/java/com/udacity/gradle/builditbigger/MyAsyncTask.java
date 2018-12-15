package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.repackaged.com.google.common.annotations.VisibleForTesting;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.imageactivity.ImageActivity;

import java.io.IOException;

public class MyAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
//    private Context context;


//    public MyAsyncTask(Context context) {
//        this.context = context;
//    }


    public MyAsyncTask(MyAsyncTaskCallback myAsyncTaskCallback) {
        this.myAsyncTaskCallback = myAsyncTaskCallback;
    }

    private MyAsyncTaskCallback myAsyncTaskCallback ;

    @Override
    protected String doInBackground(Void... voids) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }
        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    @Override
    protected void onPostExecute(String result) {
        myAsyncTaskCallback.OnDone(result);
    }
}
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        Log.i("LLLL","PRE");
//        // code will executed before task start (main thread)
//    }
//
//    @VisibleForTesting
//    @Override
//    protected String doInBackground(Pair<Context, String>... params) {
//        if (myApiService == null) {  // Only do this once
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
//            // end options for devappserver
//
//            myApiService = builder.build();
//        }
//
//        context = params[0].first;
//        String name = params[0].second;
//
//        try {
//            return myApiService.sayHi(name).execute().getData();
//        } catch (IOException e) {
//            return e.getMessage();
//        }
//    }
//
