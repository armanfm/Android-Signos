package com.armando.signos;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.armando.signos.data.SignoRepositorio;
import com.bumptech.glide.Glide;

public class DetalheFragment extends Fragment {
    SignoRepositorio repo;
    boolean favorito;
    Signos mSignos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


        repo = new SignoRepositorio(getActivity());
    }

    public static DetalheFragment novaInstancia(Signos mSigno){
        Bundle args = new Bundle();
        args.putSerializable("Signos", mSigno);

        DetalheFragment dlf = new DetalheFragment();
        dlf.setArguments(args);
        return dlf;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSignos = (Signos) getArguments().getSerializable("Signos");

        View view = inflater.inflate(R.layout.activity_detalhe_fragment, null);

        TextView periodoDetalhe= (TextView) view.findViewById(R.id.periodoDetalhe);
        TextView signoDetalhe= (TextView) view.findViewById(R.id.signoDetalhe);
        TextView conteudo = (TextView) view.findViewById(R.id.conteudo);
        ImageView imagemDetalhe = (ImageView) view.findViewById(R.id.imageView2);

        favorito = repo.SignoFavorito(mSignos.signo);
        Glide.with(getContext()).load(mSignos.imagem).into(imagemDetalhe);
        periodoDetalhe.setText(mSignos.periodo);
        signoDetalhe.setText(mSignos.signo);
        conteudo.setText(mSignos.conteudo);






        return view;


    }  @Override
       public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detalhe, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.acao_favorito){

            if (favorito) {
                repo.excluir(mSignos.signo);
                Toast.makeText(getActivity(), R.string.favorito_excluir, Toast.LENGTH_SHORT).show();
                item.setVisible(false);

            } else {
                repo.inserir(mSignos);
                Toast.makeText(getActivity(), R.string.favorito_salvar, Toast.LENGTH_SHORT).show();
                item.setVisible(false);

            }



            if (getActivity() instanceof setClickItemSigno) {

                PostFavorito listener = (PostFavorito) getActivity();
                listener.postAdicionado();

            }
        }
        return super.onOptionsItemSelected(item);
    }

    public interface PostFavorito {

        void postAdicionado();
    }
}
