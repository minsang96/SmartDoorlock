package com.team.SmartDoorlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.team.SmartDoorlock.R;

public class FAQActivity extends AppCompatActivity {
    private TextView FAQtitle;
    private TextView Did_title;
    private TextView Title_title;
    private TextView Email_title;
    private TextView Content_title;
    private TextView Did;
    private EditText Title;
    private EditText Email;
    private EditText Content;
    private Button FAQbutton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setContentView(R.layout.activity_faq);

        FAQtitle = (TextView) findViewById(R.id.FAQtitle);
        Did_title = (TextView) findViewById(R.id.Did_title);
        Title_title = (TextView) findViewById(R.id.Title_title);
        Email_title = (TextView) findViewById(R.id.Email_title);
        Content_title = (TextView) findViewById(R.id.Content_title);
        Did = (TextView) findViewById(R.id.Did);
        Title = (EditText) findViewById(R.id.Title);
        Email = (EditText) findViewById(R.id.Email);
        Content = (EditText) findViewById(R.id.Content);
        FAQbutton = (Button) findViewById(R.id.FAQButton);*/


        /*FAQbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FAQActivity.this, "버튼 눌렸음", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}
