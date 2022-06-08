package com.joseluis.crowfundingapp.register;

import com.joseluis.crowfundingapp.data.UserItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public interface RegisterContract {

    interface View {
       void injectPresenter(Presenter presenter);

        void onDataUpdated(RegisterViewModel viewModel);

        void navigateToLoginScreen();
    }

    interface Presenter {
       void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void onPause();

        void onDestroy();

        void updateStateFromScreen(String username, String password, String email);

        void setUpdatedListFromModel(ArrayList<UserItem> listUsers);

        void onRegisterButtonClicked();
    }

    interface Model {
        void getUsersList();

        void insertUser(String username, String password, String email);

    }

}