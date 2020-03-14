package Adapter;

import
        android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diseasesdictionary.R;

import java.util.ArrayList;

import Models.DiseasesModel;


public class DataADB extends ArrayAdapter<DiseasesModel> {





    public DataADB(Context context, ArrayList<DiseasesModel> d) {
       super(context, 0, d);
   }


    public View getView(int position, View convertView, ViewGroup parent){


        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.diseases_holder,parent,false);

        }
        DiseasesModel currentData = getItem(position);

        TextView Title =  ListItemView.findViewById(R.id.d_title);
        Title.setText(currentData.getTitle());




        return ListItemView;
    }

}

