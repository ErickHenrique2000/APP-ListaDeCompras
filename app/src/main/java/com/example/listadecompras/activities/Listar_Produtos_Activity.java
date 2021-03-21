package com.example.listadecompras.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.listadecompras.R;
import com.example.listadecompras.adapter.AdapterListaProdutos;
import com.example.listadecompras.controller.ProdutoCtrl;
import com.example.listadecompras.model.Produto;

import java.util.List;

public class Listar_Produtos_Activity extends AppCompatActivity {

    private ProdutoCtrl produtoCtrl;
    private ListView ltvProdutos;
    private List<Produto> produtoList;
    private AdapterListaProdutos adapterListaProdutos;
    private Spinner spnOrdem;
    private String ordenacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar__produtos_);

        this.ltvProdutos = (ListView) findViewById(R.id.ltvProdutos);
        this.spnOrdem = (Spinner) findViewById(R.id.spnOrdem);

        if(spnOrdem != null) {
            this.ordenacao = spnOrdem.getSelectedItem().toString();
        }

        this.produtoCtrl = new ProdutoCtrl(Listar_Produtos_Activity.this);
        this.produtoList = produtoCtrl.getListProdutosCtrl(this.ordenacao);

        this.adapterListaProdutos = new AdapterListaProdutos(Listar_Produtos_Activity.this ,produtoList);
        ltvProdutos.setAdapter(adapterListaProdutos);

        setsActivieties();
    }

    private void setsActivieties(){

        this.spnOrdem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Listar_Produtos_Activity.this, spnOrdem.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                ordenacao = spnOrdem.getSelectedItem().toString();
                produtoList = produtoCtrl.getListProdutosCtrl(ordenacao);
                adapterListaProdutos = new AdapterListaProdutos(Listar_Produtos_Activity.this ,produtoList);
                ltvProdutos.setAdapter(adapterListaProdutos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.ltvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produto = (Produto) adapterListaProdutos.getItem(position);

                AlertDialog.Builder janela = new AlertDialog.Builder(Listar_Produtos_Activity.this);
                janela.setTitle("Operação");
                janela.setMessage("O que deseja fazer com o produto " + produto.getNome() + "?");

                janela.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                janela.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();

                        Bundle bundle = new Bundle();
                        bundle.putInt("id", produto.getId());
                        bundle.putInt("quantidade", produto.getQtd());
                        bundle.putString("nome", produto.getNome());
                        bundle.putString("setor", produto.getSetor());

                        Intent intent = new Intent(Listar_Produtos_Activity.this, EditarProdutoActivity.class);
                        intent.putExtra("bundle", bundle);

                        //startActivity(intent);
                        startActivityForResult(intent, 1);
                    }
                });

                janela.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean teste;
                        teste = produtoCtrl.excluirProdutoCrtl(produto.getId());
                        if(teste){
                            produtoList = produtoCtrl.getListProdutosCtrl(ordenacao);
                            adapterListaProdutos = new AdapterListaProdutos(Listar_Produtos_Activity.this ,produtoList);
                            ltvProdutos.setAdapter(adapterListaProdutos);
                            Toast.makeText(Listar_Produtos_Activity.this, "Produto excluido", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Listar_Produtos_Activity.this, "Erro ao excluir produto", Toast.LENGTH_LONG).show();
                        }
                        dialog.cancel();
                    }
                });

                janela.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        produtoList = produtoCtrl.getListProdutosCtrl(ordenacao);
        adapterListaProdutos = new AdapterListaProdutos(Listar_Produtos_Activity.this ,produtoList);
        ltvProdutos.setAdapter(adapterListaProdutos);
    }
}