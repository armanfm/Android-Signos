package com.armando.signos.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by armando on 07/05/2016.
 */
public class SignoDBHelper  extends SQLiteOpenHelper{



        private static final String NOME_BANCO = "dbSignos";
        private static final int VERSAO_BANCO = 1;
        public static final String DATABASE_NAME = "SignosDBHelper" ;

        public SignoDBHelper(Context context) {
            super(context, NOME_BANCO, null, VERSAO_BANCO);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table Signos(_id integer primary key autoincrement , "
                    + "signo text not null , periodo text not null, imagem text not null, conteudo text  )");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            //update database - next versions
        }

    }
