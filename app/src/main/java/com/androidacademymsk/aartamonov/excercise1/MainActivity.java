package com.androidacademymsk.aartamonov.excercise1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText messageText;
    private Button previewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageText = findViewById(R.id.message_text);
        previewBtn = findViewById(R.id.preview_btn);

        previewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailMessage = messageText.getText().toString();
                PreviewActivity.start(MainActivity.this, emailMessage);
            }
        });
    }
}
