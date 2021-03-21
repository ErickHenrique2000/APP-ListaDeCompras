package com.example.listadecompras.model;

public class Produto {

    private int id, qtd;
    private String setor, nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", qtd=" + qtd +
                ", setor='" + setor + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
