package com.example.homemadeproductssale.NavigationDrawer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homemadeproductssale.R;

public class VotesActivity extends AppCompatActivity {

    EditText textView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votes);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        findViewById(R.id.recieveText);

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                    handleSendText(intent);
            }
        }
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            Log.d("Shared text is", sharedText);
            textView.setText(sharedText);
        }
    }

}
