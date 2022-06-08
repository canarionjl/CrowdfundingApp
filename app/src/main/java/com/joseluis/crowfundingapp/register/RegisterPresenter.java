package com.joseluis.crowfundingapp.register;

import android.util.Log;

import com.joseluis.crowfundingapp.app.AppMediator;
import com.joseluis.crowfundingapp.data.UserItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class RegisterPresenter implements RegisterContract.Presenter {

    private static final String USERNAME_NOT_VALID =  "El nombre de usuario ya existe" ;
    private static final String EMAIL_NOT_VALID = "Formato de correo electrónico incorrecto ";
    private static final String USERNAME_INVALID_FORMAT = "Formato de nombre de usuario incorrecto";
    private static final String PASSWORD_INVALID_FORMAT = "Formato de contraseña incorrecto" ;
    private static final String ALL_INPUTS_CORRECTS = "Se ha registrado correctamente";
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
        state = new RegisterState();
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
        //model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {

        model.getUsersList();

        state.emailInput="";
        state.passwordInput="";
        state.usernameInput="";
        state.information_text="";

        view.get().onDataUpdated(state);
    }

    @Override
    public void onPause() {
        // Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        // Log.e(TAG, "onDestroy()");
    }

    @Override
    public void updateStateFromScreen(String username, String password, String email) {
        state.usernameInput=username;
        state.passwordInput=password;
        state.emailInput=email;
    }

    @Override
    public void setUpdatedListFromModel(ArrayList<UserItem> listUsers){
        state.userList=listUsers;
    }

    @Override
    public void onRegisterButtonClicked() {
        if(inputDataCheck()){
            model.insertUser(state.usernameInput, state.passwordInput,state.emailInput);

            state.userList=null;
            model.getUsersList();
            while(state.userList==null);

            for (UserItem userItem: state.userList){
                Log.e(TAG,userItem.username+""+userItem.password);
            }
            state.passwordInput="";
            state.emailInput="";
            state.usernameInput="";
        }

        state.information_text=REGISTER_INFORMATION;
        view.get().onDataUpdated(state);
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
        if (!state.emailInput.contains("@")&&!state.emailInput.contains(".")&&state.emailInput.length()>5) {
            REGISTER_INFORMATION="Formato de correo electrónico incorrecto";
            return false;
        }
        if(state.passwordInput.length()<8) {
            REGISTER_INFORMATION="Formato de contraseña incorrecto";
            return false;
        }
        REGISTER_INFORMATION="Se ha registrado correctamente";
        return true;
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