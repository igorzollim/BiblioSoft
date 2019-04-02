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
public class Aluno {
    private String nome;
    private String email;
    private Calendar dataNascimento;
    private String endereco;
    private String fone;
    private int id_aluno;
    private String sexo;
    private String cpf;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getFone() {
        return fone;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
