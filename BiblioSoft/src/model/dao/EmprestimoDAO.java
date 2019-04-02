/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Aluno;
import model.Emprestimo;
import model.Livro;
import conexaoBD.ConnectionFactory;

/**
 *
 * @author igor
 */
public class EmprestimoDAO {
    private Connection connection;
    
    public EmprestimoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Emprestimo emprestimo){
        String sql = "INSERT INTO emprestimo (id_aluno, id_livro, data_emprestimo, data_devolucao) values (?,?,?,?)";
        try{
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
            
            stmt.setInt(1, emprestimo.getId_aluno());
            stmt.setInt(2, emprestimo.getId_livro());
            stmt.setDate(3,new Date(emprestimo.getData_emprestimo().getTimeInMillis()));
            stmt.setDate(4,new Date(emprestimo.getData_devolucao().getTimeInMillis()));
            
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            throw new RuntimeException (e);
        }
    }
    
    public List<Emprestimo> getLista(){
        try{
            List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM emprestimo");
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Emprestimo emprestimo = new Emprestimo();
                
                emprestimo.setId_emprestimo(rs.getInt("id_emprestimo"));
                emprestimo.setId_aluno(rs.getInt("id_aluno"));
                emprestimo.setId_livro(rs.getInt("id_livro"));
                
                Calendar data_emprestimo = Calendar.getInstance();
		data_emprestimo.setTime(rs.getDate("data_emprestimo"));
		emprestimo.setData_emprestimo(data_emprestimo);
                
                Calendar data_devolucao = Calendar.getInstance();
		data_devolucao.setTime(rs.getDate("data_devolucao"));
		emprestimo.setData_devolucao(data_devolucao);
                
                
                
                emprestimos.add(emprestimo);
            } 
            
            rs.close();
            stmt.close();
            return emprestimos;
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public void altera(Emprestimo emprestimo) {
        String sql = "update emprestimo set id_aluno=?, id_livro=?,"
                + "data_emprestimo=?, data_devolucao=? where id_emprestimo=?";
        
        try {
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

            stmt.setInt(1, emprestimo.getId_aluno());
            stmt.setInt(2, emprestimo.getId_livro());
            stmt.setDate(3, new Date(emprestimo.getData_emprestimo().getTimeInMillis()));
            stmt.setDate(4, new Date(emprestimo.getData_devolucao().getTimeInMillis()));
            stmt.setInt(5, emprestimo.getId_emprestimo());
 
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void deletar(int id_emprestimo){
        try{
            String sql = "DELETE FROM emprestimo WHERE id_emprestimo=" + id_emprestimo;
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public boolean livroEmprestado(int id_livro){
        List<Emprestimo> emprestimos = getLista();

        for (Emprestimo emprestimo : emprestimos){
            if (emprestimo.getId_livro() == id_livro){
                return true;
            }
        }
        return false;
    }
    
    public boolean temEmprestimo(String str){
        List<Emprestimo> emprestimos = pesquisa(str);
        if(!emprestimos.isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    
    public List<Emprestimo> pesquisa(String str){
        try{
            List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
            String sql = "SELECT id_emprestimo, aluno.nome, livro.titulo, emprestimo.data_emprestimo, data_devolucao from emprestimo join aluno using(id_aluno) join livro using(id_livro) where aluno.nome = '"+str+"'";
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Emprestimo emprestimo = new Emprestimo();
                
                emprestimo.setId_emprestimo(rs.getInt("id_emprestimo"));
                emprestimo.setNome_aluno(rs.getString("nome"));
                emprestimo.setNome_livro(rs.getString("titulo"));
                
                Calendar data_emprestimo = Calendar.getInstance();
		data_emprestimo.setTime(rs.getDate("data_emprestimo"));
		emprestimo.setData_emprestimo(data_emprestimo);
                
                Calendar data_devolucao = Calendar.getInstance();
		data_devolucao.setTime(rs.getDate("data_devolucao"));
		emprestimo.setData_devolucao(data_devolucao);
                
                
                
                emprestimos.add(emprestimo);
            } 
            
            rs.close();
            stmt.close();
            return emprestimos;
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
