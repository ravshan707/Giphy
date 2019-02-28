package com.example.ravshan.giphy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.ravshan.giphy.R;
import com.example.ravshan.giphy.models.Gif;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifViewHolder> {
    private ArrayList<Gif> gifs;
    Context context;


    public GifAdapter(ArrayList<Gif> gifs, Context context) {
        super();
        this.gifs = gifs;
        this.context = context;
    }

    static class GifViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gif_view) ImageView imageView;
        @BindView(R.id.progress) ProgressBar progressBar;

        GifViewHolder(RelativeLayout view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @NonNull
    @Override
    public GifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_gif, parent, false);

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

        Glide.with(context)
                .asGif()
                .load(url)
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);
    }
}
