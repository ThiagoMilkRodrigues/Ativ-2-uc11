/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
           try {
        Connection conn = new conectaDAO().connectDB(); // conecta no banco

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getValor());
        stmt.setString(3, produto.getStatus());

        int rows = stmt.executeUpdate(); // retorna quantas linhas foram inseridas

        stmt.close();
        conn.close();

        return rows > 0; // se inseriu, retorna true
    } catch (SQLException e) {
        System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        return false;
    }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){ArrayList<ProdutosDTO> lista = new ArrayList<>();

    try {
        Connection conn = new conectaDAO().connectDB(); // conectar no banco
        String sql = "SELECT * FROM produtos";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ProdutosDTO prod = new ProdutosDTO();
            prod.setId(rs.getInt("id")); // se tiver ID
            prod.setNome(rs.getString("nome"));
            prod.setValor(rs.getInt("valor"));
            prod.setStatus(rs.getString("status"));
            lista.add(prod);
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
    }

    return lista;
    }
    
    
    
        
}

