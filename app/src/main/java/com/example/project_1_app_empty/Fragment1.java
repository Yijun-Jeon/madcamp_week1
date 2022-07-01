package com.example.project_1_app_empty;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button button;

        View view = inflater.inflate(R.layout.fragment1, container, false);
        button = view.findViewById(R.id.button_f1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "done", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


}
