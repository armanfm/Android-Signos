package com.armando.signos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);


        Signos mSignos = (Signos) getIntent().getSerializableExtra("Signos");


        if (savedInstanceState == null) {
            DetalheFragment df =
                    DetalheFragment.novaInstancia(mSignos);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.conteiner, df, "detalhe")
                    .commit();

        }
    }
}
