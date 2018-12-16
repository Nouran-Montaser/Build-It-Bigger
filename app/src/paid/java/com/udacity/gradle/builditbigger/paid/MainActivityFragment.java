package com.udacity.gradle.builditbigger.paid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.udacity.gradle.builditbigger.MyAsyncTask;
import com.udacity.gradle.builditbigger.MyAsyncTaskCallback;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.imageactivity.ImageActivity;


public class MainActivityFragment extends Fragment {


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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    }
                });
            }
        });
        return root;
    }

}