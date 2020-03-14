package com.example.diseasesdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Models.DiseasesModel;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
   public  List<DiseasesModel> commentKeys;
    public void push(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                  commentKeys = new ArrayList<>();
                for (DataSnapshot dataValues  : dataSnapshot.getChildren()) {
                    DiseasesModel restaurantModel = dataValues.getValue(DiseasesModel.class);
                    commentKeys.add(restaurantModel);
                }
                for (int i = 0; i <commentKeys.size() ; i++) {
                    System.out.println(commentKeys.get(i).getId());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


    }
}
