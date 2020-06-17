package com.example.myvideoplayer;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private List<Video> videoList;

    public VideoAdapter(List<Video> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);//用子布局初始化数据仓库
        holder.videoview.setOnClickListener(new View.OnClickListener() {//如果整个子布局被点击了触发新东西
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Video video=videoList.get(position);
                String myvideoname=video.getVideoname();
                String myvideourl=video.getVideourl();
                Intent intent=new Intent(v.getContext(),PlayActivity.class);
                intent.putExtra("videoname", myvideoname);
                intent.putExtra("videourl",myvideourl);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        Video video=videoList.get(position);//根据点击位置赋予数据值
        holder.video_name.setText(video.getVideoname());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView video_name;
        View videoview;
        public ViewHolder(@NonNull View view) {
            super(view);
            video_name=view.findViewById(R.id.video_name);
            videoview=view;
        }
    }
}
