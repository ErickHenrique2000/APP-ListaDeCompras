package com.example.listadecompras.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.listadecompras.R;
import com.example.listadecompras.model.Produto;

import java.util.List;

public class AdapterListaProdutos extends BaseAdapter {

    private Context context;
    private List<Produto> produtoList;

    public AdapterListaProdutos(Context context, List<Produto> list){
        this.context = context;
        this.produtoList = list;
    }

    @Override
    public int getCount() {
        return this.produtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.produtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.produto_layout, null);

        TextView tvNomeProduto = (TextView) v.findViewById(R.id.tvNomeProduto);
        TextView tvSetorProduto = (TextView) v.findViewById(R.id.tvSetorProduto);
        TextView tvQtdProdutos = (TextView) v.findViewById(R.id.tvQtdProdutos);
        String nome, setor, qtd;
        nome = "Nome: " + this.produtoList.get(position).getNome();
        qtd = "Quantidade: " + this.produtoList.get(position).getQtd();
        setor = "Setor: " + this.produtoList.get(position).getSetor();
        tvNomeProduto.setText(nome);
        tvSetorProduto.setText(setor);
        tvQtdProdutos.setText(qtd);

        return v;
    }

    public void atualizar(List<Produto> produtoList){
        this.produtoList.clear();
        this.produtoList = produtoList;
        this.notifyDataSetChanged();
    }
}
