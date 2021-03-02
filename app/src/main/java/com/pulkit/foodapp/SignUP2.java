package com.pulkit.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUP2 extends AppCompatActivity {
    TextInputLayout regName, regUsername, regEmail, regPhone ,regPassword;
    Button regBtn, regTologin;
    FirebaseDatabase rootnode;
    DatabaseReference ref;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_u_p2);

        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.Email);
        regPhone = findViewById(R.id.phone);
        regPassword = findViewById(R.id.password1);
        regBtn = findViewById(R.id.GO);
        regTologin = findViewById(R.id.login);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateName()|!validatePassword()|!validatePhone()|!validateUsername())
                {
                    return;
                }
                else {


                    rootnode = FirebaseDatabase.getInstance();
                    ref = rootnode.getReference("users");


                    String name = regName.getEditText().getText().toString();
                    String username = regUsername.getEditText().getText().toString();
                    String email = regEmail.getEditText().getText().toString();
                    String phone = regPhone.getEditText().getText().toString();
                    String pass = regPassword.getEditText().getText().toString();
                    Intent intent=new Intent(getApplicationContext(),VerifyPhone.class);
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                    // userhelperlass helper = new userhelperlass(name, username, email, phone, pass);
                    //ref.child(phone).setValue(helper);
                    // Intent intent= new Intent(SignUP.this,MainActivity.class);
                    //startActivity(intent);
                }
            }
        });
    }


    private Boolean validateName()
    {
        String val=regName.getEditText().getText().toString();
        if(val.isEmpty())
        {
            regName.setError("Field cannot be empty");
            return false;

        }
        else
        {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword()
    {
        String val=regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if(val.isEmpty())
        {
            regPassword.setError("Field cannot be empty");
            return false;

        }
        else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        }
        else
        {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUsername()
    {
        String val=regUsername.getEditText().getText().toString();

        if(val.isEmpty())
        {
            regUsername.setError("Field cannot be empty");
            return false;

        }
        else if(val.length()>=20)
        {
            regUsername.setError("Username too long");
            return false;
        }

        else
        {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validatePhone()
    {
        String val=regPhone.getEditText().getText().toString();
        if(val.isEmpty())
        {
            regPhone.setError("Field cannot be empty");
            return false;

        }
        else
        {
            regPhone.setError(null);
            regPhone.setErrorEnabled(false);
            return true;
        }
    }
}