package com.joseluis.crowfundingapp.projectList;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

public class ProjectListScreen {

    public static void configure(ProjectListContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        ProjectListContract.Presenter presenter = new ProjectListPresenter(mediator);
        ProjectListContract.Model model = new ProjectListModel(data);
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}