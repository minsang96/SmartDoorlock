package com.team.SmartDoorlock.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.team.SmartDoorlock.R;
import com.team.SmartDoorlock.activity.CameraActivity;
import com.team.SmartDoorlock.activity.LoginActivity;
import com.team.SmartDoorlock.activity.MainActivity;
import com.team.SmartDoorlock.activity.SettingActivity;
import com.team.SmartDoorlock.activity.VisitorRecordActivity;

import java.util.ArrayList;

/**
 * Created by SM-PC on 2018-04-11.
 */

public class WrittingAdapter extends RecyclerView.Adapter<WrittingAdapter.MyViewholder> {

    private Activity activity;
    private ArrayList<Itemform> datalist;
    private Intent intent;

    //getItemCount, onCreateViewHolder, MyViewHolder, onBindViewholder 순으로 들어오게 된다.
    // 뷰홀더에서 초기세팅해주고 바인드뷰홀더에서 셋텍스트해주는 값이 최종적으로 화면에 출력되는 값


    @Override
    public WrittingAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);//뷰 생성(아이템 레이아웃을 기반으로)
        MyViewholder viewholder1 = new MyViewholder(view);//아이템레이아웃을 기반으로 생성된 뷰를 뷰홀더에 인자로 넣어줌


        return viewholder1;
    }

    @Override
    public void onBindViewHolder(WrittingAdapter.MyViewholder holder, int position) {
        Itemform data = datalist.get(position);//위치에 따라서 그에 맞는 데이터를 얻어오게 한다.
        holder.personalId.setText(data.getId());//앞서 뷰홀더에 세팅해준 것을 각 위치에 맞는 것들로 보여주게 하기 위해서 세팅해준다.
        holder.profile.setImageResource(data.getImageNumber());
        holder.writtingTxt.setText(data.getTxt());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
    public class MyViewholder extends RecyclerView.ViewHolder
    {
        ImageView profile;
        TextView writtingTxt;
        TextView personalId;

        public MyViewholder(View itemview){
            super(itemview);

            profile = (ImageView) itemview.findViewById(R.id.recyclerImage);
            writtingTxt = (TextView) itemview.findViewById(R.id.recylcerExp);
            personalId = (TextView) itemview.findViewById(R.id.recyclerText);
            itemview.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(personalId.getText()=="설정"){
                        intent = new Intent(v.getContext(), SettingActivity.class);
                        v.getContext().startActivity(intent);
                    }

                    else if(personalId.getText()=="카메라 연결"){
                        intent = new Intent(v.getContext(), CameraActivity.class);
                        v.getContext().startActivity(intent);
                    }

                    else if(personalId.getText()=="방문자 기록"){
                        intent = new Intent(v.getContext(), VisitorRecordActivity.class);
                        v.getContext().startActivity(intent);
                    }

                    else if (personalId.getText()=="문의하기"){
                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.setType("plain/text");
                        String[] address = {"dbalstkd89@naver.com"};
                        email.putExtra(Intent.EXTRA_EMAIL, address);
                        email.putExtra(Intent.EXTRA_SUBJECT, "제목을 입력해 주세요.");
                        email.putExtra(Intent.EXTRA_TEXT, "제품코드 :               연락처 :               사유 : ");
                        activity.startActivity(email);
                    }

                    else if(personalId.getText()=="로그아웃"){
                        intent = new Intent(v.getContext(), LoginActivity.class);
                        v.getContext().startActivity(intent);
                        activity.finish();
                    }
                }
            });

        }

    }
    public WrittingAdapter(Activity activity, ArrayList<Itemform> datalist){
        this.activity = activity;//보여지는 액티비티
        this.datalist = datalist;//내가 처리하고자 하는 아이템들의 리스트

    }
}
