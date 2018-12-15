package com.udacity.gradle.builditbigger.free;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.MyAsyncTask;
import com.udacity.gradle.builditbigger.MyAsyncTaskCallback;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.imageactivity.ImageActivity;
//import com.udacity.gradle.jokes.Joker;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {


//    private Joker joker ;
    private Button button;
    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_activity, container, false);
        button =  root.findViewById(R.id.fragment_btn);
        AdView mAdView =  root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask(new MyAsyncTaskCallback() {
                    @Override
                    public void OnDone(String result) {
                        Log.i("LLLL",result+"");
                        Intent intent = new Intent(getActivity(), ImageActivity.class);
                        intent.putExtra("JOKE", result);
                        startActivity(intent);
                        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                    }
                }).execute();
//                luanchLibraryActivity();
            }
        });
        return root;
    }


//
//    public void tellJoke(View view) {
////        Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(), joker.getJoke(), Toast.LENGTH_LONG).show();
//        luanchLibraryActivity(view);
//    }


//    public void luanchLibraryActivity() {
//        Intent intent = new Intent(getActivity(), ImageActivity.class);
//        intent.putExtra("JOKE", joker.getJoke());
//        startActivity(intent);
//    }

}
