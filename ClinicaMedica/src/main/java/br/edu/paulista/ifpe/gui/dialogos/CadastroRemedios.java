package br.edu.paulista.ifpe.gui.dialogos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.edu.paulista.ifpe.core.LimiteCaracteres;
import br.edu.paulista.ifpe.core.cadastros.CadastroRemedioListener;
import br.edu.paulista.ifpe.core.cadastros.InserirRemedios;
import br.edu.paulista.ifpe.gui.componentesCustomizados.CampoTextoRedondo;
import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelDegrade;

@SuppressWarnings("serial")
public class CadastroRemedios extends JDialog {

	private PainelDegrade contentPane;
	private JLabel lblNewLabel;
	private JTextField txtNome;
	private JLabel lblNewLabel_1;
	private JTextField txtApresentacao;
	private JButton btnCadastro;
	private CadastroRemedioListener listener;

	public void setListener(CadastroRemedioListener listener) {
		this.listener = listener;
	}

	public static void main(String[] args) {
		try {
			CadastroRemedios dialog = new CadastroRemedios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir a pagina de cadastro", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public CadastroRemedios() {
		super();
		setModal(true);
		setTitle("Cadastro de Remédios");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new PainelDegrade();
		contentPane.setColors(new Color(0, 128, 255), new Color(50, 205, 50));
		contentPane.repaint();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("Nome");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 13, 71, 36);
		contentPane.add(lblNewLabel);

		txtNome = new CampoTextoRedondo(10);
		txtNome.setBounds(117, 20, 158, 26);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		lblNewLabel_1 = new JLabel("Apresentação");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 82, 90, 13);
		contentPane.add(lblNewLabel_1);

		txtApresentacao = new CampoTextoRedondo(10);
		txtApresentacao.setBounds(117, 77, 158, 26);
		contentPane.add(txtApresentacao);
		txtApresentacao.setColumns(10);

		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setBounds(160, 200, 120, 30);
		btnCadastro.setToolTipText("Finalizar cadastro");
		contentPane.add(btnCadastro);

		LimiteCaracteres limiteCaracteres = new LimiteCaracteres();
		limiteCaracteres.adicionarLimiteCaracteres(txtNome, 255);
		limiteCaracteres.adicionarLimiteCaracteres(txtApresentacao, 255);

		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String nome = txtNome.getText();
				final String apresentacao = txtApresentacao.getText();

				if (nome.isBlank()) {
					JOptionPane.showMessageDialog(null, "O campo Nome é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else if (apresentacao.isBlank()) {
					JOptionPane.showMessageDialog(null, "O campo apresentacao é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					InserirRemedios i = new InserirRemedios();
					i.inserirDados(nome, apresentacao);
					txtNome.setText("");
					txtApresentacao.setText("");
					txtNome.requestFocus();
					if (listener != null) {
						listener.remedioCadastrado();
					}
					dispose();
				}

			}
		});

	}
}
