package com.joseluis.crowfundingapp.app;

import com.joseluis.crowfundingapp.login.LoginState;
import com.joseluis.crowfundingapp.projectList.ProjectListState;
import com.joseluis.crowfundingapp.register.RegisterState;

@SuppressWarnings("unused")
public class AppMediator {

    private static AppMediator INSTANCE;

    private LoginState loginState = new LoginState();
    private RegisterState registerState = new RegisterState();
    private ProjectListState projectListState = new ProjectListState();

    private LoginToProjectListState loginToProjectListState;


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

    public void setRegisterState(RegisterState registerState) {
        this.registerState = registerState;
    }


    public ProjectListState getProjectListState() {
        return projectListState;
    }

    public void setProjectListState(ProjectListState projectListState) {
        this.projectListState = projectListState;
    }

    public LoginToProjectListState getLoginToProjectListState() {
        LoginToProjectListState state = loginToProjectListState;
        loginToProjectListState=null;
        return state;
    }

    public void setLoginToProjectListState(LoginToProjectListState loginToProjectListState) {
        this.loginToProjectListState = loginToProjectListState;
    }
/*
    public CrowdfundingState getCrowdfundingState() {
        return mCrowdfundingState;
    }


    public NextToCrowdfundingState getNextCrowdfundingScreenState() {
        return null;
    }

    public void setNextCrowdfundingScreenState(CrowdfundingToNextState state) {

    }

    public void setPreviousCrowdfundingScreenState(CrowdfundingToPreviousState state) {

    }

    public PreviousToCrowdfundingState getPreviousCrowdfundingScreenState() {
        return null;
    }*/
}