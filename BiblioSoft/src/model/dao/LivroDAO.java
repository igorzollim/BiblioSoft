/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;


import conexaoBD.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Livro;
import java.util.List;

/**
 *
 * @author igor
 */
public class LivroDAO {
    private Connection connection;
    
    public LivroDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Livro livro){
        String sql = "INSERT INTO livro (titulo, autor, ano, disponibilidade, numero_paginas) values (?,?,?,?,?)";
        try{
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
            
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getDisponibilidade());
            stmt.setInt(5, livro.getN_paginas());
            
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            throw new RuntimeException (e);
        }
    }
    
    public List<Livro> getLista(){
        try{
            List<Livro> livros = new ArrayList<Livro>();
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM livro");
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Livro livro = new Livro();
                livro.setTitulo(rs.getString("titulo"));
                livro.setN_paginas(rs.getInt("numero_paginas"));
                livro.setId_livro(rs.getInt("id_livro"));
                livro.setDisponibilidade(rs.getString("disponibilidade"));
                livro.setAutor(rs.getString("autor"));
                livro.setAno(rs.getInt("ano"));
                
                livros.add(livro);
            } 
            
            rs.close();
            stmt.close();
            return livros;
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public void altera(Livro livro) {
        String sql = "update livro set titulo=?, autor=?, ano=?,"
                + "disponibilidade = ?, numero_paginas=? where id_livro=?";

        try {
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getDisponibilidade());
            stmt.setInt(5, livro.getN_paginas());

            stmt.setLong(6, livro.getId_livro());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void atualizaDisponibilidade(Livro livro) {
        String sql = "update livro set disponibilidade = ? where id_livro=?";

        try {
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
            stmt.setString(1, livro.getDisponibilidade());
            stmt.setLong(2, livro.getId_livro());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Livro getLivro(int id_livro){
        List<Livro> livros = getLista();
        
        for(Livro livro : livros){
            if (livro.getId_livro() == id_livro){
                return livro;
            }
        }
        return null;
    }
    
    public void deletar (int id_livro){
        try{
            String sql = "DELETE FROM livro WHERE id_livro=" + id_livro;
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Livro> pesquisa(String str){
        try{
            List<Livro> livros = new ArrayList<Livro>();
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM livro WHERE livro.titulo LIKE '"+str+"%' || livro.autor LIKE '"+str+"%'");
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Livro livro = new Livro();
                livro.setTitulo(rs.getString("titulo"));
                livro.setN_paginas(rs.getInt("numero_paginas"));
                livro.setId_livro(rs.getInt("id_livro"));
                livro.setDisponibilidade(rs.getString("disponibilidade"));
                livro.setAutor(rs.getString("autor"));
                livro.setAno(rs.getInt("ano"));
                
                livros.add(livro);
            } 
            
            rs.close();
            stmt.close();
            return livros;
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}


