package com.example.helpme.fragments;

import android.net.Uri;

import java.net.URI;

public class Worker {
    String name;
    String lastName;
    String id;
    String email;
    String facebook;
    String job;
    String phone;
    Uri image;

    public Worker(String name, String lastName , String id, String email, String facebook, String job, String phone)
    {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.email = email;
        this.facebook = facebook;
        this.job = job;
        this.phone = phone;
        this.image = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public Worker(){

    }
}
