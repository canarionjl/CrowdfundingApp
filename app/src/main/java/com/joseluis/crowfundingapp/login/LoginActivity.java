package com.joseluis.crowfundingapp.login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.joseluis.crowfundingapp.R;
import com.joseluis.crowfundingapp.projectList.ProjectListActivity;
import com.joseluis.crowfundingapp.register.RegisterActivity;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    public static String TAG = LoginActivity.class.getSimpleName();

    Toolbar toolbar;
    EditText userInput;
    EditText passwordInput;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        toolbar = findViewById(R.id.toolbarLoginActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_toolbar_Login_Activity);

        LoginScreen.configure(this);

        configureViewComponents();

       if (savedInstanceState == null) {
           presenter.onStart();
       } else {
           presenter.onRestart();
       }
    }


 @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    @Override
    public void onDataUpdated(LoginViewModel viewModel) {
        ((TextView)findViewById(R.id.textViewLoginErrorMessage)).setText(viewModel.informationText);
        userInput.setText(viewModel.usernameInput);
        passwordInput.setText(viewModel.passwordInput);
    }


    @Override
    public void injectPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void onTextViewGuestAccessClicked(){
        presenter.onTextViewGuestAccessClicked();
    }

    public void onRegisterTextClicked(){

        presenter.onRegisterTextClicked();
    }

    @Override
    public void getEditTextContent(){
        presenter.updateStateFromScreen(userInput.getText().toString(),passwordInput.getText().toString());
    }

    public void onLoginButtonClicked(){
        getEditTextContent();
        presenter.onLoginButtonClicked();
    }

    @Override
    public void navigateToRegisterScreen(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToProjectsListScreen(){
        Intent intent = new Intent(this, ProjectListActivity.class);
        startActivity(intent);
    }

    public void configureViewComponents(){
        ((TextView) findViewById(R.id.textViewGuestAccess)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTextViewGuestAccessClicked();
            }
        });
        ((TextView) findViewById(R.id.textLoginRegister)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterTextClicked();
            }
        });

        ((Button)findViewById(R.id.buttonLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClicked();
            }
        });

        userInput = findViewById(R.id.editTextLoginUserName);
        passwordInput = findViewById(R.id.editTextLoginPassword);

        ((ImageView)findViewById(R.id.imageCrowdfundingIconLoginScreen)).setImageResource(R.drawable.crowdfunding_icon);

    }
}