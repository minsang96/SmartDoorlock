package com.team.SmartDoorlock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team.SmartDoorlock.R;
import com.team.SmartDoorlock.etc.BackPressHandler;
import com.team.SmartDoorlock.recyclerview.Itemform;
import com.team.SmartDoorlock.recyclerview.WrittingAdapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button Lockbutton;
    String TextRecord;
    private static final String FILE_NAME="testdirectory.txt";
    private BackPressHandler backPressHandler = new BackPressHandler(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lockbutton=(Button)findViewById(R.id.Lockbutton);

        ArrayList<Itemform> list = new ArrayList<>();
        list.add(new Itemform("설정",R.drawable.setting,"어플리케이션을 설정합니다."));
        list.add(new Itemform("카메라 연결",R.drawable.cam,"도어락 앞의 화면을 카메라를 통해 확인합니다."));
        list.add(new Itemform("방문자 기록",R.drawable.log,"도어락이 열리고 닫힌 기록을 확인합니다."));
        list.add(new Itemform("문의하기",R.drawable.mail,"제품 이용에 대해 문의합니다."));
        list.add(new Itemform("로그아웃",R.drawable.logout,"로그아웃 합니다."));

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recyclerview) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        WrittingAdapter adapter = new WrittingAdapter(this, list) ;
        recyclerView.setAdapter(adapter) ;

        // 리사이클러뷰 구분선 설정
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

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
            TextRecord=sb.toString();
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



        Lockbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date currentTime = Calendar.getInstance().getTime();
                FileOutputStream fos=null;
                // 현재시간을 msec 으로 구한다.
                long now = System.currentTimeMillis();
                // 현재시간을 date 변수에 저장한다.
                Date date = new Date(now);
                // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                // nowDate 변수에 값을 저장한다.
                String formatDate = sdfNow.format(date);
                TextRecord=TextRecord+formatDate+"  이름"+"\n";

                try {
                    fos=openFileOutput(FILE_NAME,MODE_PRIVATE);
                    fos.write(TextRecord.getBytes());
                    Toast.makeText(MainActivity.this, "파일저장",Toast.LENGTH_LONG).show();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fos !=null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }

    public void onBackPressed() {
        backPressHandler.onBackPressed("뒤로가기를 한번 더 누르면 종료합니다.", 3000);
    }
}
