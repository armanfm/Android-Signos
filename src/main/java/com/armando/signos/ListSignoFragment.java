package com.armando.signos;



import android.app.ListFragment;
import android.os.AsyncTask;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class ListSignoFragment extends ListFragment {

    List<Signos> mSignos;
    ArrayAdapter<Signos> mAdapter;
    SignosTask mTask;
    Signos signos;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mSignos = new ArrayList<>();

    }
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        signos
                = mSignos.get(position);
        if (getActivity() instanceof setClickItemSigno) {
            setClickItemSigno listener = (setClickItemSigno) getActivity();
            listener.clicouNoItem(signos);
        }

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mSignos == null) {
            mSignos = new ArrayList<Signos>();
        }

        mAdapter = new SignosAdapter(getActivity(), mSignos);
        setListAdapter(mAdapter);

        if (mTask == null) {
            mTask = new SignosTask();
            mTask.execute();
        } else {
            if (mTask.getStatus() == AsyncTask.Status.RUNNING) {
                // Exibir um progress...
            }
        }
    }

    public class SignosTask extends AsyncTask<Void, Void, List<Signos>> {


        @Override
        protected List<Signos> doInBackground(Void... params) {

            try {

                HttpURLConnection conexao = SignoHttp.abrirConexao();

                if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    return SignoHttp.lerItems(conexao.getInputStream());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Signos> result) {
            super.onPostExecute(result);
            if (result != null) {
                mAdapter.clear();
                mAdapter.addAll(result);
                mAdapter.notifyDataSetChanged();

            }

        }

    }
}
