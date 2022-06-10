package com.joseluis.crowfundingapp.projectDetail;

import android.util.Log;

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

    @Override
    public void insertUserToProjectRelationship(int userId, int projectId){
        repository.setUserToProjectListWithId(userId,projectId);
    }

    @Override
    public void deleteUserToProjectRelationship(int userId, int projectId){
       repository.getRelationshipBetweenUserAndProjectWithId(userId, projectId, new RepositoryContract.GetRelationshipBetweenUserAndProjectWithId() {
           @Override
           public void setUserProjectJoinTable(UserProjectJoinTable relationship) {
               Log.e(TAG,"relation not null");
               if(relationship!=null) repository.deleteUserToProjectListWithId(relationship);
           }
       });

    }
}
