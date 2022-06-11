package com.joseluis.crowfundingapp.projectDetail;

import android.util.Log;
import android.widget.Toast;

import com.joseluis.crowfundingapp.app.AppMediator;
import com.joseluis.crowfundingapp.app.LoginToProjectListState;
import com.joseluis.crowfundingapp.app.ProjectListToProjectContentState;
import com.joseluis.crowfundingapp.data.UserProjectJoinTable;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectContentPresenter implements ProjectContentContract.Presenter {

    public static String TAG = ProjectContentPresenter.class.getSimpleName();

    private WeakReference<ProjectContentContract.View> view;
    private ProjectContentState state;
    private ProjectContentContract.Model model;
    private AppMediator mediator;

    public ProjectContentPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getProjectContentState();
    }

    @Override
    public void onStart() {
    }

   @Override
    public void onRestart() {
    }

    @Override
    public void onResume() {
       recoverDataFromProjectListState();
       view.get().onDataUpdated(state);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onDestroy() {
    }



    //DATA SETTING/GETTING METHODS

    public void addProjectToFavourite(int userId,int projectId){
        model.insertUserToProjectRelationship(userId,projectId);
    }

    public void deleteProjectToFavourite (int userId, int projectId){
        model.deleteUserToProjectRelationship(userId,projectId);
    }

    public void recoverDataFromProjectListState(){
        ProjectListToProjectContentState newState = mediator.getProjectListToProjectContentState();

       if (newState != null){

            state.invitedUser=newState.invitedUser;
            state.projectItem=newState.itemClicked;
            state.userLogged=newState.userLogged;

            state.title = newState.itemClicked.title;
            state.date = getStringDateFromMiliseconds(newState.itemClicked.date);
            state.description = newState.itemClicked.description;
            state.imageUrl = newState.itemClicked.picture;

            state.isFavourite=newState.itemIsFavourite;
        }
    }




    //AUXILIAR DATA MANIPULATION

    public String getStringDateFromMiliseconds(long miliseconds){
        Date date = new Date(miliseconds);

        int dayInt = date.getDate();
        String day="";
        if(dayInt<10) {
            day = "0";
        }
        day = day+(dayInt);


        int monthInt = date.getMonth();
        String month="";
        if(monthInt<10) month = "0";
        month = month+(monthInt+1);

        String year = Integer.toString(date.getYear()+1900);

        int hourInt = date.getHours();
        String hour="";
        if(hourInt<10) hour = "0";
        hour = hour+(hourInt);

        int minutesInt = date.getMinutes();
        String minutes="";
        if(minutesInt<10) minutes = "0";
        minutes = minutes+(minutesInt);

        int secondsInt = date.getSeconds();
        String seconds="";
        if(secondsInt<10) seconds = "0";
        seconds = seconds+(secondsInt);

        String dateString = day+"/"+month+"/"+year+" "+hour+":"+minutes+":"+seconds;

        return dateString;
    }



    //BUTTON CLICKED METHODS

    @Override
    public void onAddFavouriteButtonClicked(){
        if(!state.invitedUser) {
            if(!state.isFavourite) {
                addProjectToFavourite(state.userLogged.id, state.projectItem.id);
            }else{
                deleteProjectToFavourite(state.userLogged.id,state.projectItem.id);
            }
            state.isFavourite=!state.isFavourite;
            view.get().updateFavouriteButtonState(state);
        }
    }

    public void configureProjectContactCall(){
        view.get().makeCall(state.projectItem.phoneContact);
    }

    public void onMapButtonClicked(){
        view.get().showMap(state.projectItem.latitude,state.projectItem.longitude,state.title);
    }

    public void onAddCalendarButtonClicked(){
        view.get().addEventToCalendar(state.title, state.projectItem.date);
    }




    //MVP METHODS

    @Override
    public void injectView(WeakReference<ProjectContentContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProjectContentContract.Model model) {
        this.model = model;
    }
}