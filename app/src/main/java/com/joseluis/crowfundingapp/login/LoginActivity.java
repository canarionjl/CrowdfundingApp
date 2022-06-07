package com.joseluis.crowfundingapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.joseluis.crowfundingapp.R;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    public static String TAG = LoginActivity.class.getSimpleName();

    Toolbar toolbar;

    //private LoginActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         toolbar = findViewById(R.id.toolbarLoginActivity);
        //setActionBar(toolbar);
        //getActionBar().setTitle("LOGIN");
        //getSupportActionBar().setTitle(R.string.app_name);


        // do the setup
        //LoginScreen.configure(this);

       // if (savedInstanceState == null) {
            //presenter.onStart();

       // } else {
           // presenter.onRestart();
        //}
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

    @Override
    public void injectPresenter(LoginActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }*/
}