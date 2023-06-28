package br.edu.paulista.ifpe.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionBD {
	private Connection connection;
	
	public Connection abrir() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/clinicamedica?serverTimezone=America/Sao_Paulo";
            connection = DriverManager.getConnection(url, "root", "");
            connection.createStatement(0, 0);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
        return connection;
    }
	
	public void fechar() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
                JOptionPane.showMessageDialog(null, "Conexão fechada com sucesso.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão com o banco de dados.");
            e.printStackTrace();
        }
    }
}