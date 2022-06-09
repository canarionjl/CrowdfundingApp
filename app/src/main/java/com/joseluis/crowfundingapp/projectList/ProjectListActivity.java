package com.joseluis.crowfundingapp.projectList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.joseluis.crowfundingapp.R;
import com.joseluis.crowfundingapp.data.ProjectItem;

public class ProjectListActivity
        extends AppCompatActivity implements ProjectListContract.View {

    public static String TAG = ProjectListActivity.class.getSimpleName();

    private ProjectListContract.Presenter presenter;

    private ProjectListAdapter adapter;

    Toolbar toolbar;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        toolbar = findViewById(R.id.toolbarRecyclerActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_toolbar_ProjectList_Activity);

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
    protected void onPause() {
        super.onPause();

        presenter.onPause();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }



    public void onDataUpdated(ProjectListViewModel viewModel) {
        if(viewModel.favouriteListSelected){
            if(viewModel.favouriteList!=null) adapter.setItems(viewModel.favouriteList);
        }
        else {
            if(viewModel.projectList!=null){
                adapter.setItems(viewModel.projectList);
            }
        }
    }

    @Override
    public void displayProjectListData(ProjectListViewModel viewModel) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onDataUpdated(viewModel);
            }
        });
    }

    @Override
    public void navigateToLoginScreen() {
        finish();
    }


    @Override
    public void injectPresenter(ProjectListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        this.menu=menu;
        configureWatchFavouriteMenuItem();

        return super.onCreateOptionsMenu(menu);
    }

    public void configureWatchFavouriteMenuItem(){
        menu.findItem(R.id.favouriteListIcon).setVisible(presenter.isUserLogged());
    }

    @Override
    public void onMenuItemWatchFavouriteClicked(MenuItem item){
        boolean selection = !item.isChecked();
        presenter.onFavouriteListOptionItemSelected(selection);
        item.setChecked(selection);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.exitIcon:
                presenter.onExitOptionItemSelected();
                break;
            case R.id.favouriteListIcon:
                onMenuItemWatchFavouriteClicked(item);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}