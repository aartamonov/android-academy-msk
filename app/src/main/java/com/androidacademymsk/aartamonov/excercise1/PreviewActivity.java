package com.androidacademymsk.aartamonov.excercise1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewActivity extends AppCompatActivity {

    private static final String EXTRA_USER_MESSAGE = "EXTRA_USER_MESSAGE";
    private static final String[] EMAIL_ADDRESS = {"andr.academy.msk@gmail.com"};
    private static final String EMAIL_SUBJECT = "Hello, Android Academy MSK!";
    private static final String MAILTO = "mailto:";
    private static final String START_EMAILING = "Start Emailing...";
    private static final String EMAIL_ERROR = "No Email app found";

    private TextView emailView;
    private Button emailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        emailView = findViewById(R.id.message_view);
        emailBtn = findViewById(R.id.email_btn);

        final String userMessage = getIntent().getStringExtra(EXTRA_USER_MESSAGE);

        emailView.setText(userMessage);

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageEmail = emailView.getText().toString();
                composeEmail(EMAIL_ADDRESS, EMAIL_SUBJECT, messageEmail);
            }
        });
    }

    private void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(MAILTO));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            Toast.makeText(getApplicationContext(), START_EMAILING, Toast.LENGTH_LONG).show();
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), EMAIL_ERROR, Toast.LENGTH_LONG).show();
        }
    }

    public static void start(Activity activity, String emailMessage) {
        final Intent intent = new Intent(activity, PreviewActivity.class);
        intent.putExtra(EXTRA_USER_MESSAGE, emailMessage);
        activity.startActivity(intent);
    }
}
