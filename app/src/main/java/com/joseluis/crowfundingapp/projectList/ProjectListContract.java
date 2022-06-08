package com.joseluis.crowfundingapp.projectList;

import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.lang.ref.WeakReference;

public interface ProjectListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(ProjectListViewModel viewModel);
    }

    interface Presenter {
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
        void fetchCrowdfundingProjects(RepositoryContract.GetProjectListCallback callback);
    }

}