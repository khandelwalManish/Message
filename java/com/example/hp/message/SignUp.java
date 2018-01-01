package com.example.hp.message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    Button signUp;
    EditText name, email, phone, uName, pass, rePass;
    String Name, Email, Phone, UName, Pass, RePass;
    TextView warning;
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUp = findViewById(R.id.button3);
        name = findViewById(R.id.editText8);
        email = findViewById(R.id.editText9);
        phone = findViewById(R.id.editText10);
        uName = findViewById(R.id.editText12);
        pass = findViewById(R.id.editText13);
        rePass = findViewById(R.id.editText14);
        warning = findViewById(R.id.textView15);

        Name = name.getText().toString();
        Email = email.getText().toString();
        Phone = phone.getText().toString();
        UName = uName.getText().toString();
        Pass = pass.getText().toString();
        RePass = rePass.getText().toString();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Pass.equals(RePass))
                {
                    Toast.makeText(SignUp.this,"Passwords don't match",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Contact c = new Contact();
                    c.setName(Name);
                    c.setEmail(Email);
                    c.setPhone(Phone);
                    c.setUName(UName);
                    c.setPass(Pass);
                    helper.insertContact(c);
                }

            }
        });
    }
}
