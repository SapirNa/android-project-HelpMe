package com.example.helpme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.helpme.fragments.FragmentRegWorker;
import com.example.helpme.fragments.Worker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

    }

    //function to register new user
    public void funcCstReg(View view){

        String email = ((EditText) findViewById(R.id.setEmailReg)).getText().toString();
        String password = ((EditText) findViewById(R.id.setPassReg)).getText().toString();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "All fields must be completed !", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //check = true;
                                Toast.makeText(MainActivity.this, "Registration Successfully!", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentResults);
                            } else {
                               // check = false;
                                Toast.makeText(MainActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }

        }

    //function to login
    public void funcLogIn(View view){

        String email = ((EditText) findViewById(R.id.setEmailLogIn)).getText().toString();
        String password = ((EditText) findViewById(R.id.setPassLogIn)).getText().toString();


        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(MainActivity.this, "All fields must be completed !", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "log in Successfully!", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentResults);


                            } else {
                                Toast.makeText(MainActivity.this, "log in failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }

    }

    //function to register new worker
    public void funcWrkReg(View view){

        String email = ((EditText) findViewById(R.id.setWorkerEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.setWorkerPass)).getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(MainActivity.this, "All fields must be completed !", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).navigate(R.id.action_fragmentRegWorker_to_fragmentResults);

                            } else {
                                Toast.makeText(MainActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



        }

    }
}