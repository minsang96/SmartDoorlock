package com.team.SmartDoorlock.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.team.SmartDoorlock.R;
import com.team.SmartDoorlock.recyclerview.Itemform;
import com.team.SmartDoorlock.recyclerview.WrittingAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Itemform> list = new ArrayList<>();
        list.add(new Itemform("설정",R.drawable.setting,"어플리케이션을 설정합니다."));
        list.add(new Itemform("카메라 연결",R.drawable.cam,"도어락 앞의 화면을 카메라를 통해 확인합니다."));
        list.add(new Itemform("방문자 기록",R.drawable.log,"도어락이 열리고 닫힌 기록을 확인합니다."));
        list.add(new Itemform("로그아웃",R.drawable.logout,"로그아웃 합니다."));

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recyclerview) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        WrittingAdapter adapter = new WrittingAdapter(this, list) ;
        recyclerView.setAdapter(adapter) ;

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }


}
