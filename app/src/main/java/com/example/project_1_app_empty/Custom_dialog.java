package com.example.project_1_app_empty;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        TextView nameView,phoneView;
        nameView = view.findViewById(R.id.profile_name);
        phoneView = view.findViewById(R.id.profile_phone);

        nameView.setText(profile.getName());
        phoneView.setText(profile.getPhone());

        // 전화 버튼
        ImageView callView = view.findViewById(R.id.call);
        callView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                  if(profile.getPhone() != null && !profile.getPhone().equals("")){
                      PermissionApp permissionApp = (PermissionApp) getContext().getApplicationContext();
                      if(permissionApp.callPermission){
                          // 전화 기능 Intent
                          Intent intent = new Intent();
                          intent.setAction(Intent.ACTION_CALL);
                          intent.setData(Uri.parse("tel:"+profile.getPhone()));
                          startActivity(intent);
                      }else{
                          Toast t = Toast.makeText(getContext(),"권한이 없습니다!",Toast.LENGTH_SHORT);
                          t.show();
                      }
                }else{
                      Toast t = Toast.makeText(getContext(),"번호가 적절하지 않습니다!",Toast.LENGTH_SHORT);
                      t.show();
                  }
            }
        });

        // 메시지 버튼
        ImageView messageView = view.findViewById(R.id.message);
        messageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+profile.getPhone()));
                startActivity(intent);
            }
        });

        // 영상통화 버튼
        ImageView videoView = view.findViewById(R.id.video);
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+ profile.getPhone()));
                intent.putExtra("videocall",true);
                startActivity(intent);
            }
        });

        return builder.create();
    }
}
