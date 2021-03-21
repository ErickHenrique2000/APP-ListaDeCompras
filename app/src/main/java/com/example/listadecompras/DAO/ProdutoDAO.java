package com.example.listadecompras.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadecompras.dbHelper.ConecxaoSQLite;
import com.example.listadecompras.model.Produto;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    ConecxaoSQLite conecxaoSQLite;

    public ProdutoDAO(Context context){
        this.conecxaoSQLite = ConecxaoSQLite.getInstancia(context);
    }

    public boolean salvarProdutoDAO(Produto produto){
        SQLiteDatabase db = conecxaoSQLite.getWritableDatabase();

        try{

            ContentValues values = new ContentValues();

            values.put("nome", produto.getNome());
            values.put("quantidade", produto.getQtd());
            values.put("setor", produto.getSetor());

            long produtoId = db.insert("produto", null, values);

            if(produtoId != 0){
                return true;
            }else{
                return false;
            }

        }catch (Exception e){
            return false;
        }finally {
            if(db != null){
                db.close();
            }
        }
    }

    public List<Produto> getListProdutosDAO(String ordenacao){
        List<Produto> listProduto = new ArrayList<>();

        SQLiteDatabase db = this.conecxaoSQLite.getReadableDatabase();
        Cursor cursor;

        String query = "SELECT * FROM produto;";
        if("Nome".equals(ordenacao)){
            query = "SELECT * FROM produto ORDER BY nome ASC;";
        }else if("Data de incremento".equals(ordenacao)){
            query = "SELECT * FROM produto ORDER BY id ASC;";
        }else if("Quantidade".equals(ordenacao)){
            query = "SELECT * FROM produto ORDER BY quantidade DESC;";
        }else if("Setor".equals(ordenacao)){
            query = "SELECT * FROM produto ORDER BY setor ASC;";
        }
        Log.d("LOG_QUERY", ordenacao);
        Log.d("LOG_QUERY", query);

        try{

            cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                Produto produto;
                do{
                    produto = new Produto();
                    produto.setId(cursor.getInt(0));
                    produto.setNome(cursor.getString(1));
                    produto.setQtd(cursor.getInt(2));
                    produto.setSetor(cursor.getString(3));

                    listProduto.add(produto);
                }while (cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("ERRO_CATCH_LIST", e.toString());
            return null;
        }finally {
            if(db != null){
                db.close();
            }
        }



        return listProduto;
    }

    public boolean excluirProdutoDAO(int id){

        SQLiteDatabase db = null;

        try{

            db = conecxaoSQLite.getWritableDatabase();
            db.delete("produto", "id = ?", new String[]{String.valueOf(id)});

        }catch (Exception e){
            return false;
        }finally {
            if(db != null){
                db.close();
            }
        }

        return true;
    }

    public boolean editarProdutoDAO(Produto produto){

        SQLiteDatabase db = null;

        try{

            db = conecxaoSQLite.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put("id", produto.getId());
            values.put("nome", produto.getNome());
            values.put("quantidade", produto.getQtd());
            values.put("setor", produto.getSetor());

            int atualizou = db.update("produto",
                    values,
                    "id = ?",
                    new String[]{String.valueOf(produto.getId())});

            if(atualizou > 0){
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            return false;
        }finally {
            if(db != null){
                db.close();
            }
        }
    }

}
