package com.example.week4daily3homework.view.activities.main_activity;

import com.google.firebase.auth.FirebaseUser;

public class MainActivityPresenter {
    MainActivityContract contract;

    public MainActivityPresenter(MainActivityContract contract) {
        this.contract = contract;
    }

    public void verifyFirebaseUpdate(FirebaseUser user){
        contract.updateUI(user);
    }

    public void verifyFacebookUpdate(String userId){
        contract.updateFacebookUI(userId);
    }
}
