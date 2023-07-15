package br.edu.paulista.ifpe.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.paulista.ifpe.core.LimiteCaracteres;
import br.edu.paulista.ifpe.core.LimparCamposMedico;
import br.edu.paulista.ifpe.core.util.cadastroMedicos.InserirMedico;

@SuppressWarnings("serial")
public class CadastroMedico extends JDialog {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtCrm;
	private JLabel lblNewLabel_1;
	private JTextField txtNome;
	private JLabel lblNewLabel_2;
	private JTextField txtCpf;
	private JLabel lblNewLabel_3;
	private JTextField txtRg;
	private JLabel lblNewLabel_4;
	private JTextField txtTelefone;
	private JLabel lblNewLabel_5;
	private JTextField txtEspecialidade;
	private JButton btnCadastro;

	public static void main(String[] args) {
		try {
			CadastroMedico dialog = new CadastroMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Um erro crítico ocorreu :(", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public CadastroMedico() {
		super();
		setModal(true);
		setTitle("Cadastro de Médicos");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("CRM");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 10, 44, 20);
		contentPane.add(lblNewLabel);

		try {
			MaskFormatter mascaraCrm = new MaskFormatter("######CRM/??");
			txtCrm = new JFormattedTextField(mascaraCrm);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do CRM", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtCrm.setBounds(64, 10, 96, 19);
		contentPane.add(txtCrm);
		txtCrm.setColumns(10);

		lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 40, 44, 20);
		contentPane.add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.setBounds(64, 40, 96, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 70, 35, 20);
		contentPane.add(lblNewLabel_2);

		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			txtCpf = new JFormattedTextField(mascaraCpf);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do CPF", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtCpf.setBounds(64, 70, 96, 19);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);

		lblNewLabel_3 = new JLabel("RG");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 100, 45, 13);
		contentPane.add(lblNewLabel_3);

		try {
			MaskFormatter mask = new MaskFormatter("########"); // Define a máscara para 7 ou 8 dígitos numéricos
			txtRg = new JFormattedTextField(mask);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do RG", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtRg.setBounds(64, 100, 96, 19);
		contentPane.add(txtRg);
		txtRg.setColumns(10);

		lblNewLabel_4 = new JLabel("Telefone");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 130, 64, 13);
		contentPane.add(lblNewLabel_4);

		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##) #####-####");
			txtTelefone = new JFormattedTextField(mascaraTelefone);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do celular", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtTelefone.setBounds(85, 130, 96, 19);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		lblNewLabel_5 = new JLabel("Especialidade");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_5.setBounds(10, 160, 111, 13);
		contentPane.add(lblNewLabel_5);

		txtEspecialidade = new JTextField();
		txtEspecialidade.setBounds(120, 160, 96, 19);
		contentPane.add(txtEspecialidade);
		txtEspecialidade.setColumns(10);

		// Definindo limite de caracteres nos campos de texto
		LimiteCaracteres limiteCaracteres = new LimiteCaracteres();
		limiteCaracteres.adicionarLimiteCaracteres(txtNome, 100);
		limiteCaracteres.adicionarLimiteCaracteres(txtEspecialidade, 100);

		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setToolTipText("Finalizar cadastro");
		btnCadastro.setFont(new Font("Arial", Font.BOLD, 16));
		btnCadastro.setBounds(160, 200, 120, 30);
		contentPane.add(btnCadastro);

		btnCadastro.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				final String crm = txtCrm.getText();
				final String nome = txtNome.getText();
				final String cpf = txtCpf.getText();
				final String rg = txtRg.getText();
				final String telefone = txtTelefone.getText();
				final String especialidade = txtEspecialidade.getText();

				if (crm.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo CRM é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtCrm.requestFocus();
				} else if (nome.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo Nome é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtNome.requestFocus();
				} else if (cpf.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo Cpf é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtCpf.requestFocus();
				} else if (rg.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo Rg é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
					txtRg.requestFocus();
				} else if (telefone.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo Telefone é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtTelefone.requestFocus();
				} else if (especialidade.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo Especialidade é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtEspecialidade.requestFocus();
				} else {
					InserirMedico i = new InserirMedico();
					i.inserirDados(crm, nome, cpf, rg, telefone, especialidade);
					LimparCamposMedico limpar = new LimparCamposMedico();
					limpar.limparCampos(txtCrm, txtNome, txtCpf, txtRg, txtTelefone, txtEspecialidade);
					dispose();
				}
			}
		});
	}
}