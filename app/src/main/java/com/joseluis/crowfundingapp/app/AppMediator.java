package com.joseluis.crowfundingapp.app;

@SuppressWarnings("unused")
public class AppMediator {

    private static AppMediator INSTANCE;

    //private CrowdfundingState mCrowdfundingState;


    private AppMediator() {

    }


    public static AppMediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppMediator();
        }

        return INSTANCE;
    }

    // to reset the state when testing
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