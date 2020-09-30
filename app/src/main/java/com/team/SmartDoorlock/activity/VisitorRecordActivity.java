package com.team.SmartDoorlock.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.team.SmartDoorlock.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class VisitorRecordActivity extends AppCompatActivity {
    Button readfile;
    TextView recordtext;

    private static final String FILE_NAME="testdirectory.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_record);



        readfile=(Button)findViewById(R.id.readfile);
        recordtext=(TextView)findViewById(R.id.text);
        recordtext.setMovementMethod(new ScrollingMovementMethod());





        readfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fis=null;
                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr=new InputStreamReader(fis);
                    BufferedReader br =new BufferedReader(isr);
                    StringBuilder sb=new StringBuilder();
                    String text;
                    while((text=br.readLine())!=null){
                        sb.append(text).append("\n");
                    }
                    recordtext.setText(sb.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(fis!=null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}