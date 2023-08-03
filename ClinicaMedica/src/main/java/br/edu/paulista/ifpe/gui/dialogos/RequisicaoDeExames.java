package br.edu.paulista.ifpe.gui.dialogos;

import java.awt.Color;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.edu.paulista.ifpe.core.LimiteCaracteres;
import br.edu.paulista.ifpe.data.ConnectionBD;
import br.edu.paulista.ifpe.gui.componentesCustomizados.CampoTextoRedondo;
import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelDegrade;

@SuppressWarnings("serial")
public class RequisicaoDeExames extends JDialog {

	private PainelDegrade contentPane;
	private JLabel lblNewLabel;
	private JTextField txtExames;
	private JLabel lblNewLabel_1;
	private JTextField txtPaciente;
	private int idMedico;
	private JButton btnGerar;

	public static void main(String[] args) {
		try {
			RequisicaoDeExames dialog = new RequisicaoDeExames(131);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar fazer a requisição de exames");
		}
	}

	public RequisicaoDeExames(int idMedico) {
		setTitle("Gerar requisição de exames");
		this.idMedico = idMedico;
		setBounds(100, 100, 450, 300);
		contentPane = new PainelDegrade();
		contentPane.setColors(new Color(0, 128, 255), new Color(50, 205, 50));
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("Exames solicitados");
		lblNewLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
		lblNewLabel.setBounds(10, 23, 124, 25);
		contentPane.add(lblNewLabel);

		txtExames = new CampoTextoRedondo(10);
		txtExames.setBounds(133, 23, 117, 25);
		contentPane.add(txtExames);
		txtExames.setColumns(10);

		lblNewLabel_1 = new JLabel("Nome do paciente");
		lblNewLabel_1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 72, 124, 13);
		contentPane.add(lblNewLabel_1);

		txtPaciente = new CampoTextoRedondo(10);
		txtPaciente.setBounds(133, 66, 117, 25);
		contentPane.add(txtPaciente);
		txtPaciente.setColumns(10);

		btnGerar = new JButton("Gerar requisição");
		btnGerar.setBounds(143, 178, 107, 21);
		contentPane.add(btnGerar);
		btnGerar.addActionListener(e -> gerarRequisicaoPDF());

		LimiteCaracteres limiteCaracteres = new LimiteCaracteres();
		limiteCaracteres.adicionarLimiteCaracteres(txtExames, 255);
		limiteCaracteres.adicionarLimiteCaracteres(txtPaciente, 100);

	}

	private void gerarRequisicaoPDF() {
		String examesSolicitados = txtExames.getText();
		String nomePaciente = txtPaciente.getText();
		Date dataRequisicao = new Date();
		String medicoResponsavel = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {
			ConnectionBD con = new ConnectionBD();
			String query = "SELECT nome, assinatura FROM medico WHERE id = ?";
			try (Connection connection = con.abrir();
					PreparedStatement preparedStatement = connection.prepareStatement(query)) {

				preparedStatement.setInt(1, idMedico);
				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					medicoResponsavel = resultSet.getString("nome");
					byte[] assinaturaBytes = resultSet.getBytes("assinatura");

					Document document = new Document();
					PdfWriter.getInstance(document, new FileOutputStream(getNomeArquivo()));
					document.open();

					Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
					Paragraph title = new Paragraph("Requisição de Exames", titleFont);
					title.setAlignment(Element.ALIGN_CENTER);
					document.add(title);

					@SuppressWarnings("unused")
					Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
					Paragraph content = new Paragraph();
					content.add("Paciente: " + nomePaciente + "\n");
					content.add("Data da Requisição: " + dateFormat.format(dataRequisicao) + "\n");
					content.add("Exames Solicitados: " + examesSolicitados + "\n");
					content.add("Médico Responsável: " + medicoResponsavel + "\n");
					document.add(content);
					if (assinaturaBytes != null && assinaturaBytes.length > 0) {
						Image assinaturaImage = Image.getInstance(assinaturaBytes);
						assinaturaImage.setAlignment(Element.ALIGN_CENTER);
						assinaturaImage.scaleToFit(150, 75);
						document.add(assinaturaImage);
					}

					document.close();
				}
			}
			JOptionPane.showMessageDialog(null, "Requisição gerada com sucesso!");
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao gerar a requisição!");
		}
	}

	private String getNomeArquivo() {
		SimpleDateFormat formatoData = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String dataAtual = formatoData.format(new Date());
		return "RequisicaoDeExame_" + dataAtual + ".pdf";
	}
}
