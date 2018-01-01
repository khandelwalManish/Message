package com.example.hp.message;
import java.lang.*;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    DatabaseHelper helper = new DatabaseHelper(this);
    Button btn_Login, btn_signUpHere;
    EditText username,password;
    TextView warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_signUpHere= findViewById(R.id.button2);
        username =findViewById(R.id.editText);
        password= findViewById(R.id.editText2);
        warning = findViewById(R.id.textView3);
        btn_Login = findViewById(R.id.login);
        final String passwrd = password.getText().toString();
        final String str = username.getText().toString();
        btn_signUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , SignUp.class));
            }
        });


        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String pass =  helper.searchPass(str);

                  startActivity(new Intent(MainActivity.this , Logged.class));


            }
        });


    }

}