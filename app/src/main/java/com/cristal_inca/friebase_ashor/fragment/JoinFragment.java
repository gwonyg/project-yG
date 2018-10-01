package com.cristal_inca.friebase_ashor.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cristal_inca.friebase_ashor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class JoinFragment extends Fragment implements View.OnClickListener {

    TextView txt_join_email;
    TextView txt_join_password;

    FirebaseAuth mAuth;

    public JoinFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//        return inflater.inflate(R.layout.join_fragment,container,false);

        mAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.join_fragment, container, false);

        txt_join_email = view.findViewById(R.id.join_txt_email);
        txt_join_password = view.findViewById(R.id.join_txt_password);

        Button btn_join_ok = view.findViewById(R.id.join_btn_ok);
        btn_join_ok.setOnClickListener(this);

//        btn_join_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"회원가입 버튼 클릭",Toast.LENGTH_LONG).show();
//            }
//        });

        return view;

    }

    @Override
    public void onClick(View v) {

        String stremail = txt_join_email.getText().toString();
        String strpassword = txt_join_password.getText().toString();

        if (stremail.isEmpty()) {
            Toast.makeText(getContext(), "email은 반드시 써라", Toast.LENGTH_LONG).show();
            return;
        }
        if (strpassword.isEmpty()) {
            Toast.makeText(getContext(), "비밀번호는 반드시 써라", Toast.LENGTH_LONG).show();
            return;
        }
        // firebase에 같은 email이 등록되있는지 검사
        if(isNewUser(stremail)) {
            return;
        }
        // 없으면 firebase에 email과 비밀번호를 등록
        if(isNewUser(strpassword)) {
            return;
        }
    }
    // return값이 true이면 아직 등록되지 않은 email 등록

    boolean isNewUser = false ;
    private boolean isNewUser (String email) {

        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.isComplete()) {
                            Toast.makeText(getContext(), "이미 등록된 Email이야!!", Toast.LENGTH_LONG).show();
                            isNewUser = false;
                            return;
                        }
                        isNewUser = true;
                    }
                });
        return isNewUser;
    }

}