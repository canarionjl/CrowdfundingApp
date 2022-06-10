package com.joseluis.crowfundingapp.projectDetail;

import com.joseluis.crowfundingapp.data.UserProjectJoinTable;
import com.joseluis.crowfundingapp.database.CrowdfundingRepository;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.util.List;

public class ProjectContentModel implements ProjectContentContract.Model {

    public static String TAG = ProjectContentModel.class.getSimpleName();

    RepositoryContract repository;


    public ProjectContentModel(RepositoryContract repository) {
        this.repository=repository;
    }

    @Override
    public void getProjectsUserJoinList(int id, RepositoryContract.GetUserToProjectListCallback callback){
        repository.getUserToProjectListWithId(id,new RepositoryContract.GetUserToProjectListCallback() {
            @Override
            public void setUserToProjectList(List<UserProjectJoinTable> favouriteProjects) {
                callback.setUserToProjectList(favouriteProjects);
            }
        });


    }
}
