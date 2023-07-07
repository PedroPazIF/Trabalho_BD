package com.example.bancodedados.data;

import java.io.Serializable;

public class Jogo implements Serializable {
    private int id;
    private String nome;
    private double nota;
    private String situacao;

    public Jogo(int id, String nome, double nota) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
    }

    public Jogo(String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setValor(double valor) {
        this.nota = valor;
    }
}
