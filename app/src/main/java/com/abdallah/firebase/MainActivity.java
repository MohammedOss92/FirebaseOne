package com.abdallah.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    EditText name,email,phone;
    Button send;
    int maxid = 0;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name  =findViewById(R.id.et_name);
        phone =findViewById(R.id.et_phone);
        email =findViewById(R.id.et_email);
        send  =findViewById(R.id.btn_reg);

        member = new Member();
        ref = database.getInstance().getReferenceFromUrl("https://fir-d8f13-default-rtdb.firebaseio.com/").child("user");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member.setName(name.getText().toString());
                member.setEmail(email.getText().toString());
                member.setPhone(phone.getText().toString());

                ref.child(String.valueOf(maxid+1)).setValue(member);

            }
        });

    }
}