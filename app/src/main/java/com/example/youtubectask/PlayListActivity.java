package com.example.youtubectask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class PlayListActivity extends AppCompatActivity {
    RecyclerView videoRecyclerView;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    List<Videos> videoList;
    VideoAdaptor VA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        DB db = new DB(this);
        videoList = db.getVideos();
        videoRecyclerView = findViewById(R.id.VR);
        VA = new VideoAdaptor( PlayListActivity.this, videoList);
        videoRecyclerView.setAdapter(VA);
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        videoRecyclerView.setLayoutManager(recyclerViewLayoutManager);
    }
}