package com.joseluis.crowfundingapp.projectDetail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.joseluis.crowfundingapp.R;

public class ProjectContentActivity
        extends AppCompatActivity implements ProjectContentContract.View {

    public static String TAG = ProjectContentActivity.class.getSimpleName();

    private ProjectContentContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_detail);
        getSupportActionBar().setTitle(R.string.app_name);


        // do the setup
        ProjectContentScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
    }

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
    }

    @Override
    public void onDataUpdated(ProjectContentViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, ProjectContentActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(ProjectContentContract.Presenter presenter) {
        this.presenter = presenter;
    }
}