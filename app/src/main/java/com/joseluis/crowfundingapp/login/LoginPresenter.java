package com.joseluis.crowfundingapp.login;


import static androidx.core.content.res.TypedArrayUtils.getString;

import android.util.Log;

import com.joseluis.crowfundingapp.R;
import com.joseluis.crowfundingapp.app.AppMediator;
import com.joseluis.crowfundingapp.app.LoginToProjectListState;

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
    }

    @Override
    public void onRestart() {
    }

    @Override
    public void onResume() {
       state.informationText="";
       state.usernameInput="";
       state.passwordInput="";

       view.get().onDataUpdated(state);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onPause() {
        view.get().getEditTextContent();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void updateStateFromScreen(String username, String password){
        state.usernameInput=username;
        state.passwordInput=password;
    }

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
        state.loggedUser = null;
        navigateToProjectsListScreen();
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
            view.get().onDataUpdated(state);
        }
    }

    public void navigateToProjectsListScreen() {
        LoginToProjectListState newState = new LoginToProjectListState();
        newState.userItem=state.loggedUser;
        mediator.setLoginToProjectListState(newState);
        view.get().navigateToRegisterScreen(); //debería ser ProjectList --> se puso register para prueba
    }

    public boolean isUserCorrect(){

        Log.e(TAG, state.usernameInput+" "+state.passwordInput);
        //model.insertUser();
        state.userList=model.getUsersList();
        boolean userFound=false;
        if(state.userList!=null && state.userList.size()>0){
            for(int i=0;i<state.userList.size();i++){
                Log.i(TAG,state.userList.get(i).username+ " "+state.userList.get(i).password);
                if((state.userList.get(i).username).equals(state.usernameInput)){
                    if(state.userList.get(i).password.equals(state.passwordInput)){
                        userFound = true;
                        state.loggedUser=state.userList.get(i);
                    }
                }
            }
        }else{ }

        return userFound;
    }

}