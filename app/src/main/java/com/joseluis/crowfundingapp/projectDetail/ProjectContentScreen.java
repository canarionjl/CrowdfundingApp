package com.joseluis.crowfundingapp.projectDetail;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

public class ProjectContentScreen {

    public static void configure(ProjectContentContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);
/*

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        ProjectContentContract.Presenter presenter = new ProjectContentPresenter(mediator);
        ProjectContentContract.Model model = new ProjectContentModel(data);
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
*/

    }
}