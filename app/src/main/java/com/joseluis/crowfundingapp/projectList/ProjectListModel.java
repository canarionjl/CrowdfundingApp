package com.joseluis.crowfundingapp.projectList;

import android.util.Log;

import com.joseluis.crowfundingapp.data.ProjectItem;
import com.joseluis.crowfundingapp.data.UserProjectJoinTable;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.util.ArrayList;
import java.util.List;

public class ProjectListModel implements ProjectListContract.Model {

    public static String TAG = ProjectListModel.class.getSimpleName();

    private RepositoryContract repository;

    private ArrayList<ProjectItem> projectList;

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

    @Override
    public void getProjectsUserJoinList(int id, RepositoryContract.GetUserToProjectListCallback callback){
        repository.getUserToProjectListWithId(id,new RepositoryContract.GetUserToProjectListCallback() {
            @Override
            public void setUserToProjectList(List<UserProjectJoinTable> favouriteProjects) {
                if(favouriteProjects!=null) callback.setUserToProjectList(favouriteProjects);
            }
        });
    }

}
