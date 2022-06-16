package com.joseluis.crowfundingapp.register;

import android.util.Log;
import android.widget.Toast;

import com.joseluis.crowfundingapp.app.AppMediator;
import com.joseluis.crowfundingapp.data.UserItem;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class RegisterPresenter implements RegisterContract.Presenter {

    private static String REGISTER_INFORMATION;
    public static String TAG = RegisterPresenter.class.getSimpleName();

    private WeakReference<RegisterContract.View> view;
    private RegisterState state;
    private RegisterContract.Model model;
    private AppMediator mediator;

    public RegisterPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getRegisterState();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

        updateUserList();

        state.emailInput="";
        state.passwordInput="";
        state.usernameInput="";
        state.information_text="";

        view.get().onDataUpdated(state);
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void updateStateFromScreen(String username, String password, String email) {
        state.usernameInput=username;
        state.passwordInput=password;
        state.emailInput=email;
    }

    @Override
     public void updateUserList() {
        state.userList = null;

        model.getUsersList(new RepositoryContract.GetUserListCallback() {

            @Override
            public void setUserList(List<UserItem> users) {
                state.userList = (ArrayList<UserItem>) users;
            }
        });
    }
    @Override
    public void onRegisterButtonClicked() {

        boolean checkData = inputDataCheck();
        state.information_text = REGISTER_INFORMATION;

        if (checkData) {
            model.insertUser(state.usernameInput, state.passwordInput, state.emailInput);
            //updateUserList();
            state.passwordInput = "";
            state.emailInput = "";
            state.usernameInput = "";

            navigateToLoginScreen();
        } else {
            view.get().onDataUpdated(state);
        }
    }

    public boolean inputDataCheck(){

        if(state.userList==null){
            REGISTER_INFORMATION="ERROR DESCONOCIDO";
            return false;
        }

        if(state.usernameInput.length()<1||state.usernameInput.length()>15){
            REGISTER_INFORMATION="Formato de nombre de usuario incorrecto";
            return false;
        }
        for(int i=0;i<state.userList.size();i++){
            if(state.userList.get(i).username.equals(state.usernameInput)) {
                REGISTER_INFORMATION="El nombre de usuario ya existe";
                return false;
            }
        }
        if (!state.emailInput.contains("@")||!state.emailInput.contains(".")||state.emailInput.length()<5) {
            REGISTER_INFORMATION="El email debe contener '@' y '.' (punto)";
            return false;
        }
        if(state.passwordInput.length()<8) {
            REGISTER_INFORMATION="La contraseña debe tener 8 caracteres o más";
            return false;
        }
        REGISTER_INFORMATION="Se ha registrado correctamente";
        return true;
    }

    public void navigateToLoginScreen(){
        Log.e(TAG,state.information_text);
        view.get().navigateToLoginScreen(state);
    }

    @Override
    public void injectView(WeakReference<RegisterContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(RegisterContract.Model model) {
        this.model = model;
    }

}