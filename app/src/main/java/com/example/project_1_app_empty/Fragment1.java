package com.example.project_1_app_empty;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    private ArrayList<PhoneBook> mPhonebook;

    public Fragment1(){
        super();
        mPhonebook = new ArrayList<PhoneBook>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button button;
        View view;
        RecyclerView recyclerView;
        RecyclerAdapter recyclerAdapter;

        view = inflater.inflate(R.layout.fragment1, container, false);
        button = view.findViewById(R.id.button_f1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "done", Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(getActivity(),Addphone)
            }
        });

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        for(int i=1;i<=10;i++){
            mPhonebook.add(new PhoneBook(i+"번째 사람","000-0000-000"+i));
        }
        recyclerAdapter.setMyPhoneBook(mPhonebook);

        return view;
    }


}
