package br.edu.paulista.ifpe.core;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import br.edu.paulista.ifpe.data.ConnectionBD;

public class GeradorReceitaMedica {

	public void gerarReceitaMedica(int medicoId, String pacienteNome, String[] remedios, String[] dosagens) {
		Document document = new Document(PageSize.A4);
		ConnectionBD connectionBD = new ConnectionBD();
		Connection connection = connectionBD.abrir();

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getNomeArquivo()));
			document.open();
			PdfContentByte content = writer.getDirectContent();

			Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
			Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

			Rectangle rect = new Rectangle(50, 50, 550, 800);
			rect.setBorder(Rectangle.BOX);
			rect.setBorderWidth(2);
			content.rectangle(rect);

			Paragraph titulo = new Paragraph("Receituário", fontTitulo);
			titulo.setAlignment(Element.ALIGN_CENTER);
			document.add(titulo);

			content.moveTo(50, 760);
			content.lineTo(550, 760);
			content.stroke();

			if (remedios != null && remedios.length > 0) {
				Paragraph remediosParagraph = new Paragraph();
				remediosParagraph.setAlignment(Element.ALIGN_LEFT);
				remediosParagraph.setSpacingBefore(200);
				remediosParagraph.setIndentationLeft(25);

				for (int i = 0; i < remedios.length; i++) {
					String nomeRemedio = remedios[i];
					String dosagemRemedio = dosagens[i];
					remediosParagraph.setAlignment(Element.ALIGN_LEFT);
					remediosParagraph.setSpacingBefore(200);
					remediosParagraph.setIndentationLeft(25);
					remediosParagraph.add(new Phrase("- " + nomeRemedio + " - Dosagem: " + dosagemRemedio, fontNormal));
					remediosParagraph.add(Chunk.NEWLINE);
				}
				document.add(remediosParagraph);
			} else {
				document.add(new Paragraph("- Nenhum remédio prescrito.", fontNormal));
			}

			Paragraph dataConsulta = new Paragraph();
			dataConsulta.add(new Phrase("Data da consulta: ", fontNormal));
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			String data = formatoData.format(new Date());
			dataConsulta.add(new Phrase(data, fontNormal));
			dataConsulta.setAlignment(Element.ALIGN_LEFT);
			dataConsulta.setSpacingBefore(250);
			dataConsulta.setIndentationLeft(25);
			document.add(dataConsulta);

			String medicoNome = buscarNomeMedico(connection, medicoId);
			byte[] assinaturaMedico = buscarAssinaturaMedico(connection, medicoId);
			if (medicoNome != null && assinaturaMedico != null) {
				document.add(Chunk.NEWLINE);

				Image imagemAssinatura = Image.getInstance(assinaturaMedico);

				imagemAssinatura.scaleToFit(150, 100);

				imagemAssinatura.setAlignment(Element.ALIGN_RIGHT);
				imagemAssinatura.setIndentationRight(25);
				document.add(imagemAssinatura);

				Paragraph medicoParagraph = new Paragraph("Dr. " + medicoNome, fontNormal);
				medicoParagraph.setAlignment(Element.ALIGN_RIGHT);
				medicoParagraph.setIndentationRight(40);

				document.add(medicoParagraph);
			}

			document.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar a receita!");
		} finally {
			connectionBD.fechar();
		}
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

	private String getNomeArquivo() {
		SimpleDateFormat formatoData = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String dataAtual = formatoData.format(new Date());
		return "ReceitaMedica_" + dataAtual + ".pdf";
	}
}