package Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.diseasesdictionary.R;


public class DetailsFragment extends androidx.fragment.app.DialogFragment {

    private View view;
    private Bundle bundle;
    private String  Description, url;
    private TextView  frag_des, frag_url;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_dialog, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        bundle = getArguments();
        if(bundle != null){

            Description = bundle.getString("Description");

            url = bundle.getString("Url");
        }


        frag_des = view.findViewById(R.id.frag_des);
        frag_url = view.findViewById(R.id.frag_url);

        frag_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(url)));
            }
        });


        frag_des.setMovementMethod(new ScrollingMovementMethod());
        frag_des.setText(Description);

        return view;
    }
}
