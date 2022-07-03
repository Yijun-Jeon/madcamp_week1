package com.example.project_1_app_empty;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Custom_dialog extends AppCompatDialogFragment {

    private PhoneBook profile;

    public Custom_dialog(PhoneBook data){
        super();
        profile = data;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.profile_dialog,null);
        builder.setView(view);
        builder.setIcon(R.drawable.person);
        builder.setTitle("프로필");
/*        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });*/

        TextView nameView,phoneView;
        nameView = view.findViewById(R.id.profile_name);
        phoneView = view.findViewById(R.id.profile_phone);

        nameView.setText(profile.getName());
        phoneView.setText(profile.getPhone());

        return builder.create();
    }
}
