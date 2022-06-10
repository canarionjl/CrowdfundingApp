package com.joseluis.crowfundingapp.database;

import com.joseluis.crowfundingapp.data.ProjectItem;
import com.joseluis.crowfundingapp.data.UserItem;
import com.joseluis.crowfundingapp.data.UserProjectJoinTable;

import java.util.List;

public interface RepositoryContract {

    void getRelationshipBetweenUserAndProjectWithId(int userId, int projectId, GetRelationshipBetweenUserAndProjectWithId callback);

    interface FetchCrowdfundingDataCallback {
        void onCrowdfundingDataFetched(boolean error);
    }

    interface GetUserListCallback {
        void setUserList(List<UserItem> Users);
    }

    interface GetProjectListCallback {
        void setProjectList(List<ProjectItem> projects);
    }

    interface GetUserToProjectListCallback {
        void setUserToProjectList(List<UserProjectJoinTable> projects);
    }

    interface GetRelationshipBetweenUserAndProjectWithId {
        void setUserProjectJoinTable(UserProjectJoinTable relationship);
    }



    void loadCrowdfundingProjectsList(CrowdfundingRepository.FetchCrowdfundingDataCallback callback);

    void insertUser (UserItem userItem);

    void getUserList(CrowdfundingRepository.GetUserListCallback callback);

    void getProjectList(CrowdfundingRepository.GetProjectListCallback callback);

    void getUserToProjectListWithId(int id, GetUserToProjectListCallback callback);

    void setUserToProjectListWithId(int userId, int projectId);

    void deleteUserToProjectListWithId(UserProjectJoinTable userToProjectRelationship);

}

