package com.joseluis.crowfundingapp.login;


import static androidx.core.content.res.TypedArrayUtils.getString;

import android.util.Log;
import android.widget.Toast;

import com.joseluis.crowfundingapp.R;
import com.joseluis.crowfundingapp.app.AppMediator;
import com.joseluis.crowfundingapp.app.LoginToProjectListState;
import com.joseluis.crowfundingapp.data.UserItem;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class LoginPresenter implements LoginContract.Presenter {

    public static String TAG = LoginPresenter.class.getSimpleName();
    public static String LOGIN_ERROR = "Usuario y/o contraseña incorrectos";

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
        model.deleteAllTables();
    }

    @Override
    public void onRestart() {
    }

    @Override
    public void onResume() {
        updateUserList();

       state.informationText="";
       state.usernameInput="";
       state.passwordInput="";

       view.get().onDataUpdated(state);
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
        state.invitedUser=true;
        navigateToProjectsListScreen();
    }

    @Override
    public void onRegisterTextClicked(){
        view.get().navigateToRegisterScreen();
    }

    @Override
    public void onLoginButtonClicked(){
        if(isUserCorrect()){
            state.invitedUser=false;
            navigateToProjectsListScreen();
        }else{
            state.informationText = LOGIN_ERROR;
            state.usernameInput="";
            state.passwordInput="";
            view.get().onDataUpdated(state);
        }
    }

    @Override
    public void updateUserList() {
        model.getUsersList(new RepositoryContract.GetUserListCallback() {

            @Override
            public void setUserList(List<UserItem> users) {
                state.userList = (ArrayList<UserItem>) users;
            }
        });
    }

    public void navigateToProjectsListScreen() {
        LoginToProjectListState newState = new LoginToProjectListState();

        newState.userItem=state.loggedUser;
        newState.invitedUser=state.invitedUser;

        mediator.setLoginToProjectListState(newState);
        view.get().navigateToProjectsListScreen();
    }

    public boolean isUserCorrect(){

        updateUserList();

        boolean userFound=false;

        if(state.userList!=null && state.userList.size()>0){
            for(int i=0;i<state.userList.size();i++){
                if((state.userList.get(i).username).equals(state.usernameInput)){
                    if(state.userList.get(i).password.equals(state.passwordInput)){
                        userFound = true;
                        state.loggedUser=state.userList.get(i);
                    }
                }
            }

        }else{}

        return userFound;
    }

}