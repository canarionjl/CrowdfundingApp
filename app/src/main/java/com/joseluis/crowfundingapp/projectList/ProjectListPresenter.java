package com.joseluis.crowfundingapp.projectList;

import java.lang.ref.WeakReference;

public class ProjectListPresenter implements ProjectListContract.Presenter {

    public static String TAG = ProjectListPresenter.class.getSimpleName();

    private WeakReference<ProjectListContract.View> view;
    private ProjectListState state;
    private ProjectListContract.Model model;
/*    private AppMediator mediator;

    public ProjectListPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getProjectListState();
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state
        state = new ProjectListState();

        // call the model and update the state
        state.data = model.getStoredData();

        // use passed state if is necessary
        PreviousToProjectListState savedState = getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromPreviousScreen(savedState.data);

            // update the state if is necessary
            state.data = savedState.data;
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
        model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        NextToProjectListState savedState = getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromNextScreen(savedState.data);

            // update the state if is necessary
            state.data = savedState.data;
        }

        // call the model and update the state
        //state.data = model.getStoredData();

        // update the view
        view.get().onDataUpdated(state);

    }

    @Override
    public void onBackPressed() {
        // Log.e(TAG, "onBackPressed()");
    }

    @Override
    public void onPause() {
        // Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        // Log.e(TAG, "onDestroy()");
    }

    private NextToProjectListState getStateFromNextScreen() {
        return mediator.getNextProjectListScreenState();
    }

    private void passStateToNextScreen(ProjectListToNextState state) {
        mediator.setNextProjectListScreenState(state);
    }

    private void passStateToPreviousScreen(ProjectListToPreviousState state) {
        mediator.setPreviousProjectListScreenState(state);
    }

    private PreviousToProjectListState getStateFromPreviousScreen() {
        return mediator.getPreviousProjectListScreenState();
    }

    @Override
    public void injectView(WeakReference<ProjectListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProjectListContract.Model model) {
        this.model = model;
    }*/

}