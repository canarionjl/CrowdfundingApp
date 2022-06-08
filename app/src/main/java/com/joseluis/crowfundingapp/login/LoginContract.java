package com.joseluis.crowfundingapp.login;

import com.joseluis.crowfundingapp.data.UserItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public interface LoginContract {


    interface View {
       void injectPresenter(Presenter presenter);

        void navigateToRegisterScreen();

        void navigateToProjectsListScreen();
        /*
        void onDataUpdated(LoginViewModel viewModel);

        void navigateToNextScreen();*/
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void onLoginButtonClicked();
        void onRegisterTextClicked();
        void onTextViewGuestAccessClicked();
        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void onBackPressed();

        void onPause();

        void onDestroy();
    }

    interface Model {
        
        ArrayList<UserItem> getUsersList();
     
    }

}
