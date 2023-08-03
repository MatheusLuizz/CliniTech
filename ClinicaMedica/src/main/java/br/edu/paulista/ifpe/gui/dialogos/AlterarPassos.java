package br.edu.paulista.ifpe.gui.dialogos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.edu.paulista.ifpe.core.cadastros.AlterarPassosListener;
import br.edu.paulista.ifpe.data.ConnectionBD;
import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelDegrade;

@SuppressWarnings("serial")
public class AlterarPassos extends JDialog {
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txt;
	private JButton btnAlterar;
	private AlterarPassosListener listener;
	@SuppressWarnings("unused")
	private String id;
	private int idInt;

	public void setListener(AlterarPassosListener listener) {
		this.listener = listener;
	}

	public AlterarPassos(String id) {
		this.id = id;
		idInt = Integer.parseInt(id);
		contentPane = new PainelDegrade();
		setContentPane(contentPane);
		((PainelDegrade) contentPane).setColors(new Color(0, 128, 255), new Color(50, 205, 50));
		contentPane.repaint();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setFont(new Font("Arial", Font.BOLD, 22));
		setModal(true);
		setType(Type.UTILITY);

		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Detalhes do Paciente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		lblNewLabel = new JLabel("Altere os próximos passos:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 25, 158, 28);
		contentPane.add(lblNewLabel);

		txt = new JTextField();
		txt.setBounds(10, 79, 158, 57);
		contentPane.add(txt);
		txt.setColumns(10);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Arial", Font.BOLD, 12));
		btnAlterar.setBounds(150, 179, 85, 21);
		contentPane.add(btnAlterar);
		btnAlterar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = txt.getText();
				if (listener != null) {
					listener.passosAlterados();
				}
				System.out.println(idInt);
				alterarDados(texto, idInt);

				dispose();

			}

		});

	}

	private void alterarDados(String texto, int id) {
		ConnectionBD conn = new ConnectionBD();
		PreparedStatement st;
		String query = "UPDATE clinicamedica.historico SET proximos_passos = ? WHERE (id = ?);";
		try {
			st = conn.abrir().prepareStatement(query);

			st.setString(1, texto);
			st.setInt(2, id);

			st.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao alterar os dados no banco de dados. Por favor, tente novamente.");
		} finally {
			conn.fechar();
		}
	}

}
