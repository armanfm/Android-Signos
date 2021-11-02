package com.armando.signos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by armando on 30/04/2016.
 */
public class Signos implements Serializable {
    public String signo;
    public String periodo;
    public String imagem;
    public String conteudo;
    public long _id;



    public Signos(String signo, String periodo, String imagem, String conteudo) {
        this.signo = signo;
        this.periodo = periodo;
        this.imagem = imagem;
        this.conteudo = conteudo;
    }





    public Signos(long id, String signo, String imagem, String periodo, String conteudo) {

    }


    @Override
    public String toString() {
        return "Signos{" +
                "signo='" + signo + '\'' +
                ", periodo='" + periodo + '\'' +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }



}
