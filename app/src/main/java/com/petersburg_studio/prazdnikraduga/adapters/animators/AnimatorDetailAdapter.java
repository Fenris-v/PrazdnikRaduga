package com.petersburg_studio.prazdnikraduga.adapters.animators;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.petersburg_studio.prazdnikraduga.R;

import java.util.List;

public class AnimatorDetailAdapter extends RecyclerView.Adapter<AnimatorDetailAdapter.MyViewHolder> {

    private Context context;
    private List<String> urlList;

    public AnimatorDetailAdapter(Context context, List<String> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    @NonNull
    @Override
    public AnimatorDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animator_image_slider, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimatorDetailAdapter.MyViewHolder holder, int position) {
        if (urlList == null || urlList.isEmpty()) {
            return;
        }
        final int pos = position % urlList.size();
        String url = urlList.get(pos);
        ImageView img = holder.imageView;
        Glide.with(context)
                .load(url)
                .fitCenter()
                .placeholder(R.drawable.cat_wait_rectangle)
                .override(380, 560)
                .into(img);
    }

    @Override
    public int getItemCount() {
        if (urlList != null) {
            return urlList.size();
        } else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
