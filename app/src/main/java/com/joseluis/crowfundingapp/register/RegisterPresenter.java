package com.joseluis.crowfundingapp.register;

import com.joseluis.crowfundingapp.app.AppMediator;

import java.lang.ref.WeakReference;

public class RegisterPresenter implements RegisterContract.Presenter {

    public static String TAG = RegisterPresenter.class.getSimpleName();

    private WeakReference<RegisterContract.View> view;
    private RegisterState state;
    private RegisterContract.Model model;
    private AppMediator mediator;

    public RegisterPresenter(AppMediator mediator) {
        this.mediator = mediator;
        //state = mediator.getRegisterState();
    }
/*
    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state
        state = new RegisterState();

        // call the model and update the state
        state.data = model.getStoredData();

       *//* // use passed state if is necessary
        PreviousToRegisterState savedState = getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromPreviousScreen(savedState.data);

            // update the state if is necessary
            state.data = savedState.data;*//*
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
      *//*  // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        NextToRegisterState savedState = getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromNextScreen(savedState.data);

            // update the state if is necessary
            state.data = savedState.data;*//*
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

*//*    private NextToRegisterState getStateFromNextScreen() {
        return mediator.getNextRegisterScreenState();
    }

    private void passStateToNextScreen(RegisterToNextState state) {
        mediator.setNextRegisterScreenState(state);
    }

    private void passStateToPreviousScreen(RegisterToPreviousState state) {
        mediator.setPreviousRegisterScreenState(state);
    }

    private PreviousToRegisterState getStateFromPreviousScreen() {
        return mediator.getPreviousRegisterScreenState();
    }*//*

    @Override
    public void injectView(WeakReference<RegisterContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(RegisterContract.Model model) {
        this.model = model;
    }*/

}