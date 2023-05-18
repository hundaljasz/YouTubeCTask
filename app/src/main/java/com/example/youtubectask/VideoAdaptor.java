package com.example.youtubectask;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VideoAdaptor extends RecyclerView.Adapter<VideoAdaptor.VideoViewHolder>{
    private Context context;
    private List<Videos> V;
    public VideoAdaptor(Context context, List<Videos> video) {
        this.context = context;
        this.V = video;
    }
    @NonNull
    @Override
    public VideoAdaptor.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.video_recycler, parent, false);
        return new VideoViewHolder(itemView,V,context);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdaptor.VideoViewHolder holder, int position) {
        holder.VL.setText(V.get(position).getLink());
    }

    @Override
    public int getItemCount() {
        return V.size();
    }
    public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        List<Videos> v;
        TextView VL;
        private Context mContext;
        public VideoViewHolder(@NonNull View itemView, List<Videos> V,Context context) {
            super(itemView);
            VL = itemView.findViewById(R.id.video_link);
            this.v = V;
            this.mContext = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            Intent intent = new Intent(mContext, PlayerActivty.class);
            intent.putExtra("YID", v.get(position).getVideo_id());
            mContext.startActivity(intent);
        }
    }
}
