package com.pulkit.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {

    Button callSign;
    TextInputLayout username1,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callSign=findViewById(R.id.signup);


        callSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this,SignUP2.class);
                startActivity(intent);

            }
        });
    }




    private Boolean validatePassword()
    {
        String val=pass.getEditText().getText().toString();

        if(val.isEmpty())
        {
            pass.setError("Field cannot be empty");
            return false;

        }

        else
        {
            pass.setError(null);
            pass.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUsername()
    {
        String val=username1.getEditText().getText().toString();

        if(val.isEmpty())
        {
            username1.setError("Field cannot be empty");
            return false;

        }
        else if(val.length()>=20)
        {
            username1.setError("Username too long");
            return false;
        }

        else
        {
            username1.setError(null);
            username1.setErrorEnabled(false);
            return true;
        }
    }
    public void Login(View  v) {
        if (!validatePassword() | !validateUsername())
        {
            return;
        }
        else{
            isUser();
        }

    }

    private void isUser()
    {
        final String user=username1.getEditText().getText().toString().trim();
        final String pass1=pass.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query check= reference.orderByChild("username1").equalTo(user);
        check.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    username1.setError(null);
                    username1.setErrorEnabled(false);
                    String passF = dataSnapshot.child(user).child("pass").getValue(String.class);
                    if (passF.equals(user)) {
                        pass.setError(null);
                        pass.setErrorEnabled(false);
                        String nameDB = dataSnapshot.child(user).child("user").child("name").getValue(String.class);
                        String emailDB = dataSnapshot.child(user).child("user").child("email").getValue(String.class);
                        String userDB = dataSnapshot.child(user).child("user").child("username").getValue(String.class);
                        String phoneDB = dataSnapshot.child(user).child("user").child("phone").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        startActivity(intent);


                    } else {
                        pass.setError("Wrong Password");
                        pass.requestFocus();

                    }
                } else
                {
                    username1.setError("No Such user");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}