package com.example.joel.task5;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<DayQuoteItem> mQuotes = new ArrayList<>();
    private RecyclerView mRecyclerView;

    private QuoteAdapter mQuoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        requestData();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData();
            }
        });
    }

    private void updateUI() {
        if (mQuoteAdapter == null) {
            mQuoteAdapter = new QuoteAdapter(mQuotes);
            mRecyclerView.setAdapter(mQuoteAdapter);
        } else {
            mQuoteAdapter.notifyDataSetChanged();
        }
    }

    private void requestData()
    {
        NumberApiService service = NumberApiService.retrofit.create(NumberApiService.class);
        /**
         * Make an a-synchronous call by enqueing and definition of callbacks.
         */
        Call<DayQuoteItem> call = service.getN();
        call.enqueue(new Callback<DayQuoteItem>() {
            @Override
            public void onResponse(Call<DayQuoteItem> call, Response<DayQuoteItem> response) {
                if (response.isSuccessful()) {
                    DayQuoteItem dayQuoteItem = response.body();
                    System.out.println(response.body());
                    mQuotes.add(dayQuoteItem);
                    updateUI();
                }

            }
            @Override
            public void onFailure(Call<DayQuoteItem> call, Throwable t) {
                System.out.println("erorr" + t.toString());
            }
        });
    }


}
