package com.example.ravshan.giphy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ravshan.giphy.R;
import com.example.ravshan.giphy.adapters.GifAdapter;
import com.example.ravshan.giphy.models.Gif;
import com.example.ravshan.giphy.network.GiphySearchResponse;
import com.example.ravshan.giphy.network.GiphyService;
import com.example.ravshan.giphy.network.ServiceFactory;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.query) EditText query;
    @BindView(R.id.search) Button search;
    @BindView(R.id.clear) Button clear;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;
    private ArrayList<Gif> gifs = new ArrayList<>();
    private GiphyService mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mAdapter = new GifAdapter(gifs, this);
        recyclerView.setAdapter(mAdapter);

        mApi = ServiceFactory.getApi(GiphyService.class);
    }

    @OnClick(R.id.search)
    public void onClickSearch() {
        String q = query.getText().toString();

        if (!q.isEmpty()) {
            getGifs(q);
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
        }
    }

    @OnClick(R.id.clear)
    public void onClickClear() {
        query.setText("");
        gifs.clear();
        mAdapter.notifyDataSetChanged();
    }

    private void getGifs(String query) {
        Realm realm = Realm.getDefaultInstance();
        ArrayList<Gif> rGifs = new ArrayList<>(realm.where(Gif.class).equalTo("query", query).findAll());

        if (rGifs.isEmpty()){
            mApi.search(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(GiphySearchResponse::getData)
                    .subscribe(new SingleObserver<ArrayList<Gif>>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onSuccess(ArrayList<Gif> response) {
                            if (response != null && response.size() > 0) {
                                gifs.clear();
                                gifs.addAll(response);
                                mAdapter.notifyDataSetChanged();

                                for (Gif g : response) {
                                    g.setQuery(query);
                                    realm.executeTransaction(realm1 -> {
                                        realm.copyToRealmOrUpdate(g);
                                    });
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(getApplication(), R.string.error_msg, Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            gifs.clear();
            gifs.addAll(rGifs);
            mAdapter.notifyDataSetChanged();
        }
    }
}
