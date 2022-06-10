package com.joseluis.crowfundingapp.app;

import com.joseluis.crowfundingapp.login.LoginState;
import com.joseluis.crowfundingapp.projectDetail.ProjectContentState;
import com.joseluis.crowfundingapp.projectList.ProjectListState;
import com.joseluis.crowfundingapp.register.RegisterState;

@SuppressWarnings("unused")
public class AppMediator {

    private static AppMediator INSTANCE;


    private LoginState loginState = new LoginState();
    private RegisterState registerState = new RegisterState();
    private ProjectListState projectListState = new ProjectListState();
    private ProjectContentState projectContentState = new ProjectContentState();

    private LoginToProjectListState loginToProjectListState;
    private ProjectListToProjectContentState projectListToProjectContentState;



    private AppMediator() {}



    public static AppMediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppMediator();
        }
        return INSTANCE;
    }

    public static void resetInstance() {
        INSTANCE = null;
    }




    public LoginState getLoginState() {
        return loginState;
    }

    public RegisterState getRegisterState() {
        return registerState;
    }

    public ProjectListState getProjectListState() {
        return projectListState;
    }

    public ProjectContentState getProjectContentState() {
        return projectContentState;
    }




    public LoginToProjectListState getLoginToProjectListState() {
        LoginToProjectListState state = loginToProjectListState;
        loginToProjectListState=null;
        return state;
    }

    public void setLoginToProjectListState(LoginToProjectListState loginToProjectListState) {
        this.loginToProjectListState = loginToProjectListState;
    }

    public ProjectListToProjectContentState getProjectListToProjectContentState() {
        ProjectListToProjectContentState state = projectListToProjectContentState;
        projectListToProjectContentState = null;
        return state;
    }

    public void setProjectListToProjectContentState(ProjectListToProjectContentState projectListToProjectContentState) {
        this.projectListToProjectContentState = projectListToProjectContentState;
    }

}