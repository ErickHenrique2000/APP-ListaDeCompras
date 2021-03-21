package com.example.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.listadecompras.activities.CadastrarProdutoActivity;
import com.example.listadecompras.activities.Listar_Produtos_Activity;

public class MainActivity extends AppCompatActivity {

    private Button btnAdicionar, btnListarProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        this.btnListarProdutos = (Button) findViewById(R.id.btnListarProdutos);

        setButtons();

    }

    public void setButtons(){
        this.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastrarProdutoActivity.class);
                startActivity(intent);
            }
        });

        this.btnListarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Listar_Produtos_Activity.class);
                startActivity(intent);
            }
        });
    }
}