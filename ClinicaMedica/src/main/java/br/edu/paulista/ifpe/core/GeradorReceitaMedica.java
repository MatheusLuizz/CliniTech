package br.edu.paulista.ifpe.core;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.paulista.ifpe.data.ConnectionBD;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GeradorReceitaMedica {

    public String gerarReceitaMedica(int pacienteId, int medicoId, String pacienteNome, String medicoNome, String recomendacoes, String[] remedios) {
        Document document = new Document();
        ConnectionBD connectionBD = new ConnectionBD();
        Connection connection = connectionBD.abrir();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("ReceitaMedica.pdf"));

            document.open();

            // Obter a data atual do computador
            Date dataAtual = new Date();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            String dataConsulta = formatoData.format(dataAtual);

            document.add(new Paragraph("Receita Médica"));
            document.add(new Paragraph("Nome do paciente: " + pacienteNome));
            document.add(new Paragraph("Data da consulta: " + dataConsulta));
            document.add(new Paragraph("Recomendações: " + recomendacoes));

            document.add(new Paragraph("Assinatura do médico: " + medicoNome));

            // Adicionar os remédios prescritos pelo médico
            document.add(new Paragraph("Remédios prescritos:"));
            for (String remedio : remedios) {
                document.add(new Paragraph("- " + remedio));
            }

            document.close();
            System.out.println("Receita Médica gerada com sucesso!");

            // Retornar a data da consulta
            return dataConsulta;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionBD.fechar();
        }
        return null;
    }

    // Métodos para buscar o nome do médico e do paciente pelo ID
    private String buscarNomePaciente(Connection connection, int pacienteId) throws SQLException {
        String nomePaciente = null;
        String sql = "SELECT nome FROM paciente WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pacienteId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nomePaciente = resultSet.getString("nome");
                }
            }
        }
        return nomePaciente;
    }

    private String buscarNomeMedico(Connection connection, int medicoId) throws SQLException {
        String nomeMedico = null;
        String sql = "SELECT nome FROM medico WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, medicoId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nomeMedico = resultSet.getString("nome");
                }
            }
        }
        return nomeMedico;
    }

    private byte[] buscarAssinaturaMedico(Connection connection, int medicoId) throws SQLException {
        byte[] assinatura = null;
        String sql = "SELECT assinatura FROM medico WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, medicoId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    assinatura = resultSet.getBytes("assinatura");
                }
            }
        }
        return assinatura;
    }
}