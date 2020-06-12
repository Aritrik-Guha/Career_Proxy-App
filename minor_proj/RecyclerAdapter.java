package com.example.minor_proj;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerAdapter";
    List<String> moviesList;
    List<String> moviesListAll;
    //private Context c;
    private RecyclerViewClickListerer listerer;

    public RecyclerAdapter(List<String> moviesList, RecyclerViewClickListerer listerer) {
        this.moviesList = moviesList;
        this.listerer=listerer;
        moviesListAll = new ArrayList<>();
        moviesListAll.addAll(moviesList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.rowCountTextView.setText(String.valueOf(position+1));
        holder.textView.setText(moviesList.get(position));

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public interface RecyclerViewClickListerer{
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
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv1);
            textView = itemView.findViewById(R.id.cnames);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);
            parent_layout=itemView.findViewById(R.id.parent_layout);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listerer.onClick (v,getAdapterPosition ());
        }

          }
}















