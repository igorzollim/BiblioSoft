/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexaoBD.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Aluno;
import model.Livro;

/**
 *
 * @author igor
 */
public class AlunoDAO {
    private Connection connection;
    
    public AlunoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Aluno aluno){
        String sql = "INSERT INTO aluno (nome, data_nascimento, sexo, cpf, endereco, email, fone) values (?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
            
            stmt.setString(1, aluno.getNome());
            stmt.setDate(2, new Date(aluno.getDataNascimento().getTimeInMillis()));

            if (aluno.getSexo().equals("Masculino")){
                stmt.setString(3,"M");
            } else{
                stmt.setString(3, "F");
            }
            stmt.setString(4, aluno.getCpf());
            stmt.setString(5, aluno.getEndereco());
            stmt.setString(6, aluno.getEmail());
            stmt.setString(7, aluno.getFone());
            
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            throw new RuntimeException (e);
        }
    }
    
    public List<Aluno> getLista(){
        try{
            List<Aluno> alunos = new ArrayList<Aluno>();
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aluno");
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Aluno aluno = new Aluno();
                
                aluno.setId_aluno(rs.getInt("id_aluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                
                Calendar data_nascimento = Calendar.getInstance();
		data_nascimento.setTime(rs.getDate("data_nascimento"));
		aluno.setDataNascimento(data_nascimento);
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setEmail(rs.getString("email"));
                aluno.setFone(rs.getString("fone"));
                aluno.setSexo(rs.getString("sexo"));
                
                alunos.add(aluno);
            } 
            
            rs.close();
            stmt.close();
            return alunos;
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public void altera(Aluno aluno) {
        String sql = "update aluno set nome=?, data_nascimento=?, sexo=?,"
                + "cpf = ?, endereco=?, email=?, fone=? where id_aluno=?";
        
        try {
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

            stmt.setString(1, aluno.getNome());
            stmt.setDate(2, new Date(aluno.getDataNascimento().getTimeInMillis()));
            
            if (aluno.getSexo().equals("Masculino")){
                stmt.setString(3,"M");
            } else{
                stmt.setString(3, "F");
            }
            
            stmt.setString(4, aluno.getCpf());
            stmt.setString(5, aluno.getEndereco());
            stmt.setString(6, aluno.getEmail());
            stmt.setString(7, aluno.getFone());
            stmt.setInt(8, aluno.getId_aluno());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public Aluno getAluno(int id_aluno){
        List<Aluno> alunos = getLista();
        
        for(Aluno aluno : alunos){
            if (aluno.getId_aluno() == id_aluno){
                return aluno;
            }
        }
        return null;
    }
    
    public void deletar(int id_aluno){
        try{
            String sql = "DELETE FROM aluno WHERE id_aluno=" + id_aluno;
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Aluno> pesquisa(String nome){
        try{
            List<Aluno> alunos = new ArrayList<Aluno>();
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aluno WHERE aluno.nome LIKE '"+nome+"%'");
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Aluno aluno = new Aluno();
                
                aluno.setId_aluno(rs.getInt("id_aluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setEndereco(rs.getString("endereco"));
                
                Calendar data_nascimento = Calendar.getInstance();
		data_nascimento.setTime(rs.getDate("data_nascimento"));
		aluno.setDataNascimento(data_nascimento);
                
                aluno.setEmail(rs.getString("email"));
                aluno.setFone(rs.getString("fone"));
                aluno.setSexo(rs.getString("sexo"));
                
                
                alunos.add(aluno);
            } 
            
            rs.close();
            stmt.close();
            return alunos;
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
