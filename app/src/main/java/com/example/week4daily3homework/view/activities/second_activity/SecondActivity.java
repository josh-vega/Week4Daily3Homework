package com.example.week4daily3homework.view.activities.second_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.week4daily3homework.R;
import com.example.week4daily3homework.view.activities.third_activity.ThirdActivity;
import com.google.firebase.auth.FirebaseUser;

public class SecondActivity extends AppCompatActivity implements SecondActivityContract {
    SecondActivityPresenter presenter;
    FirebaseUser firebaseUser;
    EditText etSecondName, etSecondEmail, etSecondPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        presenter = new SecondActivityPresenter(this);

        firebaseUser = getIntent().getParcelableExtra("user");

        etSecondName = findViewById(R.id.etSecondName);
        etSecondEmail = findViewById(R.id.etSecondEmail);
        etSecondPassword = findViewById(R.id.etSecondPassword);
    }

    public void onClick(View view) {
        if(!etSecondName.getText().toString().isEmpty()) {
            presenter.updateUserInfo(firebaseUser, etSecondName.getText().toString(), etSecondEmail.getText().toString(), etSecondPassword.getText().toString());
        } else {
            Toast.makeText(this,"Enter name of user.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void update(FirebaseUser user) {
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
