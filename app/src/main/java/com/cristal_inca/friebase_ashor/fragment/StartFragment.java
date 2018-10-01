package com.cristal_inca.friebase_ashor.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cristal_inca.friebase_ashor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartFragment extends Fragment {
    TextView txt_massage;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    public StartFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FirebaseAuth.getInstance().signOut();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
//        return inflater.inflate(R.layout.start_fragment,
//                container,false);

        View view = inflater.inflate(R.layout.start_fragment,
                container, false);

        txt_massage = view.findViewById(R.id.start_message);

        mUser = mAuth.getCurrentUser();

        if (mUser != null) {
            txt_massage.setText("로그인:" + mUser.getEmail());
        }
        return view ;
    }

}