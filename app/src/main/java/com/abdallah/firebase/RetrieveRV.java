package com.abdallah.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RetrieveRV extends AppCompatActivity {

    RecyclerView rv;
    DatabaseReference ref;
    FirebaseDatabase database;
    Adapter adapter;
    ArrayList<Member> mlist = new ArrayList<>();
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_rv);
        ref = FirebaseDatabase.getInstance().getReference("user");
        rv = findViewById(R.id.rc);
        b1 = findViewById(R.id.button);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new Adapter(RetrieveRV.this, mlist);
        rv.setAdapter(adapter);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mlist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Member member = dataSnapshot.getValue(Member.class);
                    mlist.add(member);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RetrieveRV.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}