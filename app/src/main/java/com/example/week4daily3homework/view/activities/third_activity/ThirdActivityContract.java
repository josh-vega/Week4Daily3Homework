package com.example.week4daily3homework.view.activities.third_activity;

import com.google.firebase.auth.FirebaseUser;

public interface ThirdActivityContract {
    void displayFirebase(FirebaseUser user);
    void displayFacebook(String id);
    void failure();
}
