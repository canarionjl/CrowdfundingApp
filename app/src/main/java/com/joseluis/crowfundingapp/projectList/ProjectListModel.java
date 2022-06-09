package com.joseluis.crowfundingapp.projectList;

import android.util.Log;

import com.joseluis.crowfundingapp.database.RepositoryContract;

public class ProjectListModel implements ProjectListContract.Model {

    public static String TAG = ProjectListModel.class.getSimpleName();

    private RepositoryContract repository;

    public ProjectListModel(RepositoryContract repository) {
        this.repository=repository;
    }

    @Override
    public void fetchCrowdfundingProjects(RepositoryContract.GetProjectListCallback callback){

         repository.loadCrowdfundingProjectsList( new RepositoryContract.FetchCrowdfundingDataCallback() {
            @Override
            public void onCrowdfundingDataFetched(boolean error) {
                if (!error) {
                    repository.getProjectList(callback);
                }
            }
        });
    }
}
