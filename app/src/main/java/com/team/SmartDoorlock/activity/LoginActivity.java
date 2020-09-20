package com.team.SmartDoorlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.team.SmartDoorlock.R;
import com.team.SmartDoorlock.data.LoginData;
import com.team.SmartDoorlock.data.LoginResponse;
import com.team.SmartDoorlock.network.RetrofitClient;
import com.team.SmartDoorlock.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView logintitle;
    private EditText uid;
    private EditText password;
    private Button loginbutton;
    private Button joinbutton;
    private Button faqbutton;
    private ServiceApi service;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logintitle = (TextView) findViewById(R.id.logintitle);
        uid = (EditText) findViewById((R.id.Uid));
        password = (EditText) findViewById(R.id.Password);
        loginbutton = (Button) findViewById(R.id.signinbutton);
        joinbutton = (Button) findViewById(R.id.joinbutton);
        faqbutton = (Button) findViewById(R.id.faqbutton);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        joinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        faqbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FAQActivity.class);
                startActivity(intent);
            }
        });
    }

    private void attemptLogin() {
        uid.setError(null);
        password.setError(null);

        String uid_string = uid.getText().toString();
        String password_string = password.getText().toString();
        boolean cancel = false;
        View focusView = null;

        // 아이디의 유효성 검사
        if (uid_string.isEmpty()) {
            uid.setError("아이디를 입력해주세요.");
            focusView = uid;
            cancel = true;
        }

        // 패스워드의 유효성 검사
        if (password_string.isEmpty()) {
            password.setError("이름을 입력해주세요.");
            focusView = password;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(uid_string, password_string));
        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }
}
