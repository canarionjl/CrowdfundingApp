package com.joseluis.crowfundingapp.register;

import androidx.fragment.app.FragmentActivity;

import com.joseluis.crowfundingapp.app.AppMediator;
import com.joseluis.crowfundingapp.database.CrowdfundingRepository;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.lang.ref.WeakReference;

public class RegisterScreen {

    public static void configure(RegisterContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();

        RegisterContract.Presenter presenter = new RegisterPresenter(mediator);
        RegisterContract.Model model = new RegisterModel(CrowdfundingRepository.getInstance(context.get()));
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}