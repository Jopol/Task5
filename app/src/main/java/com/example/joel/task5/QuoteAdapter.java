package com.example.joel.task5;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.ViewHolder> {

    private List<DayQuoteItem> mQuotes;

    public QuoteAdapter(List<DayQuoteItem> mQuotes) {
        this.mQuotes = mQuotes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotemessage, null);

        // Return a new holder instance
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DayQuoteItem quote =  mQuotes.get(position);
        holder.textView.setText(quote.getText());
    }

    @Override
    public int getItemCount() {

       return mQuotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.quote_message);
            this.view = itemView;
        }
    }
}
