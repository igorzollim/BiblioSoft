/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author igor
 */
public class Emprestimo {
    private int id_emprestimo;
    private int id_aluno;
    private int id_livro;
    private Calendar data_emprestimo;
    private Calendar data_devolucao;
    
    private String nome_aluno;
    private String nome_livro;

    public String getNome_aluno() {
        return nome_aluno;
    }

    public String getNome_livro() {
        return nome_livro;
    }

    public void setNome_aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }

    public void setNome_livro(String nome_livro) {
        this.nome_livro = nome_livro;
    }

    public int getId_emprestimo() {
        return id_emprestimo;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public int getId_livro() {
        return id_livro;
    }

    public Calendar getData_emprestimo() {
        return data_emprestimo;
    }

    public Calendar getData_devolucao() {
        return data_devolucao;
    }

    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public void setData_emprestimo(Calendar data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public void setData_devolucao(Calendar data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
    
}
