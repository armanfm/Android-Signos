package com.armando.signos;

import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.armando.signos.data.SignoRepositorio;

import java.util.ArrayList;
import java.util.List;

public class FavoritoFragment extends ListFragment {

    SignoRepositorio repo;
    List<Signos> mSignos;
    ArrayAdapter<Signos> mAdapter;



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        mSignos= new ArrayList<>();

    }
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


        if (getActivity() instanceof setClickItemSigno) {
            Signos signos = (Signos) l.getItemAtPosition(position);
            setClickItemSigno listener = (setClickItemSigno) getActivity();
            listener.clicouNoItem(signos);

        }








}
