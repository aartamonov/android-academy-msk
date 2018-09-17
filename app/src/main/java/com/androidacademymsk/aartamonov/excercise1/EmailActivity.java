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

public class EmailActivity extends AppCompatActivity {

    private static final String EXTRA_USER_MESSAGE = "EXTRA_USER_MESSAGE";
    private static final String[] EMAIL_ADDRESS = {"andr.academy.msk@gmail.com"};
    private static final String EMAIL_SUBJECT = "Hello, Android Academy MSK!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        final TextView emailView = findViewById(R.id.emailView);
        final Button emailBtn = findViewById(R.id.emailBtn);

        final String userMessage = getIntent().getStringExtra(EXTRA_USER_MESSAGE);

        emailView.setText(userMessage);

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageEmail = emailView.getText().toString();
                composeEmail(EMAIL_ADDRESS, EMAIL_SUBJECT, messageEmail);
            }

            public void composeEmail(String[] addresses, String subject, String body) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    Toast.makeText(getApplicationContext(), "Start Emailing...", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No Email app found", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void start(Activity activity, String emailMessage) {
        final Intent intent = new Intent(activity, EmailActivity.class);
        intent.putExtra(EXTRA_USER_MESSAGE, emailMessage);
        activity.startActivity(intent);
    }
}
