package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.interactor.LoginInteractor;
import com.crossover.auctionsystem.presenter.LoginPresenter;
import com.crossover.auctionsystem.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    //EditText for entering username
    private EditText mUsernameEditText;

    //EditText for entering password
    private EditText mPasswordEditText;

    //Login Button
    private Button mLoginButton;

    //sign up Button
    private Button mSignUpButton;

    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameEditText = (EditText) findViewById(R.id.username_edittext);
        mPasswordEditText = (EditText) findViewById(R.id.password_edittext);

        mLoginButton = (Button) findViewById(R.id.login_button);
        mSignUpButton = (Button) findViewById(R.id.signup_button);

        final LoginInteractor loginInteractor = new LoginInteractor(mContext);
        LoginView loginView = this;

        final LoginPresenter loginPresenter = new LoginPresenter(loginView, loginInteractor);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.login();
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.signup();
            }
        });
    }

    @Override
    public void signup() {
        Intent intent = new Intent(mContext, SignupActivity.class);
        startActivity(intent);
    }

    @Override
    public void startItemsAvailableForBidActivity() {
        Intent intent = new Intent(mContext, ViewItemsInAuctionActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showPasswordLengthInvalidError() {
        mPasswordEditText.setError(getString(R.string.password_length_invalid_error));
    }

    @Override
    public void showUsernameLengthInvalidError() {
        mPasswordEditText.setError(getString(R.string.username_length_invalid_error));
    }

    @Override
    public String getPassword() {
        return mPasswordEditText.getText().toString();
    }

    @Override
    public String getUsername() {
        return mUsernameEditText.getText().toString();
    }

    @Override
    public void showInvalidCredentialsError() {
        Toast.makeText(mContext, getString(R.string.invalid_credentials_error), Toast.LENGTH_SHORT).show();
    }
}
