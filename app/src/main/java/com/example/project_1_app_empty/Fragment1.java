package com.example.project_1_app_empty;

import android.content.Intent;
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
    private ClickListener clkListener;

    public Fragment1(){
        super();
        mPhonebook = new ArrayList<PhoneBook>();
        clkListener = new ClickListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button button;
        View view;
        RecyclerView recyclerView;
        RecyclerAdapter recyclerAdapter;

        view = inflater.inflate(R.layout.fragment1, container, false);
        button = view.findViewById(R.id.button_f1);
        button.setOnClickListener(clkListener);

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

    public class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //Toast.makeText(getContext(), "새 연락처를 생성합니다", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(),AddPhoneActivity.class);
            startActivity(intent);
        }
    }
}
