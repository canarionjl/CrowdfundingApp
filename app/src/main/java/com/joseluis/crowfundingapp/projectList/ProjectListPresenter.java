package com.joseluis.crowfundingapp.projectList;

import com.joseluis.crowfundingapp.app.AppMediator;
import com.joseluis.crowfundingapp.data.ProjectItem;
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

    public ProjectListPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getProjectListState();
    }

    @Override
    public void onStart() {

        }

    @Override
    public void onRestart() {
    }

    @Override
    public void onResume() {
        model.fetchCrowdfundingProjects(new RepositoryContract.GetProjectListCallback() {
            @Override
            public void setProjectList(List<ProjectItem> projects) {
                state.projectList=(ArrayList<ProjectItem>) projects;
            }
        });
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


    @Override
    public void injectView(WeakReference<ProjectListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProjectListContract.Model model) {
        this.model = model;
    }

}