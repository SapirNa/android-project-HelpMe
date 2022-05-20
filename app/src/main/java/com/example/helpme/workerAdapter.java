package com.example.helpme;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helpme.fragments.Worker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class workerAdapter extends RecyclerView.Adapter<workerAdapter.MyViewHolder> {

    Context context;

    ArrayList<Worker> list;
    public workerAdapter(Context context, ArrayList<Worker> list) {
        this.context = context;
        this.list    = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_result_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Worker worker = list.get(position);
        holder.firstName.setText(worker.getName());
        holder.lastName.setText(worker.getLastName());
        holder.facebook.setText(worker.getFacebook());
        holder.Phone.setText(worker.getPhone());
        holder.Job.setText(worker.getJob());
        holder.image.setImageURI(worker.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstName, lastName, facebook, Phone, Job;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.textViewFirstName);
            lastName = itemView.findViewById(R.id.textViewLastName);
            facebook = itemView.findViewById(R.id.textViewFacebook);
            Phone = itemView.findViewById(R.id.textViewPhone);
            Job = itemView.findViewById(R.id.textViewJob);
            image = itemView.findViewById(R.id.RecycleworkerImage);
        }
    }

}
