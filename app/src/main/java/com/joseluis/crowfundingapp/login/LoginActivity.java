package com.joseluis.crowfundingapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.joseluis.crowfundingapp.R;
import com.joseluis.crowfundingapp.register.RegisterActivity;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    public static String TAG = LoginActivity.class.getSimpleName();

    Toolbar toolbar;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbarLoginActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_toolbar_Login_Activity);

        LoginScreen.configure(this);

        setOnClickListeners();

       if (savedInstanceState == null) {
           //presenter.onStart();
       } else {
          // presenter.onRestart();
       }
    }


/*    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        presenter.onBackPressed();
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
    public void onDataUpdated(LoginActivityViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
*/
    @Override
    public void injectPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
    public void onTextViewGuestAccessClicked(){

    }
    public void onRegisterTextClicked(){
        presenter.onRegisterTextClicked();
    }

    public void onLoginButtonClicked(){
        presenter.onLoginButtonClicked();
    }

    public void navigateToRegisterScreen(){
        Log.e(TAG,"AQUI");
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void setOnClickListeners(){
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

    }
}