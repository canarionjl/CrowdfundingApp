package com.joseluis.crowfundingapp.projectDetail;

import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.lang.ref.WeakReference;

public interface ProjectContentContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(ProjectContentViewModel viewModel);

        void updateFavouriteButtonState(ProjectContentViewModel viewModel);

        void navigateToNextScreen();
    }

    interface Presenter {
        void onAddFavouriteButtonClicked();

        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void onBackPressed();

        void onPause();

        void onDestroy();
    }

    interface Model {

        void getProjectsUserJoinList(int id, RepositoryContract.GetUserToProjectListCallback callback);

        void insertUserToProjectRelationship(int userId, int projectId);

        void deleteUserToProjectRelationship(int userId, int projectId);
    }

}