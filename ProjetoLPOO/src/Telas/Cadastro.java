package Telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Entidades.ArmazenamentoPacientes;
import Entidades.Paciente;

@SuppressWarnings("serial")
public class Cadastro extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtNome;
	private JLabel lblNewLabel_1;
	private JTextField txtCpf;
	private JLabel lblNewLabel_2;
	private JTextField txtRg;
	private JLabel lblNewLabel_3;
	private JTextField txtDdd;
	private JLabel lblNewLabel_4;
	private JTextField txtTelefone;
	private JLabel lblNewLabel_5;
	private JTextField txtEmail;
	private JLabel lblNewLabel_6;
	private JTextField txtRua;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JLabel lblNewLabel_8;
	private JTextField txtComplemento;
	private JLabel lblNewLabel_9;
	private JTextField txtBairro;
	@SuppressWarnings("rawtypes")
	private JComboBox boxUf;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_10;
	@SuppressWarnings("rawtypes")
	private JComboBox boxSexo;
	private JLabel lblNewLabel_11;
	private JTextField txtCep;
	private JLabel lblNewLabel_12;
	private JTextField txtCidade;
	private JLabel lblNewLabel_13;
	private JTextField txtNascimento;
	private JLabel lblNewLabel_14;
	private JTextField txtEstadoCivil;
	private JLabel lblNewLabel_15;
	private JLabel lblNewLabel_16;
	private JTextField txtNomeMae;
	private JTextField txtNomePai;
	private JButton btnNewButton;
	ArmazenamentoPacientes armazenarPaciente = new ArmazenamentoPacientes();
	private JButton btnHome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 10, 33, 13);
		contentPane.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(49, 7, 197, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(256, 10, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(290, 7, 136, 19);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
		
		lblNewLabel_2 = new JLabel("RG");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 33, 26, 13);
		contentPane.add(lblNewLabel_2);
		
		txtRg = new JTextField();
		txtRg.setBounds(33, 30, 104, 19);
		contentPane.add(txtRg);
		txtRg.setColumns(10);
		
		lblNewLabel_3 = new JLabel("DDD");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_3.setBounds(147, 36, 26, 13);
		contentPane.add(lblNewLabel_3);
		
		txtDdd = new JTextField();
		txtDdd.setBounds(177, 30, 26, 19);
		contentPane.add(txtDdd);
		txtDdd.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Celular");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_4.setBounds(213, 36, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(266, 30, 119, 19);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 56, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(49, 53, 128, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Rua");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_6.setBounds(187, 56, 26, 13);
		contentPane.add(lblNewLabel_6);
		
		txtRua = new JTextField();
		txtRua.setBounds(223, 53, 96, 19);
		contentPane.add(txtRua);
		txtRua.setColumns(10);
		
		lblNumero = new JLabel("Nº");
		lblNumero.setFont(new Font("Arial", Font.BOLD, 12));
		lblNumero.setBounds(329, 59, 13, 13);
		contentPane.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(352, 53, 33, 19);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		lblNewLabel_8 = new JLabel("Complemento");
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_8.setBounds(10, 88, 78, 13);
		contentPane.add(lblNewLabel_8);
		
		txtComplemento = new JTextField();
		txtComplemento.setBounds(98, 85, 78, 19);
		contentPane.add(txtComplemento);
		txtComplemento.setColumns(10);
		
		lblNewLabel_9 = new JLabel("Bairro");
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_9.setBounds(197, 88, 45, 13);
		contentPane.add(lblNewLabel_9);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(246, 85, 96, 19);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);
		
		boxUf = new JComboBox();
		boxUf.setFont(new Font("Arial", Font.PLAIN, 10));
		boxUf.setModel(new DefaultComboBoxModel(new String[] {"Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)", "Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", "Espírito Santo (ES)", "Goiás (GO)", "Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", "Pará (PA)", "Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)", "Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)", "Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)"}));
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
		
		boxSexo = new JComboBox();
		boxSexo.setFont(new Font("Arial", Font.PLAIN, 10));
		boxSexo.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		boxSexo.setBounds(197, 134, 41, 21);
		contentPane.add(boxSexo);
		
		lblNewLabel_11 = new JLabel("CEP");
		lblNewLabel_11.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_11.setBounds(213, 111, 26, 13);
		contentPane.add(lblNewLabel_11);
		
		txtCep = new JTextField();
		txtCep.setBounds(246, 108, 96, 19);
		contentPane.add(txtCep);
		txtCep.setColumns(10);
		
		lblNewLabel_12 = new JLabel("Cidade");
		lblNewLabel_12.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_12.setBounds(10, 138, 45, 13);
		contentPane.add(lblNewLabel_12);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(57, 135, 96, 19);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);
		
		lblNewLabel_13 = new JLabel("Nascimento");
		lblNewLabel_13.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_13.setBounds(246, 138, 73, 13);
		contentPane.add(lblNewLabel_13);
		
		txtNascimento = new JTextField();
		txtNascimento.setBounds(329, 135, 96, 19);
		contentPane.add(txtNascimento);
		txtNascimento.setColumns(10);
		
		lblNewLabel_14 = new JLabel("Estado civil");
		lblNewLabel_14.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_14.setBounds(10, 170, 78, 13);
		contentPane.add(lblNewLabel_14);
		
		txtEstadoCivil = new JTextField();
		txtEstadoCivil.setBounds(84, 167, 119, 19);
		contentPane.add(txtEstadoCivil);
		txtEstadoCivil.setColumns(10);
		
		lblNewLabel_15 = new JLabel("Nome da mãe");
		lblNewLabel_15.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_15.setBounds(10, 200, 78, 13);
		contentPane.add(lblNewLabel_15);
		
		lblNewLabel_16 = new JLabel("Nome do Pai");
		lblNewLabel_16.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_16.setBounds(10, 226, 78, 13);
		contentPane.add(lblNewLabel_16);
		
		txtNomeMae = new JTextField();
		txtNomeMae.setBounds(98, 196, 148, 19);
		contentPane.add(txtNomeMae);
		txtNomeMae.setColumns(10);
		
		txtNomePai = new JTextField();
		txtNomePai.setBounds(98, 223, 148, 19);
		contentPane.add(txtNomePai);
		txtNomePai.setColumns(10);
		
		btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(290, 218, 109, 21);
		contentPane.add(btnNewButton);
		
		btnHome = new JButton("HOME");
		btnHome.addActionListener(this);
		btnHome.setBounds(314, 170, 85, 21);
		contentPane.add(btnHome);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Paciente paciente = new Paciente();
		paciente.setNome(txtNome.getText());
		paciente.setCpf(txtCpf.getText());
		paciente.setRg(txtRg.getText());
		paciente.setTelefone(txtTelefone.getText());
		paciente.setEmail(txtEmail.getText());
		paciente.setRua(txtRua.getText());
		paciente.setComplemento(txtComplemento.getText());
		paciente.setNum(txtNumero.getText());
		paciente.setBairro(txtBairro.getText());
		paciente.setCep(txtCep.getText());
		paciente.setCidade(txtCidade.getText());
		paciente.setUf(boxUf.getSelectedItem().toString());
		paciente.setNascimento(txtNascimento.getText());
		paciente.setSexo(boxSexo.getSelectedItem().toString());
		paciente.setEstadoCivil(txtEstadoCivil.getText());
		paciente.setNomePai(txtNomePai.getText());
		paciente.setNomeMae(txtNomeMae.getText());
		
		if (armazenarPaciente.salvar(paciente)) {
			JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso");
			txtNome.setText("");
			txtCpf.setText("");
			txtRg.setText("");
			txtEmail.setText("");
			txtTelefone.setText("");
			txtRua.setText("");
			txtComplemento.setText("");
			txtNumero.setText("");
			txtBairro.setText("");
			txtCep.setText("");
			txtCidade.setText("");
			txtNascimento.setText("");
			txtEstadoCivil.setText("");
			txtNomePai.setText("");
			txtNomeMae.setText("");
			
			txtNome.requestFocus();
		} else {
			JOptionPane.showMessageDialog(null,"Não foi possível realizar o cadastro");
		}
	
	btnHome.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Home h = new Home();
			Cadastro.this.dispose();
			h.setVisible(true);
		}
	});
	}
}
