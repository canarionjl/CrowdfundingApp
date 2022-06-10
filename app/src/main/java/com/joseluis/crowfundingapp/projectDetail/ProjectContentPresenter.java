package com.joseluis.crowfundingapp.projectDetail;

import android.util.Log;

import com.joseluis.crowfundingapp.app.AppMediator;
import com.joseluis.crowfundingapp.app.LoginToProjectListState;
import com.joseluis.crowfundingapp.app.ProjectListToProjectContentState;
import com.joseluis.crowfundingapp.data.UserProjectJoinTable;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectContentPresenter implements ProjectContentContract.Presenter {

    public static String TAG = ProjectContentPresenter.class.getSimpleName();

    private WeakReference<ProjectContentContract.View> view;
    private ProjectContentState state;
    private ProjectContentContract.Model model;
    private AppMediator mediator;

    public ProjectContentPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getProjectContentState();
    }

    @Override
    public void onStart() {


    }

   @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {
       recoverDataFromProjectListState();
       view.get().onDataUpdated(state);

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    //DATA MANIPULATION

    public void recoverDataFromProjectListState(){
        ProjectListToProjectContentState newState = mediator.getProjectListToProjectContentState();

       if (newState != null){
           Log.e(TAG,"NOT NULL");

            state.invitedUser=newState.invitedUser;
            state.projectItem=newState.itemClicked;
            state.userLogged=newState.userLogged;

            state.title = newState.itemClicked.title;
            state.date = new Date(newState.itemClicked.date);
            state.description = newState.itemClicked.description;
            state.imageUrl = newState.itemClicked.picture;

            state.isFavourite=newState.itemIsFavourite;
        }
    }




    //MVP METHODS

    @Override
    public void injectView(WeakReference<ProjectContentContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProjectContentContract.Model model) {
        this.model = model;
    }
}