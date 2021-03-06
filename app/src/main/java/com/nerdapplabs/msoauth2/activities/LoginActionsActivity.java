package com.nerdapplabs.msoauth2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nerdapplabs.msoauth2.R;
import com.nerdapplabs.msoauth2.utility.Duration;
import com.nerdapplabs.msoauth2.utility.ErrorType;
import com.nerdapplabs.msoauth2.utility.MessageSnackbar;
import com.nerdapplabs.msoauth2.utility.Preferences;

public class LoginActionsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignup, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actions);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignup = (Button) findViewById(R.id.btn_signup);

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
            mTitle.setText(getString(R.string.login_actions_activity_title));
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String successMessage = getIntent().getStringExtra("EMAIL_SENT_MESSAGE");
        if (null != successMessage) {
            // clear preferences
            Preferences.clear();
            MessageSnackbar.with(this, null).type(ErrorType.WARNING).message(successMessage)
                    .duration(Duration.LONG).show();
            getIntent().removeExtra("EMAIL_SENT_MESSAGE");
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_signup:
                intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login:
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
        return super.onOptionsItemSelected(item);
    }
}
