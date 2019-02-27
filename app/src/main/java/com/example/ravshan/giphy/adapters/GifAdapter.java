package com.example.ravshan.giphy.adapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ravshan.giphy.R;
import com.example.ravshan.giphy.models.Gif;

import java.util.ArrayList;


public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifViewHolder> {
    private ArrayList<Gif> gifs;
    Context context;


    public GifAdapter(ArrayList<Gif> gifs, Context context) {
        super();
        this.gifs = gifs;
        this.context = context;
    }

    public static class GifViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public GifViewHolder(ImageView view) {
            super(view);
            imageView = view;
        }
    }

    @NonNull
    @Override
    public GifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView view = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_gif, parent, false);

        return new GifViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return gifs.size();
    }

    @Override
    public void onBindViewHolder(@NonNull GifViewHolder holder, int position) {
        ImageView imageView = holder.imageView;
        String url = gifs.get(position).getImages().getFixedWidth().getUrl();

        Glide.with(context).asGif().load(url).into(imageView);
    }

    public void setGifs(ArrayList<Gif> gifs) {
        this.gifs = gifs;
    }
}
