package com.joseluis.crowfundingapp.login;

import com.joseluis.crowfundingapp.data.UserItem;

import java.util.ArrayList;

public class LoginState extends LoginViewModel {

    ArrayList<UserItem> userList;
    UserItem loggedUser;
    boolean invitedUser;
}
