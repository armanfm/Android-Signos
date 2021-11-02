package com.armando.signos.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.armando.signos.Signos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by armando on 07/05/2016.
 */
public class SignoRepositorio {


    private SignoDBHelper helper;



    public SignoRepositorio(Context ctx) {
        helper = new SignoDBHelper(ctx);
    }


    public long inserir(Signos signos) {
        SQLiteDatabase db = helper.getWritableDatabase();

        long id = db.insert("Signos", null, valuesFromSigno(signos));

        signos._id = id;

        db.close();
        return id;
    }
    public int alterar(Signos signos) {
        SQLiteDatabase db = helper.getWritableDatabase();

        int alteracao = db.update("Signos", valuesFromSigno(signos),
                "_id = ?", new String[]{String.valueOf(signos._id)});

        db.close();
        return alteracao;
    }
    public boolean SignoFavorito(String signo) {

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Signos where signo =?",
                new String[]{signo});

        boolean resultado = false;

        if (cursor != null && cursor.getCount() > 0) {
            resultado = true;

        }

        cursor.close();
        db.close();

        return resultado;
    }
    public List<Signos> listar() {

        List<Signos> lista = new ArrayList<Signos>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Signos order by signo", null);

        while (cursor.moveToNext()) {
             long id = cursor.getLong(cursor.getColumnIndex("_id"));
            String signo = cursor.getString(cursor.getColumnIndex("signo"));
            String imagem = cursor.getString(cursor.getColumnIndex("imagem"));
            String periodo = cursor.getString(cursor.getColumnIndex("periodo"));
            String conteudo = cursor.getString(cursor.getColumnIndex("conteudo"));

            lista.add(new Signos(id,signo, imagem,periodo,conteudo));
        }
        cursor.close();
        db.close();
        return lista;
    }
    public int excluir(String conteudo) {
        SQLiteDatabase db = helper.getWritableDatabase();

        int excluindo = db.delete("Signos", "signo=?", new String[]{conteudo});
        db.close();
        return excluindo;
    }
    private ContentValues valuesFromSigno(Signos signos) {

        ContentValues cv = new ContentValues();

        cv.put("_id", signos._id);
        cv.put("signo", signos.signo);
        cv.put("periodo", signos.periodo);
        cv.put("imagem", signos.imagem);
        cv.put("conteudo", signos.conteudo);

        return cv;
    }

}
