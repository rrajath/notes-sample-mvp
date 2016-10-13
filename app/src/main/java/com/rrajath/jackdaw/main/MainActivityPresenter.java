package com.rrajath.jackdaw.main;

public class MainActivityPresenter {
    MainActivity activity;

    public MainActivityPresenter(MainActivity activity) {
        this.activity = activity;
    }

    public void onShowNotesClick() {
        activity.showNotesForUser();
    }
}
