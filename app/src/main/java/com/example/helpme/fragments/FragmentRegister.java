package com.example.helpme.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helpme.MainActivity;
import com.example.helpme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;


public class FragmentRegister extends Fragment {

    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mAuth = FirebaseAuth.getInstance();
        MainActivity main = (MainActivity) getActivity();

        Button btnBTLG = view.findViewById(R.id.buttonBackToLogIn);//button that back to log in page
        Button btnReg = view.findViewById(R.id.buttonCstReg);//button that accept the register action


        //function that call to function register from MainActivity
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText) view.findViewById(R.id.setEmailReg)).getText().toString();
                main.funcCstReg(view);
            }
        });

        //when want go back to login page
        btnBTLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_fragmentRegister_to_fragmentLogin);
            }

        });

        return view;
    }
}


