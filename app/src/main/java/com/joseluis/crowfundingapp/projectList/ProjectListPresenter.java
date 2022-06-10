package com.joseluis.crowfundingapp.projectList;

import android.util.Log;

import com.joseluis.crowfundingapp.app.AppMediator;
import com.joseluis.crowfundingapp.app.LoginToProjectListState;
import com.joseluis.crowfundingapp.app.ProjectListToProjectContentState;
import com.joseluis.crowfundingapp.data.ProjectItem;
import com.joseluis.crowfundingapp.data.UserProjectJoinTable;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ProjectListPresenter implements ProjectListContract.Presenter {

    public static String TAG = ProjectListPresenter.class.getSimpleName();

    private WeakReference<ProjectListContract.View> view;
    private ProjectListState state;
    private ProjectListContract.Model model;
    private AppMediator mediator;


    //CONSTRUCTOR

    public ProjectListPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getProjectListState();
    }




    //ACTIVITY LIFECYCLE METHODS

    @Override
    public void onStart() {
        LoginToProjectListState newState = mediator.getLoginToProjectListState();
        if (newState != null) {
            state.userLogged = newState.userItem;
            state.invitedUser = newState.invitedUser;
        }
        state.favouriteListSelected = false;
    }

    @Override
    public void onRestart() {
    }

    @Override
    public void onResume() {
        fetchCrowdfundingProjects();

        if (isUserLogged()) {
            new Runnable() {
                @Override
                public void run() {
                    while (state.projectList == null) ;
                    getCrowfundingFavouriteProjects();
                }
            }.run();
        }
        view.get().displayProjectListData(state);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onPause() {
        state.favouriteList=null;
        state.projectList=null;
    }

    @Override
    public void onDestroy() {
    }






    //BUTTON CLICKED METHODS

    @Override
    public void onExitOptionItemSelected() {
        state.userLogged = null;
        state.invitedUser = true;
        view.get().navigateToLoginScreen();
    }

    @Override
    public void onFavouriteListOptionItemSelected(boolean favourite) {
        state.favouriteListSelected = favourite;
        view.get().displayProjectListData(state);
    }

    @Override
    public void selectProjectListData(ProjectItem item){
        ProjectListToProjectContentState newState=new ProjectListToProjectContentState();

        newState.invitedUser=state.invitedUser;
        newState.itemClicked=item;
        newState.userLogged= state.userLogged;

        boolean favourite;
        if(!state.invitedUser) {
            favourite = isProjectFavourite(item);
        } else {
            favourite = false;
        }
        newState.itemIsFavourite=favourite;

        mediator.setProjectListToProjectContentState(newState);
        navigateToDetailProjectScreen();
    }

    private void navigateToDetailProjectScreen() {
        view.get().navigateToDetailProjectScreen();
    }






    // DATA GETTING METHODS

    @Override
    public boolean isUserLogged() {
        return !state.invitedUser;
    }

    public void fetchCrowdfundingProjects() {

        model.fetchCrowdfundingProjects(new RepositoryContract.GetProjectListCallback() {

            @Override
            public void setProjectList(List<ProjectItem> projects) {
                state.projectList = projects;
                view.get().displayProjectListData(state);
            }
        });
    }

    public void getCrowfundingFavouriteProjects() {
        model.getProjectsUserJoinList(state.userLogged.id, new RepositoryContract.GetUserToProjectListCallback() {

            @Override
            public void setUserToProjectList(List<UserProjectJoinTable> favouriteProjects) {
                if(favouriteProjects!=null) {
                    state.favouriteList=userProjectJoinTableIterator(favouriteProjects);
                } else {

                }

            }
        });
    }






    //AUXILIAR DATA MANIPULATION

    public ArrayList<ProjectItem> userProjectJoinTableIterator(List<UserProjectJoinTable> favouriteProjects) {
        ArrayList<ProjectItem> list = new ArrayList<>();
        for (UserProjectJoinTable favouriteId : favouriteProjects) {
            list.add(searchProjectById(favouriteId.projectId));
        }
        return list;
    }


    public ProjectItem searchProjectById(int id) {
        if(state.projectList==null) Log.e(TAG,"null en project list");
        for (ProjectItem item : state.projectList) {
            if (item.id == id) {
                return item;
            }
        }
        return null;
    }

    public boolean isProjectFavourite(ProjectItem item){
            for (ProjectItem project : state.favouriteList) {
                if (project.id == item.id) return true;

        }
        return false;
    }






    //MVP METHODS

    @Override
    public void injectView(WeakReference<ProjectListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProjectListContract.Model model) {
        this.model = model;
    }

}