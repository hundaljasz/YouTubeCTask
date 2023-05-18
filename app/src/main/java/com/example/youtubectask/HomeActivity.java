package com.example.youtubectask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeActivity extends AppCompatActivity {
    Button play, ATP, MYP;
    EditText URL;
    String YURL,YID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        URL  = findViewById(R.id.youtubeURL);
        play = findViewById(R.id.play);
        ATP  = findViewById(R.id.ATP);
        MYP  = findViewById(R.id.MPlay);
        DB db = new DB(this);

        play.setOnClickListener(view -> {
            if(getYoutubeLink() == null){
                return;
            }
            Intent playerIntent = new Intent(HomeActivity.this, PlayerActivty.class);
            playerIntent.putExtra("YID",YID);
            startActivity(playerIntent);
        });

        ATP.setOnClickListener(view -> {
            if(getYoutubeLink() == null){
                return;
            }
            db.addVideo(YID,YURL);
            Toast.makeText(this, "Video added to playList Successfully!...", Toast.LENGTH_SHORT).show();
        });

        MYP.setOnClickListener(view -> {
            Intent PlaylistIntent = new Intent(HomeActivity.this,PlayListActivity.class);
            startActivity(PlaylistIntent);
        });
    }

    public static String extractVideoId(String youtubeLink) {
        String videoId = null;
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v=|v=|\\/v\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|v%2F)[^#\\&\\?\\n]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youtubeLink); //url is youtube url for which you want to extract the video id.
        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }

    public String getYoutubeLink() {
        YURL = String.valueOf(URL.getText()).trim();
        if(TextUtils.isEmpty(YURL)) {
            Toast.makeText(this, "Please enter YouTube URL!...", Toast.LENGTH_SHORT).show();
            return null;
        }
        YID = extractVideoId(YURL);
        if (YID == null) {
            Toast.makeText(this, "Enter Valid YouTube URL!...", Toast.LENGTH_SHORT).show();
            return null;
        }
        return YID;
    }
}