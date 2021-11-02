package com.armando.signos;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements setClickItemSigno, DetalheFragment.PostFavorito {

    FavoritoFragment fv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fv = new FavoritoFragment();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PaginaAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    public void clicouNoItem(Signos signos) {
        // smartphone
        if (getResources().getBoolean(R.bool.phone)) {
            Intent it = new Intent(this, DetalheActivity.class);
            it.putExtra("Signos", signos);
            startActivity(it);

        } else {
            DetalheFragment dfl =
                    DetalheFragment.novaInstancia(signos);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.conteiner, dfl, "detalhe")
                    .commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this

        //
        // adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void postAdicionado() {
        try {
            fv.refreshList();
            }catch (Exception e) {
            e.printStackTrace();
        }
    }



    class PaginaAdapter extends FragmentPagerAdapter {
        public PaginaAdapter(FragmentManager fm) {
            super(fm);


        }


        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new ListSignoFragment();
            } else {
                return fv;


            }
        }
            @Override
            public int getCount() {
                return 2;
            }

            public CharSequence getPageTitle(int position) {
                if (position == 0) return "Web";
                else return "Favorito";
            }

    }
}