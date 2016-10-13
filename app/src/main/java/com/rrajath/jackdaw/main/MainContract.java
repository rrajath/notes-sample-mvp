package com.rrajath.jackdaw.main;

import com.rrajath.jackdaw.BasePresenter;
import com.rrajath.jackdaw.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }

}
