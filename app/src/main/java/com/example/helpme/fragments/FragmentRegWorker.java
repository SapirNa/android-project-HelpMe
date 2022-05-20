package com.example.helpme.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.helpme.MainActivity;
import com.example.helpme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class FragmentRegWorker extends Fragment implements View.OnClickListener {

    ImageView imageUpload;
    private static final int resultLoadImage = 1;
    Uri Selected;
    String workerName;
    String workerLastName;
    String workerId;
    String workerEmail;
    String workerFacebook;
    String workerJob;
    String workerPhone;
    MainActivity main = (MainActivity) getActivity();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reg_worker, container, false);
        imageUpload = (ImageView) view.findViewById(R.id.workerImage);//for image upload
        Button btnUpimg = view.findViewById(R.id.buttonUploadImage);//for image upload
        Button btnWrkReg = view.findViewById(R.id.buttonWrkReg); //button that accept the worker register action
        MainActivity main = (MainActivity) getActivity();
        Spinner spinner = (Spinner) view.findViewById(R.id.setWorkerjob);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.jobs));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);


        //when worker want to access his register
        btnWrkReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                workerName     = ((EditText) view.findViewById(R.id.setWorkerName)).getText().toString();
                workerLastName = ((EditText) view.findViewById(R.id.setWorkerLastName)).getText().toString();
                workerId       = ((EditText) view.findViewById(R.id.setWorkerId)).getText().toString();
                workerEmail    = ((EditText) view.findViewById(R.id.setWorkerEmail)).getText().toString();
                workerFacebook = ((EditText) view.findViewById(R.id.setWorkerFacebook)).getText().toString();
                workerPhone = ((EditText) view.findViewById(R.id.setWorkerPhone)).getText().toString();

                workerJob = spinner.getSelectedItem().toString();

                //if the worker doesn't complete all the fileds
                if(workerName.isEmpty() || workerLastName.isEmpty() || workerId.isEmpty() || workerEmail.isEmpty() || workerFacebook.isEmpty() || workerPhone.isEmpty() || Selected == null){
                    Toast.makeText(getContext(),"you have one or more field empty!",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{

                    main.funcWrkReg(view);
                    Worker worker = setWorkerData(workerName, workerLastName, workerId, workerEmail, workerFacebook,workerJob,workerPhone);
                }

        }
        });
        btnUpimg.setOnClickListener(this);//for image upload
        imageUpload.setOnClickListener(this);//for image upload


        return view;
    }

    //**************************************functions*******************************

    //add worker to database
    public Worker setWorkerData(String workerName, String workerLastName, String workerId, String workerEmail, String workerFacebook,String workerJob,String workerPhone) {

        Worker worker = new Worker(workerName, workerLastName, workerId, workerEmail, workerFacebook,workerJob,workerPhone);
        uploadToStorage();
        //worker.setImage(add_url_image(worker));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myWorkerRef = database.getReference("worker").child(worker.getId());

        myWorkerRef.setValue(worker);
        return worker;
        }


    //for image choseing
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonUploadImage:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                super.startActivityForResult(galleryIntent, resultLoadImage);
                break;

        }
    }

    //for image upload to app
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Create a Cloud Storage reference from the app
        if (requestCode == resultLoadImage && resultCode == main.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            Selected = selectedImage;

            imageUpload.setImageURI(selectedImage);
        }
    }

    //for upload the image to storage
    public void uploadToStorage(){

        StorageReference storageRef = storage.getReference();

        StorageReference workersRef = storageRef.child(workerId+".jpg");

//        StorageReference workerImagesRef = storageRef.child("images/"+workerId+".jpg");

        imageUpload.setDrawingCacheEnabled(true);
        imageUpload.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageUpload.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = workersRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }

    public Uri add_url_image(Worker worker){
        StorageReference mImageStorage = FirebaseStorage.getInstance().getReference();
        StorageReference ref = mImageStorage.child(worker.getId()+".jpg");

        return ref.getDownloadUrl().getResult();
//                .addOnCompleteListener(new OnCompleteListener<Uri>() {
//            @Override
//            public Uri onComplete(@NonNull Task<Uri> task) {
//                if (task.isSuccessful()) {
//                    Uri downUri = task.getResult();
//                    return downUri;
//                }
//            }
//        });


    }
}