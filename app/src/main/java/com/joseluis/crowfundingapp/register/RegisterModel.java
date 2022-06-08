package com.joseluis.crowfundingapp.register;

import android.util.Log;

import com.joseluis.crowfundingapp.data.UserItem;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.util.ArrayList;
import java.util.List;

public class RegisterModel implements RegisterContract.Model {

    public static String TAG = RegisterModel.class.getSimpleName();

    RepositoryContract repository;
    RegisterContract.Presenter presenter;

    List<UserItem> userList = new ArrayList<UserItem>();

    public RegisterModel(RepositoryContract repository) {
        this.repository=repository;
    }

    public void getUsersListFromRepository(){
        repository.getUserList(new RepositoryContract.GetUserListCallback (){
            @Override
            public void setUserList(List<UserItem> users) {
                if(users!=null) userList = (ArrayList<UserItem>) users;
                presenter.setUpdatedListFromModel((ArrayList<UserItem>)users);
            }
        });
    }

   

    @Override
    public void insertUser(String username, String password, String email) {
        repository.insertUser(new UserItem(username,password,email));
    }

    public void injectPresenter (RegisterContract.Presenter presenter){
        this.presenter=presenter;
    }
}
