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
import java.util.List;
import model.Multa;

/**
 *
 * @author igor
 */
public class MultaDAO {
    private Connection connection;
    
    public MultaDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Multa multa){
        String sql = "INSERT INTO multa (id_aluno, descricao, valor) VALUES (?,?,?)";
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, multa.getId_aluno());
            stmt.setString(2, multa.getDescricao());
            stmt.setDouble(3, multa.getValor());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Multa> pesquisa(String str){
        try {
            List<Multa> multas = new ArrayList<Multa>();
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT id_multa,id_aluno, aluno.nome AS 'nomeAluno', descricao, valor FROM multa JOIN aluno USING(id_aluno) WHERE aluno.nome ='"+str+"'");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Multa multa = new Multa();
                multa.setId_multa(rs.getInt("id_multa"));
                multa.setId_aluno(rs.getInt("id_aluno"));
                multa.setNome_aluno(rs.getString("nomeAluno"));
                multa.setDescricao(rs.getString("descricao"));
                multa.setValor(rs.getDouble("valor"));
                
                multas.add(multa);
            }
            
            rs.close();
            stmt.close();
            return multas;
        } catch(SQLException e){
            throw new RuntimeException (e);
        }
    }
    
    public List<Multa> getLista(){
        try {
            List<Multa> multas = new ArrayList<Multa>();
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM multas");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Multa multa = new Multa();
                multa.setId_multa(rs.getInt("id_multa"));
                multa.setId_aluno(rs.getInt("id_aluno"));
                multa.setDescricao(rs.getString("descricao"));
                multa.setValor(rs.getDouble("valor"));
                
                multas.add(multa);
            }
            
            rs.close();
            stmt.close();
            return multas;
        } catch(SQLException e){
            throw new RuntimeException (e);
        }
    }
    
    public void altera(Multa multa){
        try{
            PreparedStatement stmt = connection.prepareStatement( "UPDATE multa SET id_multa=?, id_aluno=?, descricao=?, valor=?");
            stmt.setInt(0, multa.getId_multa());
            stmt.setInt(1, multa.getId_aluno());
            stmt.setString(2, multa.getDescricao());
            stmt.setDouble(3, multa.getValor());
            
            stmt.execute();
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException (e);
        }
    }
    
    public void deletar (int id_multa){
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM multa WHERE id_multa='"+id_multa+"'");
            stmt.execute();
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public String getPessoaMultada(int id_multa){
        String nome="";
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT id_multa, aluno.nome from multa join aluno using(id_aluno)");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                if(rs.getInt("id_multa") == id_multa){
                    nome = rs.getString("nome");
                }
            }
            
            rs.close();
            stmt.close();
            return nome;
        }catch(SQLException e){
            throw new RuntimeException (e);
        }
    }
    
    public String getTotDiv(String str){
        String divida="";
        try{
            PreparedStatement stmt = connection.prepareStatement( "SELECT SUM(valor) AS 'total' FROM multa JOIN aluno USING(id_aluno) WHERE aluno.nome = '"+str+"'");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                divida = rs.getString("total");
            }
            
            rs.close();
            stmt.close();
            return divida;
        }catch(SQLException e){
            throw new RuntimeException (e);
        }
    }
    
}
