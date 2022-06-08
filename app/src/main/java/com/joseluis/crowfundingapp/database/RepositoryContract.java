package com.joseluis.crowfundingapp.database;

import com.joseluis.crowfundingapp.data.ProjectItem;
import com.joseluis.crowfundingapp.data.UserItem;

import java.util.List;

public interface RepositoryContract {

    interface FetchCrowdfundingDataCallback {
        void onCrowdfundingDataFetched(boolean error);
    }

    interface GetUserListCallback {
        void setUserList(List<UserItem> Users);
    }

    interface GetUserCallback {
        void setUser(UserItem User);
    }

    interface GetProjectListCallback {
        void setProjectList(List<ProjectItem> projects);
    }

    interface GetProjectCallback {
        void setProject(ProjectItem Project);
    }

    interface DeleteProjectCallback {
        void onProjectDeleted();
    }

    interface UpdateProjectCallback {
        void onProjectUpdated();
    }

    interface DeleteUserCallback {
        void onUserDeleted();
    }

    interface UpdateUserCallback {
        void onUserUpdated();
    }

    void loadCrowdfundingProjectsList(boolean clearFirst, CrowdfundingRepository.FetchCrowdfundingDataCallback callback);

    void getUser(int userId, CrowdfundingRepository.GetUserCallback callback);

    void insertUser (UserItem userItem);

    void getUserList(CrowdfundingRepository.GetUserListCallback callback);

    void getProject(int projectId, CrowdfundingRepository.GetProjectCallback callback);

    void getProjectList(CrowdfundingRepository.GetProjectListCallback callback);

    void deleteUser(UserItem User, CrowdfundingRepository.DeleteUserCallback callback);

    void updateUser(UserItem User, CrowdfundingRepository.UpdateUserCallback callback);

    void deleteProject(ProjectItem Project, CrowdfundingRepository.DeleteProjectCallback callback);

    void updateProject(ProjectItem Project, CrowdfundingRepository.UpdateProjectCallback callback);
}

