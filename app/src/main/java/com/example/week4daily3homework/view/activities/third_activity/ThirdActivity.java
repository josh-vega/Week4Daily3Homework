package com.example.week4daily3homework.view.activities.third_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week4daily3homework.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ThirdActivity extends AppCompatActivity implements ThirdActivityContract {
    ThirdActivityPresenter presenter;
    FirebaseUser user, loggedInUser;
    FirebaseAuth auth;
    TextView tvThirdName, tvThirdEmail;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        presenter = new ThirdActivityPresenter(this);
        auth = FirebaseAuth.getInstance();
        loggedInUser = auth.getCurrentUser();

        user = getIntent().getParcelableExtra("user");
        id = getIntent().getStringExtra("facebook");

        tvThirdName = findViewById(R.id.tvThirdName);
        tvThirdEmail = findViewById(R.id.tvThirdEmail);

        presenter.display(user, id);
    }

    @Override
    public void displayFirebase(FirebaseUser user) {
        tvThirdName.setText(loggedInUser.getDisplayName());
        tvThirdEmail.setText(user.getEmail());
    }

    @Override
    public void displayFacebook(String id) {
        tvThirdName.setText("User Id: " + id);
    }

    @Override
    public void failure() {
        Toast.makeText(this, "Uh oh! Something went wrong...", Toast.LENGTH_SHORT).show();
    }
}
