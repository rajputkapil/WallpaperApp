package com.wallpapers.app.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wallpapers.app.R;
import com.wallpapers.app.activity.ShowDetail;
import com.wallpapers.app.model.Photo;
import com.wallpapers.app.model.Wallpapers;

import java.util.ArrayList;
import java.util.List;

public class WallpapersAdapter extends RecyclerView.Adapter<WallpapersAdapter.WallPaperHolder> {

    private boolean isLoadingAdded = false;
    Activity activity;
    List<Photo> wallpapers;

    public static class WallPaperHolder extends RecyclerView.ViewHolder {
        TextView photoGrapherName;
        ImageView wallPaperImg;
        LinearLayout detailView;

        public WallPaperHolder(View v) {
            super(v);
            photoGrapherName = v.findViewById(R.id.photographer_name_txt);
            wallPaperImg = v.findViewById(R.id.wallpaper_img);
            detailView = v.findViewById(R.id.detail_view);
        }
    }

    public void setAdapterDatasetAdapterData(List<Photo> wallpapers) {
        this.wallpapers = wallpapers;
    }

    public WallpapersAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public WallpapersAdapter.WallPaperHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpaper_adapter, parent, false);

        return new WallPaperHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpapersAdapter.WallPaperHolder holder, final int position) {
        holder.photoGrapherName.setText(wallpapers.get(position).getPhotographer());
        holder.detailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ShowDetail.class);
                if (wallpapers.get(position).getSrc().getLarge() != null) {
                    intent.putExtra(ShowDetail.IMAGE_URL, wallpapers.get(position).getSrc().getLarge());
                } else {
                    Toast.makeText(activity, "No Preview Available", Toast.LENGTH_LONG).show();
                }
                activity.startActivity(intent);
            }
        });

        Picasso.get()
                .load(wallpapers.get(position).getSrc().getLarge())
                .into(holder.wallPaperImg);
    }

    @Override
    public int getItemCount() {
        if (wallpapers != null) {
            return wallpapers.size();
        } else return 0;
    }
}
