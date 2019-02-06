package com.example.week4daily3homework.view.activities.main_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.week4daily3homework.R;
import com.example.week4daily3homework.view.activities.second_activity.SecondActivity;
import com.example.week4daily3homework.view.activities.third_activity.ThirdActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements MainActivityContract{
    MainActivityPresenter presenter;
    private FirebaseAuth fireAuth;
    CallbackManager callbackManager;
    EditText etEmail, etPassword;
    String email, password;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fireAuth = FirebaseAuth.getInstance();

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        presenter.verifyFacebookUpdate(loginResult.getAccessToken().getUserId());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("TAG", "onError: " + exception);
                    }
                });

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    @Override
    public void updateUI(FirebaseUser user) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    public void updateFacebookUI(String id) {
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("facebook", id);
        startActivity(intent);
    }

    public void onClick(View view) {
        email = etEmail.getText() != null ? etEmail.getText().toString() : "";
        password = etPassword.getText() != null ? etPassword.getText().toString() : "";

        if(!etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {

            switch (view.getId()) {
                case R.id.btnSignUp:
                    fireAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("TAG", "createUserWithEmail:success");
                                        FirebaseUser user = fireAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "Sign-Up failed. Try Again!", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                    break;
                case R.id.btnSignIn:
                    fireAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("TAG", "signInWithEmail:success");
                                        FirebaseUser user = fireAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("TAG", "signInWithEmail:failure", task.getException());
                                    }

                                    // ...
                                }
                            });
                    break;
            }
        } else {
            Toast.makeText(MainActivity.this, "Authentication failed. Please enter email and password.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
