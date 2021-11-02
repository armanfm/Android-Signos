package com.armando.signos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by armando on 30/04/2016.
 */
public class SignoHttp {


    public static HttpURLConnection abrirConexao() throws IOException {
        URL url = new URL("https://dl.dropboxusercontent.com/u/64114950/signosImg/signos.json");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);
        conexao.connect();
        return conexao;

    }
    public static List<Signos> lerItems(InputStream is)
            throws JSONException, IOException{

        List<Signos> mSignos = new ArrayList<Signos>();


        JSONObject json = new JSONObject(bytesToString(is));

        JSONArray jsonItems = json.getJSONArray("Signos");

        for (int i = 0; i <= 11; i++) {
            JSONObject jsonSigno = jsonItems.getJSONObject(i);
            Signos sg = new Signos(

                    jsonSigno.getString("periodo"),
                    jsonSigno.getString("signo"),
                    jsonSigno.getString("imagem"),
                    jsonSigno.getString("conteudo"));

            mSignos.add(sg);

        }
        return mSignos;
    }
    private static String bytesToString(InputStream is) throws IOException{
        byte[] bufferzinho = new byte[1024];
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();
        int bytesLidos;
        while ((bytesLidos = is.read(bufferzinho)) != -1){
            bufferzao.write(bufferzinho, 0, bytesLidos);
        }
        return new String(bufferzao.toByteArray());
    }

}
