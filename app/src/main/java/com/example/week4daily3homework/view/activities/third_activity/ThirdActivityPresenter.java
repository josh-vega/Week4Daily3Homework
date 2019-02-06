package com.example.week4daily3homework.view.activities.third_activity;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;

public class ThirdActivityPresenter {
    ThirdActivityContract contract;

    public ThirdActivityPresenter(ThirdActivityContract contract) {
        this.contract = contract;
    }

    public void display(FirebaseUser user, String id) {
        if(user!=null){
            contract.displayFirebase(user);
        } else if (!id.isEmpty()){
            contract.displayFacebook(id);
        } else {
            contract.failure();
        }
    }
}
