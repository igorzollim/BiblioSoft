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
public class Livro {

    private String titulo;
    private String autor;
    private String disponibilidade;
    private int id_livro ;
    private int n_paginas;
    private int ano;
    
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public int getId_livro() {
        return id_livro;
    }

    public int getN_paginas() {
        return n_paginas;
    }

    public int getAno() {
        return ano;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public void setN_paginas(int n_paginas) {
        this.n_paginas = n_paginas;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
}
