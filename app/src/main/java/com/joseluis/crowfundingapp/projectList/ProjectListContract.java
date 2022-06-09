package com.joseluis.crowfundingapp.projectList;

import android.view.MenuItem;

import com.joseluis.crowfundingapp.data.ProjectItem;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.lang.ref.WeakReference;

public interface ProjectListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayProjectListData(ProjectListViewModel viewModel);

        void navigateToLoginScreen();

        void onMenuItemWatchFavouriteClicked(MenuItem item);
    }

    interface Presenter {
        boolean isUserLogged();


        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void onBackPressed();

        void onPause();

        void onDestroy();

        void onExitOptionItemSelected();

        void onFavouriteListOptionItemSelected(boolean favourite);
    }

    interface Model {
        void fetchCrowdfundingProjects(RepositoryContract.GetProjectListCallback callback);

        void getProjectsUserJoinList(int id, RepositoryContract.GetUserToProjectListCallback callback);
    }

}