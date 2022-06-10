package com.joseluis.crowfundingapp.register;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.joseluis.crowfundingapp.R;
import com.joseluis.crowfundingapp.login.LoginViewModel;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

    public static String TAG = RegisterActivity.class.getSimpleName();

    Toolbar toolbar;
    EditText usernameInput;
    EditText passwordInput;
    EditText emailInput;

    private RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolbarRegisterActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_toolbar_Register_Activity);

        configureViewComponents();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        RegisterScreen.configure(this);

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
        finish();
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
    public void onDataUpdated(RegisterViewModel viewModel) {
        ((TextView)findViewById(R.id.textViewIncorrectData)).setText(viewModel.information_text);
        usernameInput.setText(viewModel.usernameInput);
        passwordInput.setText(viewModel.passwordInput);
        emailInput.setText(viewModel.emailInput);
    }

    public void onRegisterButtonClicked(){
        presenter.updateStateFromScreen(usernameInput.getText().toString(),passwordInput.getText().toString(),emailInput.getText().toString());
        presenter.onRegisterButtonClicked();

    }


    @Override
    public void navigateToLoginScreen(RegisterViewModel viewModel) {
        Toast.makeText(this,viewModel.information_text,Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void injectPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void configureViewComponents(){
        ((Button)findViewById(R.id.buttonRegister)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterButtonClicked();
            }
        });
        ((TextView)findViewById(R.id.textLoginRegisterScreen)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        usernameInput = findViewById(R.id.editTextRegisterUserName);
        passwordInput = findViewById(R.id.editTextRegisterPassword);
        emailInput = findViewById(R.id.editTextRegisterEmailAddress);
    }


}
