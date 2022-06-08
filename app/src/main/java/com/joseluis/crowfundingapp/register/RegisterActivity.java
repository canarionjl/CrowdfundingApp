package com.joseluis.crowfundingapp.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.joseluis.crowfundingapp.R;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

    public static String TAG = RegisterActivity.class.getSimpleName();

    Toolbar toolbar;

    //private RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolbarRegisterActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_toolbar_Register_Activity);
        //getSupportActionBar().setTitle(R.string.app_name);


        // do the setup
        //RegisterScreen.configure(this);

       // if (savedInstanceState == null) {
            //presenter.onStart();

        //} else {
            //presenter.onRestart();
       // }
    }
/*
    @Override
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
    }*/

/*
    @Override
    public void onDataUpdated(RegisterViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
       // ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(RegisterContract.Presenter presenter) {
        //this.presenter = presenter;
    }

 */
}
