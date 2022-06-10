package com.joseluis.crowfundingapp.projectDetail;

import androidx.fragment.app.FragmentActivity;

import com.joseluis.crowfundingapp.R;
import com.joseluis.crowfundingapp.app.AppMediator;
import com.joseluis.crowfundingapp.database.CrowdfundingRepository;

import java.lang.ref.WeakReference;

public class ProjectContentScreen {

    public static void configure(ProjectContentContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();

        ProjectContentContract.Presenter presenter = new ProjectContentPresenter(mediator);
        ProjectContentContract.Model model = new ProjectContentModel(CrowdfundingRepository.getInstance(context.get()));
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}