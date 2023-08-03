package br.edu.paulista.ifpe.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.paulista.ifpe.core.LimiteCaracteres;
import br.edu.paulista.ifpe.core.VerificadorEmail;
import br.edu.paulista.ifpe.core.util.cadastroMedicos.CadastroPacienteListener;
import br.edu.paulista.ifpe.core.util.cadastroPacientes.InserirPaciente;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.CampoTextoRedondo;

@SuppressWarnings("serial")
public class CadastroPaciente extends JDialog {

	private PainelDegrade contentPane;
	private JLabel lblNewLabel;
	private CampoTextoRedondo txtNome;
	private JLabel lblNewLabel_1;
	private CampoTextoFormatadoRedondo txtCpf;
	private JLabel lblNewLabel_2;
	private CampoTextoFormatadoRedondo txtRg;
	private JLabel lblNewLabel_4;
	private CampoTextoFormatadoRedondo txtTelefone;
	private JLabel lblNewLabel_5;
	private CampoTextoRedondo txtEmail;
	private JLabel lblNewLabel_6;
	private CampoTextoRedondo txtRua;
	private JLabel lblNumero;
	private CampoTextoRedondo txtNumero;
	private JLabel lblNewLabel_8;
	private CampoTextoRedondo txtComplemento;
	private JLabel lblNewLabel_9;
	private CampoTextoRedondo txtBairro;
	@SuppressWarnings("rawtypes")
	private ComboBoxCustomizada boxUf;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_10;
	@SuppressWarnings("rawtypes")
	private ComboBoxCustomizada boxSexo;
	private JLabel lblNewLabel_11;
	private CampoTextoFormatadoRedondo txtCep;
	private JLabel lblNewLabel_12;
	private CampoTextoRedondo txtCidade;
	private JLabel lblNewLabel_13;
	private CampoTextoFormatadoRedondo txtNascimento;
	private JLabel lblNewLabel_14;
	@SuppressWarnings("rawtypes")
	private ComboBoxCustomizada boxEstadoCivil;
	private JLabel lblNewLabel_15;
	private JLabel lblNewLabel_16;
	private CampoTextoRedondo txtNomeMae;
	private CampoTextoRedondo txtNomePai;
	private JButton btnCadastro;
	private CadastroPacienteListener listener;

	public void setListener(CadastroPacienteListener listener) {
		this.listener = listener;
	}

	private boolean concluido = false;

	public boolean isConcluido() {
		return concluido;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPaciente frame = new CadastroPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Não foi possível exibir a tela de cadastro", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CadastroPaciente() {
		super();
		setModal(true);
		setType(Type.UTILITY);
		setTitle("Cadastro de Pacientes");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new PainelDegrade();
		contentPane.setColors(new Color(0, 128, 255), new Color(50, 205, 50));
		contentPane.repaint();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 10, 33, 13);
		contentPane.add(lblNewLabel);

		txtNome = new CampoTextoRedondo(10);
		txtNome.setFont(new Font("Arial", Font.BOLD, 12));
		txtNome.setBounds(49, 7, 197, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(256, 10, 45, 13);
		contentPane.add(lblNewLabel_1);

		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			txtCpf = new CampoTextoFormatadoRedondo(mascaraCpf, 15);
			txtCpf.setFont(new Font("Arial", Font.BOLD, 12));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do CPF", "Erro", JOptionPane.ERROR_MESSAGE);
		}

		txtCpf.setBounds(290, 7, 136, 19);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);

		lblNewLabel_2 = new JLabel("RG");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 33, 26, 13);
		contentPane.add(lblNewLabel_2);

		try {
			MaskFormatter mascaraRg = new MaskFormatter("########");
			txtRg = new CampoTextoFormatadoRedondo(mascaraRg, 10);
			txtRg.setFont(new Font("Arial", Font.BOLD, 12));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do RG", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtRg.setBounds(33, 30, 104, 19);
		contentPane.add(txtRg);
		txtRg.setColumns(10);

		lblNewLabel_4 = new JLabel("Celular");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_4.setBounds(147, 33, 45, 13);
		contentPane.add(lblNewLabel_4);

		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##) #####-####");
			txtTelefone = new CampoTextoFormatadoRedondo(mascaraTelefone, 10);
			txtTelefone.setFont(new Font("Arial", Font.BOLD, 12));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do celular", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtTelefone.setBounds(200, 30, 119, 19);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 56, 45, 13);
		contentPane.add(lblNewLabel_5);

		txtEmail = new CampoTextoRedondo(10);
		txtEmail.setFont(new Font("Arial", Font.BOLD, 12));
		txtEmail.setBounds(49, 53, 128, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setInputVerifier(new VerificadorEmail());

		lblNewLabel_6 = new JLabel("Rua");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_6.setBounds(187, 56, 26, 13);
		contentPane.add(lblNewLabel_6);

		txtRua = new CampoTextoRedondo(10);
		txtRua.setFont(new Font("Arial", Font.BOLD, 12));
		txtRua.setBounds(223, 53, 96, 19);
		contentPane.add(txtRua);
		txtRua.setColumns(10);

		lblNumero = new JLabel("Nº");
		lblNumero.setFont(new Font("Arial", Font.BOLD, 12));
		lblNumero.setBounds(233, 170, 13, 13);
		contentPane.add(lblNumero);

		txtNumero = new CampoTextoRedondo(10);
		txtNumero.setFont(new Font("Arial", Font.BOLD, 12));
		txtNumero.setBounds(256, 167, 33, 19);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		lblNewLabel_8 = new JLabel("Complemento");
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_8.setBounds(10, 88, 78, 13);
		contentPane.add(lblNewLabel_8);

		txtComplemento = new CampoTextoRedondo(10);
		txtComplemento.setFont(new Font("Arial", Font.BOLD, 12));
		txtComplemento.setBounds(98, 85, 78, 19);
		contentPane.add(txtComplemento);
		txtComplemento.setColumns(10);

		lblNewLabel_9 = new JLabel("Bairro");
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_9.setBounds(197, 88, 45, 13);
		contentPane.add(lblNewLabel_9);

		txtBairro = new CampoTextoRedondo(10);
		txtBairro.setFont(new Font("Arial", Font.BOLD, 12));
		txtBairro.setBounds(246, 85, 96, 19);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		boxUf = new ComboBoxCustomizada();
		boxUf.setFont(new Font("Arial", Font.BOLD, 12));
		boxUf.setModel(new DefaultComboBoxModel(new String[] { "Acre (AC)", "Alagoas (AL)", "Amapá (AP)",
				"Amazonas (AM)", "Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", "Espírito Santo (ES)",
				"Goiás (GO)", "Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)",
				"Pará (PA)", "Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)",
				"Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)",
				"Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)" }));
		boxUf.setBounds(33, 107, 170, 21);
		contentPane.add(boxUf);

		lblNewLabel_7 = new JLabel("UF");
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_7.setBounds(10, 111, 18, 13);
		contentPane.add(lblNewLabel_7);

		lblNewLabel_10 = new JLabel("Sexo");
		lblNewLabel_10.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_10.setBounds(163, 138, 45, 13);
		contentPane.add(lblNewLabel_10);

		boxSexo = new ComboBoxCustomizada();
		boxSexo.setFont(new Font("Arial", Font.BOLD, 12));
		boxSexo.setModel(new DefaultComboBoxModel(new String[] { "M", "F" }));
		boxSexo.setBounds(200, 138, 41, 21);
		contentPane.add(boxSexo);

		lblNewLabel_11 = new JLabel("CEP");
		lblNewLabel_11.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_11.setBounds(213, 111, 26, 13);
		contentPane.add(lblNewLabel_11);

		try {
			MaskFormatter mascaraCep = new MaskFormatter("#####-###");
			txtCep = new CampoTextoFormatadoRedondo(mascaraCep, 10);
			txtCep.setFont(new Font("Arial", Font.BOLD, 12));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do CEP", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtCep.setBounds(246, 108, 96, 19);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		lblNewLabel_12 = new JLabel("Cidade");
		lblNewLabel_12.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_12.setBounds(10, 138, 45, 13);
		contentPane.add(lblNewLabel_12);

		txtCidade = new CampoTextoRedondo(10);
		txtCidade.setFont(new Font("Arial", Font.BOLD, 12));
		txtCidade.setBounds(57, 135, 96, 19);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		lblNewLabel_13 = new JLabel("Nascimento");
		lblNewLabel_13.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_13.setBounds(246, 138, 73, 13);
		contentPane.add(lblNewLabel_13);

		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			txtNascimento = new CampoTextoFormatadoRedondo(mascaraData, 10);
			txtNascimento.setFont(new Font("Arial", Font.BOLD, 12));

			txtNascimento.setInputVerifier(new InputVerifier() {
				@Override
				public boolean verify(JComponent input) {
					CampoTextoFormatadoRedondo campoTexto = (CampoTextoFormatadoRedondo) input;
					String text = campoTexto.getText();
					String[] parts = text.split("/");

					if (parts.length != 3) {
						JOptionPane.showMessageDialog(null, "Data inválida. O correto é: DD/MM/AAAA", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}

					int day, month;
					try {
						day = Integer.parseInt(parts[0]);
						month = Integer.parseInt(parts[1]);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Data inválida. O correto é: DD/MM/AAAA", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}

					if (month < 1 || month > 12 || day < 1 || day > 31) {
						JOptionPane.showMessageDialog(null,
								"Data inválida. Digite um mês de 01 a 12 e um dia de 01 a 31", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}

					return true;
				}
			});
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação da data de nascimento. O correto é: DD/MM/AAAA",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtNascimento.setBounds(329, 135, 96, 19);
		contentPane.add(txtNascimento);
		txtNascimento.setColumns(10);

		lblNewLabel_14 = new JLabel("Estado civil");
		lblNewLabel_14.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_14.setBounds(10, 170, 78, 13);
		contentPane.add(lblNewLabel_14);

		boxEstadoCivil = new ComboBoxCustomizada();
		boxEstadoCivil.setFont(new Font("Arial", Font.BOLD, 12));
		boxEstadoCivil.setModel(new DefaultComboBoxModel(new String[] { "Solteiro", "Casado", "Viuvo", "Divorciado" }));
		boxEstadoCivil.setBounds(96, 166, 117, 21);
		contentPane.add(boxEstadoCivil);

		lblNewLabel_15 = new JLabel("Nome da mãe");
		lblNewLabel_15.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_15.setBounds(10, 200, 78, 13);
		contentPane.add(lblNewLabel_15);

		lblNewLabel_16 = new JLabel("Nome do Pai");
		lblNewLabel_16.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_16.setBounds(10, 226, 78, 13);
		contentPane.add(lblNewLabel_16);

		txtNomeMae = new CampoTextoRedondo(10);
		txtNomeMae.setFont(new Font("Arial", Font.BOLD, 12));
		txtNomeMae.setBounds(98, 196, 148, 19);
		contentPane.add(txtNomeMae);
		txtNomeMae.setColumns(10);

		txtNomePai = new CampoTextoRedondo(10);
		txtNomePai.setFont(new Font("Arial", Font.BOLD, 12));
		txtNomePai.setBounds(98, 223, 148, 19);
		contentPane.add(txtNomePai);
		txtNomePai.setColumns(10);

		LimiteCaracteres limiteCaracteres = new LimiteCaracteres();
		limiteCaracteres.adicionarLimiteCaracteres(txtNome, 100);
		limiteCaracteres.adicionarLimiteCaracteres(txtEmail, 100);
		limiteCaracteres.adicionarLimiteCaracteres(txtRua, 100);
		limiteCaracteres.adicionarLimiteCaracteres(txtNumero, 10);
		limiteCaracteres.adicionarLimiteCaracteres(txtComplemento, 100);
		limiteCaracteres.adicionarLimiteCaracteres(txtBairro, 100);
		limiteCaracteres.adicionarLimiteCaracteres(txtCidade, 100);
		limiteCaracteres.adicionarLimiteCaracteres(txtNomeMae, 100);
		limiteCaracteres.adicionarLimiteCaracteres(txtNomePai, 100);

		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setToolTipText("Finalizar cadastro");
		btnCadastro.setFont(new Font("Arial", Font.BOLD, 16));
		btnCadastro.setBounds(290, 218, 109, 21);
		contentPane.add(btnCadastro);
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final String nome = txtNome.getText();
				final String cpf = txtCpf.getText();
				final String rg = txtRg.getText();
				final String telefone = txtTelefone.getText();
				final String email = txtEmail.getText();
				final String rua = txtRua.getText();
				final String numero = txtNumero.getText();
				final String complemento = txtComplemento.getText();
				final String bairro = txtBairro.getText();
				final String cep = txtCep.getText();
				final String cidade = txtCidade.getText();
				final String uf = (String) boxUf.getSelectedItem();
				final String nascimento = txtNascimento.getText();
				final String sexo = (String) boxSexo.getSelectedItem();
				final String estadoCivil = (String) boxEstadoCivil.getSelectedItem();
				final String nomePai = txtNomePai.getText();
				final String nomeMae = txtNomeMae.getText();
				if (nome.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo nome é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtNome.requestFocus();
				} else if (cpf.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo cpf é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtCpf.requestFocus();
				} else if (rg.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo rg é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
					txtRg.requestFocus();
				} else if (telefone.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo telefone é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtTelefone.requestFocus();
				} else if (email.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo e-mail é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtEmail.requestFocus();
				} else if (rua.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo rua é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtRua.requestFocus();
				} else if (numero.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo numero é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtNumero.requestFocus();
				} else if (bairro.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo bairro é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtBairro.requestFocus();
				} else if (cep.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo cep é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtCep.requestFocus();
				} else if (cidade.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo cidade é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtCidade.requestFocus();
				} else if (nascimento.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo nascimento é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtNascimento.requestFocus();
				} else if (nomeMae.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo nome da mãe é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					txtNomeMae.requestFocus();
				} else {
					String dataNascimentoDigitada = txtNascimento.getText();
					String[] partesData = dataNascimentoDigitada.split("/");
					String nascimentoSQL = partesData[2] + "-" + partesData[1] + "-" + partesData[0];

					InserirPaciente i = new InserirPaciente();
					i.inserirDados(nome, cpf, rg, telefone, email, rua, numero, complemento, bairro, cep, cidade, uf,
							nascimentoSQL, sexo, estadoCivil, nomePai, nomeMae);

					if (listener != null) {
						listener.pacienteCadastrado();
					}
					dispose();
				}
			}
		});
	}

}
