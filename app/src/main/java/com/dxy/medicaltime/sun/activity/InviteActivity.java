package com.dxy.medicaltime.sun.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dxy.medicaltime.R;

public class InviteActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private Button button;
    private String inviteCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        editText= (EditText) findViewById(R.id.invite_code);
        button= (Button) findViewById(R.id.make_sure);
        button.setOnClickListener(this);
        init();
    }
    public void init(){
        inviteCode=editText.getText().toString();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this,inviteCode,Toast.LENGTH_SHORT).show();
    }
    public void goback(View view){
        finish();
    }
}
