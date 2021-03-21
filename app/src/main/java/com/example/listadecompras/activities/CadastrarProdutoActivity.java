package com.example.listadecompras.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.listadecompras.R;
import com.example.listadecompras.controller.ProdutoCtrl;
import com.example.listadecompras.model.Produto;

public class CadastrarProdutoActivity extends AppCompatActivity {

    private Button btnFinalizar;
    private Spinner spnSetor;
    private EditText edtQtdProduto, edtNomeProduto;

    private ProdutoCtrl produtoCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        this.produtoCtrl = new ProdutoCtrl(CadastrarProdutoActivity.this);

        iniciarLayout();

    }

    public void iniciarLayout(){

        this.btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
        this.spnSetor = (Spinner) findViewById(R.id.spnSetor);
        this.edtNomeProduto = (EditText) findViewById(R.id.edtNomeProduto);
        this.edtQtdProduto = (EditText) findViewById(R.id.edtQtdProduto);

        setButton();

    }

    public void setButton(){
        this.btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = "", setor = "";
                int qtd = -1;

                Produto produto = new Produto();
                if (edtNomeProduto.getText().toString().isEmpty() == false) {
                    nome = edtNomeProduto.getText().toString();
                }
                if (spnSetor.getSelectedItem().toString().isEmpty() == false) {
                    setor = spnSetor.getSelectedItem().toString();
                }
                if (edtQtdProduto.getText().toString().isEmpty() == false) {
                    qtd = Integer.parseInt(edtQtdProduto.getText().toString());
                }


                if (nome == "" || setor == "" || qtd == -1) {
                    Toast.makeText(CadastrarProdutoActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show();
                    return;
                }

                if (qtd <= 0) {
                    Toast.makeText(CadastrarProdutoActivity.this, "A quantidade deve ser maior que 0", Toast.LENGTH_LONG).show();
                    return;
                }

                produto.setQtd(qtd);
                produto.setSetor(setor);
                produto.setNome(nome);
                if (produtoCtrl.salvarProdutoCrtl(produto)) {
                    Toast.makeText(CadastrarProdutoActivity.this, produto.getSetor(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(CadastrarProdutoActivity.this, "Erro ao cadastrar o produto", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}