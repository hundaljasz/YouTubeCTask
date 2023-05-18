package com.example.youtubectask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {
    Button signUP;
    EditText name,username,phoneNumber,password,Cpassowrd;
    Uri selectedImage;
    String Name,UserName,Password,CPassword,PhoneNumber;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = new DB(this);
        signUP      = findViewById(R.id.signUp);
        name        = findViewById(R.id.name);
        username    = findViewById(R.id.userName);
        phoneNumber = findViewById(R.id.phoneNumber);
        password    = findViewById(R.id.password);
        Cpassowrd   = findViewById(R.id.cpassword);

        signUP.setOnClickListener(View -> {
            Name = String.valueOf(name.getText());
            UserName = String.valueOf(username.getText());
            Password = String.valueOf(password.getText());
            CPassword = String.valueOf(Cpassowrd.getText());
            PhoneNumber = String.valueOf(phoneNumber.getText());
            if(!Password.equals(CPassword)) {
                Toast.makeText(this, "Password doesn't Matches.", Toast.LENGTH_SHORT).show();
                return;
            } else if(TextUtils.isEmpty(Name.trim())) {
                Toast.makeText(this, "Please Enter Name.", Toast.LENGTH_SHORT).show();
                return;
            } else if(TextUtils.isEmpty(UserName.trim())) {
                Toast.makeText(this, "Please Enter UserName.", Toast.LENGTH_SHORT).show();
                return;
            } else if(TextUtils.isEmpty(PhoneNumber.trim())) {
                Toast.makeText(this, "Please Enter Phone Number.", Toast.LENGTH_SHORT).show();
                return;
            }
            saveDate();
        });
    }

    public void saveDate() {
        User user = new User(Name,UserName, Password, PhoneNumber);
        long result = db.addUser(user);
        if (result > -1) {
            moveToLoginScreen();
        } else {
            Toast.makeText(this, "Something Went Wrong!..., error: " + result, Toast.LENGTH_SHORT).show();
        }
    }

    public void moveToLoginScreen(){

        Toast.makeText(this, "Account Created Successfully!...", Toast.LENGTH_SHORT).show();
        // Set a delay for the launcher screen
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}