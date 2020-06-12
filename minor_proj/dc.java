package com.example.minor_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class dc extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter5 recyclerAdapter;
    private RecyclerAdapter5.RecyclerViewClickListerer3 listener;

    List<String> moviesList;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar ().hide ();
        setContentView(R.layout.activity_dc);
        String x="a";
        Bundle extras=getIntent ().getExtras ();
        if (extras!=null){
            x=extras.getString ("value2");
        }
        getSupportActionBar().setTitle(x);
        ActionBar ab=getSupportActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_bg));


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child(x);

        recyclerView = findViewById(R.id.recyclerView5);

        myRef.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists ( )) {
                    moviesList = new ArrayList<> ();

                    for (DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        String name = ds.child ("name").getValue ().toString ();
                        moviesList.add (name);
                    }
                    setOnClickListener3();
                    recyclerAdapter = new RecyclerAdapter5(moviesList,listener);
                    recyclerView.setAdapter(recyclerAdapter);
                    // adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
    private void setOnClickListener3() {
        listener=new RecyclerAdapter5.RecyclerViewClickListerer3 () {
            @Override
            public void onClick(View v, int position) {
                Intent i=new Intent (getApplicationContext (),dpc.class);
                i.putExtra ("value",moviesList.get (position));
                startActivity (i);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu5, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search5);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}

