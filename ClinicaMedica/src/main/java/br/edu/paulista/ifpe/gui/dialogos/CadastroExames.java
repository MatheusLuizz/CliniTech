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
import br.edu.paulista.ifpe.core.cadastros.CadastroExameListener;
import br.edu.paulista.ifpe.core.cadastros.InserirExames;
import br.edu.paulista.ifpe.gui.componentesCustomizados.CampoTextoRedondo;
import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelDegrade;

@SuppressWarnings("serial")
public class CadastroExames extends JDialog {
	private PainelDegrade contentPane;
	private JLabel lblNewLabel;
	private JTextField txtNome;
	private JButton btnCadastro;
	private CadastroExameListener listener;

	public void setListener(CadastroExameListener listener) {
		this.listener = listener;
	}

	public static void main(String[] args) {
		try {
			CadastroExames dialog = new CadastroExames();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir a pagina de cadastro", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public CadastroExames() {
		super();
		setModal(true);
		setTitle("Cadastro de Exames");
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
		lblNewLabel.setBounds(45, 43, 71, 36);
		contentPane.add(lblNewLabel);

		txtNome = new CampoTextoRedondo(10);
		txtNome.setBounds(145, 50, 158, 26);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setBounds(160, 200, 120, 30);
		btnCadastro.setToolTipText("Finalizar cadastro");
		contentPane.add(btnCadastro);

		LimiteCaracteres limiteCaracteres = new LimiteCaracteres();
		limiteCaracteres.adicionarLimiteCaracteres(txtNome, 256);

		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String nome = txtNome.getText();

				if (nome.isBlank()) {
					JOptionPane.showMessageDialog(null, "O campo Nome é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					InserirExames i = new InserirExames();
					i.inserirDados(nome);
					txtNome.setText("");
					txtNome.requestFocus();
					if (listener != null) {
						listener.exameCadastrado();
					}
					dispose();
				}

			}
		});

	}
}