package com.joseluis.crowfundingapp.projectList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.joseluis.crowfundingapp.R;
import com.joseluis.crowfundingapp.data.ProjectItem;

public class ProjectListActivity
        extends AppCompatActivity implements ProjectListContract.View {

    public static String TAG = ProjectListActivity.class.getSimpleName();

    private ProjectListContract.Presenter presenter;

    private ProjectListAdapter adapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        toolbar = findViewById(R.id.toolbarRecyclerActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        adapter = new ProjectListAdapter(this,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectItem item = (ProjectItem) view.getTag();
                //presenter.selectProjectListData(item);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.project_list);
        recyclerView.setAdapter(adapter);

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
    public void onDataUpdated(ProjectListViewModel viewModel) {

    }

    @Override
    public void displayProjectListData(ProjectListState stateOne) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setItems(stateOne.projectList);
            }
        });
    }


    @Override
    public void injectPresenter(ProjectListContract.Presenter presenter) {
        this.presenter = presenter;
    }

}