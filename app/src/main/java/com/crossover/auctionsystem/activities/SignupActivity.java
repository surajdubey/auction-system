package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.interactor.SignupInteractor;
import com.crossover.auctionsystem.presenter.SignupPresenter;
import com.crossover.auctionsystem.view.SignupView;


public class SignupActivity extends AppCompatActivity implements SignupView {


    //EditText for entering name
    private EditText mNameEditText;

    //EditText for entering username
    private EditText mUsernameEditText;

    //EditText for entering password
    private EditText mPasswordEditText;

    //sign up Button
    private Button mSignUpButton;

    //login Button
    private Button mLoginButton;

    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mNameEditText = (EditText) findViewById(R.id.etName);
        mUsernameEditText = (EditText) findViewById(R.id.username_edittext);
        mPasswordEditText = (EditText) findViewById(R.id.password_edittext);
        mSignUpButton = (Button) findViewById(R.id.btnSignUp);
        mLoginButton = (Button) findViewById(R.id.login_button);

        SignupView signupView = this;
        SignupInteractor signupInteractor = new SignupInteractor(mContext);

        final SignupPresenter signupPresenter = new SignupPresenter(signupView, signupInteractor);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupPresenter.signup();
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupPresenter.login();
            }
        });

    }


    @Override
    public String getUsername() {
        return mUsernameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordEditText.getText().toString();
    }

    @Override
    public void showUsernameLengthInvalidError() {
        mUsernameEditText.setError(getString(R.string.username_length_invalid_error));
    }

    @Override
    public void showPasswordLengthInvalidError() {
        mPasswordEditText.setError(getString(R.string.password_length_invalid_error));
    }

    @Override
    public void showUserExistsError() {
        mUsernameEditText.setError(getString(R.string.user_exists_error));
    }

    @Override
    public void startItemsAvailableForBidActivity() {
        Intent intent = new Intent(mContext, ItemsAvailableForBidActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showNameLengthInvalidError() {

    }

    @Override
    public String getName() {
        return mNameEditText.getText().toString();
    }

}
