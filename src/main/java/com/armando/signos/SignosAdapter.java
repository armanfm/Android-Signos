package com.armando.signos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SignosAdapter extends ArrayAdapter<Signos> {


    public SignosAdapter(Context context, List<Signos>signos) {
        super(context, 0, signos);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Signos signos = getItem(position);
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_signo, null);
        }
        TextView periodo = (TextView) convertView.findViewById(R.id.periodo);
        TextView signo = (TextView) convertView.findViewById(R.id.signo);
        ImageView imagem = (ImageView) convertView.findViewById(R.id.imageView);

        Glide.with(getContext()).load(signos.imagem).into(imagem);
        periodo.setText(signos.periodo);
        signo.setText(signos.signo);


        return convertView;
    }

}
