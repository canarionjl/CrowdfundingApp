package com.joseluis.crowfundingapp.projectList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.joseluis.crowfundingapp.R;

public class ProjectListActivity
        extends AppCompatActivity implements ProjectListContract.View {

    public static String TAG = ProjectListActivity.class.getSimpleName();

    private ProjectListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        getSupportActionBar().setTitle(R.string.app_name);


    /*    // do the setup
        ProjectListScreen.configure(this);

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
*/
/*    @Override
    public void onDataUpdated(ProjectListViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, ProjectListActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(ProjectListContract.Presenter presenter) {
        this.presenter = presenter;
    }*/
    }
}