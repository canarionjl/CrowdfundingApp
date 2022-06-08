package com.joseluis.crowfundingapp.login;

import com.joseluis.crowfundingapp.data.UserItem;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public interface LoginContract {


    interface View {
        void onDataUpdated(LoginViewModel viewModel);

        void injectPresenter(Presenter presenter);

        void getEditTextContent();

        void navigateToRegisterScreen();

        void navigateToProjectsListScreen();

    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void onLoginButtonClicked();
        void onRegisterTextClicked();
        void onTextViewGuestAccessClicked();

        void updateStateFromScreen(String username, String password);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void onBackPressed();

        void onPause();

        void onDestroy();

        void updateUserList();
    }

    interface Model {
        
        void getUsersList(RepositoryContract.GetUserListCallback callback);

    }

}
