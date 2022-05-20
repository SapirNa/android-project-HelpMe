package com.example.helpme.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helpme.MainActivity;
import com.example.helpme.R;
import com.example.helpme.workerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentResults extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference database;
    workerAdapter MyAdapter;
    ArrayList<Worker> list;
    MainActivity main = (MainActivity) getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        recyclerView = view.findViewById(R.id.recycleViewJobs);
        database = FirebaseDatabase.getInstance().getReference("worker");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        list = new ArrayList<>();
        MyAdapter = new workerAdapter(getContext(), list);
        recyclerView.setAdapter(MyAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Worker worker = dataSnapshot.getValue(Worker.class);
                    list.add(worker);
                }
                MyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}