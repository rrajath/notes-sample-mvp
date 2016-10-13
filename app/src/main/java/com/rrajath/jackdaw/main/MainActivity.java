package com.rrajath.jackdaw.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.rrajath.jackdaw.BaseActivity;
import com.rrajath.jackdaw.JackdawApplication;
import com.rrajath.jackdaw.R;
import com.rrajath.jackdaw.notes.NotesActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Inject
    Context context;
    @Inject
    MainActivityPresenter presenter;

    @Bind(R.id.text1)
    TextView textView;
    @Bind(R.id.button)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        textView.setText(context.getApplicationContext().toString());
    }

    @Override
    protected void setupActivityComponent() {
        JackdawApplication
                .get(this)
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @OnClick(R.id.button)
    public void onShowNotesClick() {
        presenter.onShowNotesClick();
    }

    public void showNotesForUser() {
        startActivity(new Intent(this, NotesActivity.class));
    }
}
