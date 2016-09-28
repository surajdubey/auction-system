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

    private Context mContext = this;

    private SignupPresenter mSignupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mNameEditText = (EditText) findViewById(R.id.etName);
        mUsernameEditText = (EditText) findViewById(R.id.username_edittext);
        mPasswordEditText = (EditText) findViewById(R.id.password_edittext);
        Button signUpButton = (Button) findViewById(R.id.btnSignUp);
        Button loginButton = (Button) findViewById(R.id.login_button);

        setSignupPresenter();

        if (signUpButton != null) {
            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSignupPresenter.signup();
                }
            });
        }

        if (loginButton != null) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSignupPresenter.login();
                }
            });
        }
    }

    private void setSignupPresenter() {
        SignupView signupView = this;
        SignupInteractor signupInteractor = new SignupInteractor(mContext);

        mSignupPresenter = new SignupPresenter(signupView, signupInteractor);
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
        Intent intent = new Intent(mContext, ViewItemsInAuctionActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showNameLengthInvalidError() {
        mNameEditText.setError(getString(R.string.name_length_invalid_error));
    }

    @Override
    public String getName() {
        return mNameEditText.getText().toString();
    }

}
