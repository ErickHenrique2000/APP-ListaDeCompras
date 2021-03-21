package com.example.listadecompras.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConecxaoSQLite extends SQLiteOpenHelper {

    private static ConecxaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "db_produtos_app";

    public ConecxaoSQLite(@Nullable Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static ConecxaoSQLite getInstancia(Context context){
        if(INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new ConecxaoSQLite(context);
        }
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE IF NOT EXISTS produto" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "nome TEXT," +
                        "quantidade INTEGER," +
                        "setor TEXT" +
                        ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
