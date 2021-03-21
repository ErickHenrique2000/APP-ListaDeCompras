package com.example.listadecompras.controller;

import android.content.Context;

import com.example.listadecompras.DAO.ProdutoDAO;
import com.example.listadecompras.model.Produto;

import java.util.List;

public class ProdutoCtrl {

    ProdutoDAO produtoDAO;

    public ProdutoCtrl(Context context){
        produtoDAO = new ProdutoDAO(context);
    }

    public boolean salvarProdutoCrtl(Produto produto){
        return produtoDAO.salvarProdutoDAO(produto);
    }

    public List<Produto> getListProdutosCtrl(String ordenacao){
        return produtoDAO.getListProdutosDAO(ordenacao);
    }

    public boolean excluirProdutoCrtl(int id){
        return produtoDAO.excluirProdutoDAO(id);
    }

    public boolean editarProdutoCtrl(Produto produto){
        return produtoDAO.editarProdutoDAO(produto);
    }
}
