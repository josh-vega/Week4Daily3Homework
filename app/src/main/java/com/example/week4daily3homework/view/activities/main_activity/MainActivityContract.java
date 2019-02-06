package com.example.week4daily3homework.view.activities.main_activity;

import com.google.firebase.auth.FirebaseUser;

public interface MainActivityContract {
    public void updateUI(FirebaseUser user);
    public void updateFacebookUI(String id);
}
