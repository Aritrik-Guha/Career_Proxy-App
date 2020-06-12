package com.example.minor_proj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerAdapter4 extends RecyclerView.Adapter<RecyclerAdapter4.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerAdapter";
    List<String> moviesList;
    List<String> moviesListAll;
    private RecyclerViewClickListerer5 listerer;

    public RecyclerAdapter4(List<String> moviesList, RecyclerViewClickListerer5 listerer) {
        this.moviesList = moviesList;
        this.listerer=listerer;
        moviesListAll = new ArrayList<>();
        moviesListAll.addAll(moviesList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item4, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rowCountTextView.setText(String.valueOf(position+1));
        holder.textView.setText(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public interface RecyclerViewClickListerer5{
        void onClick(View v,int position);
    }

    @Override
    public Filter getFilter() {

        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<String> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(moviesListAll);
            } else {
                for (String movie: moviesListAll) {
                    if (movie.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(movie);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            moviesList.clear();
            moviesList.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView, rowCountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv4);
            textView = itemView.findViewById(R.id.slist);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView4);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) { listerer.onClick (view,getAdapterPosition ()); }
    }
}















