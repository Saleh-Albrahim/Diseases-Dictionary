package Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.diseasesdictionary.R;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter.DataADB;
import Models.DiseasesModel;


public class SearchFragment extends Fragment {
    private static final String TAG = "ViewDatabase";
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private ArrayList<DiseasesModel> data;
    private View view;
    private DetailsFragment fragment;
    private Bundle bundle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Init();



        return view;
    }

    private void Init(){

        data = new ArrayList<>();
        fragment = new DetailsFragment();
        bundle=new Bundle();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("disease");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    DiseasesModel d = new DiseasesModel();
                    d.setDescription(ds.getValue(DiseasesModel.class).getDescription());
                    d.setId(ds.getValue(DiseasesModel.class).getId());
                    d.setTitle(ds.getValue(DiseasesModel.class).getTitle());
                    d.setUrl(ds.getValue(DiseasesModel.class).getUrl());
                    data.add(d);
//                    Log.d(TAG,  d.getTitle());
//                    Log.d(TAG,  d.getDescription());
//                    Log.d(TAG,  d.getUrl());
//                    Log.d(TAG,  d.getId());


                }
                DataADB itemsAdapter = new DataADB(view.getContext(), data);
                ListView listView =  view.findViewById(R.id.list);
                listView.setAdapter(itemsAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        DiseasesModel data= (DiseasesModel) parent.getAdapter().getItem(position);
                        FragmentManager manager = ((AppCompatActivity) view.getContext()).getSupportFragmentManager();
                        bundle.putString("Description", data.getDescription());
                        bundle.putString("Url", data.getUrl());
                        fragment.setArguments(bundle);
                        fragment.show(manager, null);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }

        });

    }
    




}
