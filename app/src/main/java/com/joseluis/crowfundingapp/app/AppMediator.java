package com.joseluis.crowfundingapp.app;

import com.joseluis.crowfundingapp.login.LoginState;

@SuppressWarnings("unused")
public class AppMediator {

    private static AppMediator INSTANCE;

    private LoginState loginState = new LoginState();

    public LoginState getLoginState() {
        return loginState;
    }

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