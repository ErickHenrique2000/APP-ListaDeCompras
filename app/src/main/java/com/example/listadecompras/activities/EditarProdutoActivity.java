package com.example.listadecompras.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class EditarProdutoActivity extends AppCompatActivity {

    private EditText edtQtd, edtNome;
    private Button btnSalvar;
    private Spinner spnSetor;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        this.edtNome = (EditText) findViewById(R.id.edtNome);
        this.edtQtd = (EditText) findViewById(R.id.edtQtd);
        this.btnSalvar = (Button) findViewById(R.id.btnSalvar);

        this.bundle = getIntent().getBundleExtra("bundle");

        this.edtQtd.setText(String.valueOf(bundle.getInt("quantidade")));
        this.edtNome.setText(bundle.getString("nome"));

        setClick();
    }

    private void setClick(){

     btnSalvar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             ProdutoCtrl produtoCtrl = new ProdutoCtrl(EditarProdutoActivity.this);
             Produto produto = new Produto();

             if(edtNome.getText().toString().isEmpty() || edtQtd.getText().toString().isEmpty()){
                 Toast.makeText(EditarProdutoActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show();
             }else{
                 produto.setId(bundle.getInt("id"));
                 produto.setQtd(Integer.parseInt(edtQtd.getText().toString()));
                 produto.setSetor(bundle.getString("setor"));
                 produto.setNome(edtNome.getText().toString());

                 boolean salvo;
                 salvo = produtoCtrl.editarProdutoCtrl(produto);

                 if(salvo) {
                     Toast.makeText(EditarProdutoActivity.this, "Produto salvo", Toast.LENGTH_LONG).show();

                     Intent intent = getIntent();
                     intent.putExtra("resultado", "finalizado");
                     setResult(Activity.RESULT_OK, intent);

                     finish();
                 }else{
                     Toast.makeText(EditarProdutoActivity.this, "Erro ao salvar produto", Toast.LENGTH_LONG).show();
                 }
             }


         }
     });
    }


}