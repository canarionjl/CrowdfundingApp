package com.joseluis.crowfundingapp.login;

import androidx.fragment.app.FragmentActivity;

import com.joseluis.crowfundingapp.R;
import com.joseluis.crowfundingapp.app.AppMediator;
import com.joseluis.crowfundingapp.database.CrowdfundingRepository;
import com.joseluis.crowfundingapp.database.RepositoryContract;

import java.lang.ref.WeakReference;

public class LoginScreen {

    public static void configure(LoginContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

       String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        LoginContract.Presenter presenter = new LoginPresenter(mediator);
        RepositoryContract repository = CrowdfundingRepository.getInstance(context.get());
        LoginContract.Model model = new LoginModel(repository);
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}