package com.team.SmartDoorlock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.team.SmartDoorlock.R;
import com.team.SmartDoorlock.data.JoinData;
import com.team.SmartDoorlock.data.JoinResponse;
import com.team.SmartDoorlock.network.RetrofitClient;
import com.team.SmartDoorlock.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JoinActivity extends AppCompatActivity {
    private TextView title;
    private TextView caution;
    private EditText did;
    private EditText uid;
    private EditText pwd;
    private EditText pnumber;
    private EditText relation;
    private Button capturebutton;
    private Button imageloadbutton;
    private Button signinbutton;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        title = (TextView) findViewById(R.id.title);
        caution = (TextView) findViewById(R.id.caution);
        did = (EditText) findViewById((R.id.Did));
        pwd = (EditText) findViewById((R.id.pwd));
        uid = (EditText) findViewById(R.id.UID);
        pnumber = (EditText) findViewById(R.id.Pnumber);
        relation = (EditText) findViewById(R.id.Relation);
        signinbutton = (Button) findViewById(R.id.signinbutton);
        capturebutton = (Button) findViewById(R.id.Capturebutton);
        imageloadbutton = (Button) findViewById(R.id.Imageloadbutton);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptJoin();
            }
        });

        capturebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

        imageloadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    private void attemptJoin() {
        did.setError(null);
        uid.setError(null);
        pwd.setError(null);
        pnumber.setError(null);
        relation.setError(null);

        String did_string = did.getText().toString();
        String uid_string = uid.getText().toString();
        String pwd_string = uid.getText().toString();
        String pnumber_string = pnumber.getText().toString();
        String relation_string = relation.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (did_string.isEmpty()) {
            did.setError("제품코드를 입력해주세요.");
            focusView = did;
            cancel = true;
        } else if (!isDidValid(did_string)) {
            did.setError("12자리 이상의 유효한 제품코드를 입력해주세요.");
            focusView = did;
            cancel = true;
        }

        // 이름의 유효성 검사
        if (uid_string.isEmpty()) {
            uid.setError("이름을 입력해주세요.");
            focusView = uid;
            cancel = true;
        }

        // 패스워드의 유효성 검사
        if (pwd_string.isEmpty()) {
            pwd.setError("비밀번호를 입력해주세요.");
            focusView = pwd;
            cancel = true;
        }

        // 전화번호의 유효성 검사
        if (pnumber_string.isEmpty()) {
            pnumber.setError("전화번호를 입력해주세요.");
            focusView = pnumber;
            cancel = true;
        }

        // 관계의 유효성 검사
        if (relation_string.isEmpty()) {
            relation.setError("관계를 입력해주세요.");
            focusView = relation;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(did_string, uid_string, pwd_string, pnumber_string, relation_string));
        }
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isDidValid(String did) {
        return did.length() >= 12;
    }
}
