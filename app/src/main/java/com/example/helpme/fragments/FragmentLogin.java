package com.example.helpme.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.helpme.MainActivity;
import com.example.helpme.R;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentLogin extends Fragment {

    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mAuth = FirebaseAuth.getInstance();
        MainActivity main = (MainActivity) getActivity();

        Button btnGTReg = view.findViewById(R.id.buttonGTRegCustomer);//button that transfer to register page
        Button btnLogIn = view.findViewById(R.id.buttonLogIn);//button that accept the login action
        Button btnGTRegWrk = view.findViewById(R.id.buttonGTRegWorker);//button that transfer to register worker page

        //function that call to function login from MainActivity
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.funcLogIn(v);
            }
        });

        //when customer want to register
        btnGTReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentRegister);
            }
        });

        //when worker want to register
        btnGTRegWrk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentRegWorker);
            }
        });


        return view;
    }

}



