package com.example.week4daily3homework.view.activities.second_activity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SecondActivityPresenter {
    SecondActivityContract contract;

    public SecondActivityPresenter(SecondActivityContract contract) {
        this.contract = contract;
    }

    public void updateUserInfo(FirebaseUser user, String name, String email, String password) {
        if(!name.isEmpty()){
            UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build();
            user.updateProfile(userProfileChangeRequest);
        }
        if(!email.isEmpty()){
            user.updateEmail(email);
        }
        if(!password.isEmpty()){
            user.updatePassword(password);
        }
        contract.update(user);
    }
}
