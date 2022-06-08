package com.joseluis.crowfundingapp.login;


import static androidx.core.content.res.TypedArrayUtils.getString;

import com.joseluis.crowfundingapp.R;
import com.joseluis.crowfundingapp.app.AppMediator;

import java.lang.ref.WeakReference;

public class LoginPresenter implements LoginContract.Presenter {

    public static String TAG = LoginPresenter.class.getSimpleName();
    public static String LOGIN_ERROR = "El usuario y/o la contraseña no son correctos";

    private WeakReference<LoginContract.View> view;
    private LoginState state;
    private LoginContract.Model model;
    private AppMediator mediator;

    public LoginPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getLoginState();
    }

    @Override
    public void onStart() {

        state.userList=model.getUsersList();
        // use passed state if is necessary
        //      PreviousToLoginState savedState = getStateFromPreviousScreen();
       /* if (savedState != null) {

            // update the model if is necessary
            model.onDataFromPreviousScreen(savedState.data);

            // update the state if is necessary
            state.data = savedState.data;
        }*/
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
        //model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

      /*  // use passed state if is necessary
        NextToLoginState savedState = getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromNextScreen(savedState.data);

            // update the state if is necessary
            state.data = savedState.data;
        }

        // call the model and update the state
        //state.data = model.getStoredData();

        // update the view
        view.get().onDataUpdated(state);*/

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
/*
    private NextToLoginState getStateFromNextScreen() {
        return mediator.getNextLoginScreenState();
    }

    private void passStateToNextScreen(LoginToNextState state) {
        mediator.setNextLoginScreenState(state);
    }

    private void passStateToPreviousScreen(LoginToPreviousState state) {
        mediator.setPreviousLoginScreenState(state);
    }

    private PreviousToLoginState getStateFromPreviousScreen() {
        return mediator.getPreviousLoginScreenState();
    }*/



    @Override
    public void injectModel(LoginContract.Model model) {
        this.model = model;
    }
     @Override
     public void injectView(WeakReference<LoginContract.View> view) {
         this.view = view;
     }

    @Override
    public void onTextViewGuestAccessClicked(){

    }
    @Override
    public void onRegisterTextClicked(){
        view.get().navigateToRegisterScreen();
    }

    @Override
    public void onLoginButtonClicked(){
        if(isUserCorrect()){
            navigateToProjectsListScreen();
        }else{
            state.informationText = LOGIN_ERROR;
            state.usernameInput="";
            state.passwordInput="";
        }
    }

    public void navigateToProjectsListScreen(){
        // rellenar mañana
    }

    public boolean isUserCorrect(){
        boolean userFound;
        if(state.userList!=null && state.userList.size()!=0){
            for(int i=0;i<state.userList.size();i++){
                if((state.userList.get(i).username).equals(state.usernameInput){
                    if(state.userList.get(i).password.equals(state.passwordInput)){
                        userFound = true;
                        state.loggedUser=state.userList.get(i);
                    }
                }
            }
        }else{
            userFound=false;
        }
        return userFound;
    }

}