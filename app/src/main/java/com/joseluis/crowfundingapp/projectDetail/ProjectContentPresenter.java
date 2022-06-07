package com.joseluis.crowfundingapp.projectDetail;

import com.joseluis.crowfundingapp.app.AppMediator;

import java.lang.ref.WeakReference;

public class ProjectContentPresenter implements ProjectContentContract.Presenter {

    public static String TAG = ProjectContentPresenter.class.getSimpleName();

    private WeakReference<ProjectContentContract.View> view;
    private ProjectContentState state;
    private ProjectContentContract.Model model;
    private AppMediator mediator;

    public ProjectContentPresenter(AppMediator mediator) {
        this.mediator = mediator;
        //state = mediator.getProjectContentState();
    }

/*    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state
        state = new ProjectContentState();

        // call the model and update the state
        state.data = model.getStoredData();

        // use passed state if is necessary
        PreviousToProjectContentState savedState = getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromPreviousScreen(savedState.data);

            // update the state if is necessary
            state.data = savedState.data;
        }
    }*/

/*    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
        model.onRestartScreen(state.data);
    }*/

    /*@Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        NextToProjectContentState savedState = getStateFromNextScreen();
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

    private NextToProjectContentState getStateFromNextScreen() {
        return mediator.getNextProjectContentScreenState();
    }

    private void passStateToNextScreen(ProjectContentToNextState state) {
        mediator.setNextProjectContentScreenState(state);
    }

    private void passStateToPreviousScreen(ProjectContentToPreviousState state) {
        mediator.setPreviousProjectContentScreenState(state);
    }

    private PreviousToProjectContentState getStateFromPreviousScreen() {
        return mediator.getPreviousProjectContentScreenState();
    }

    @Override
    public void injectView(WeakReference<ProjectContentContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProjectContentContract.Model model) {
        this.model = model;
    }
*/
}