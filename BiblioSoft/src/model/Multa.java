/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author igor
 */
public class Multa {

    private double valor;
    private String descricao;
    private int id_multa;
    private int id_aluno;
    private String nome_aluno;
    
    public String getNome_aluno() {
        return nome_aluno;
    }

    public void setNome_aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }
    
    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId_multa() {
        return id_multa;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId_multa(int id_multa) {
        this.id_multa = id_multa;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }
    
}
