package com.joseluis.crowfundingapp.login;

import com.joseluis.crowfundingapp.data.UserItem;
import com.joseluis.crowfundingapp.database.CrowdfundingRepository;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.util.ArrayList;
import java.util.List;

public class LoginModel implements LoginContract.Model {

    public static String TAG = LoginModel.class.getSimpleName();

    List<UserItem> userList = new ArrayList<UserItem>();

    RepositoryContract repository;

    public LoginModel(RepositoryContract repository) {
        this.repository=repository;
    }

    public void getUsersList(RepositoryContract.GetUserListCallback callback){

        repository.getUserList(new RepositoryContract.GetUserListCallback (){
            @Override
            public void setUserList(List<UserItem> users) {
                if(users!=null) userList = (ArrayList<UserItem>) users;
                callback.setUserList(users);
            }
        });
    }
}
