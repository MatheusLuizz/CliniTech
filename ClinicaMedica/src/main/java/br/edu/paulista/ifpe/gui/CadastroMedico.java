package br.edu.paulista.ifpe.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.edu.paulista.ifpe.data.ConnectionBD;

@SuppressWarnings("serial")
public class CadastroMedico extends JFrame {

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
	
	private JButton btnHome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMedico frame = new CadastroMedico();
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
	public CadastroMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("CRM");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(9, 9, 44, 20);
		contentPane.add(lblNewLabel);
		
		txtCrm = new JTextField();
		txtCrm.setBounds(62, 9, 96, 19);
		contentPane.add(txtCrm);
		txtCrm.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(9, 41, 44, 20);
		contentPane.add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setBounds(62, 41, 96, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setBounds(9, 74, 35, 20);
		contentPane.add(lblNewLabel_2);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(62, 74, 96, 19);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
		
		lblNewLabel_3 = new JLabel("RG");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3.setBounds(9, 107, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		txtRg = new JTextField();
		txtRg.setBounds(62, 107, 96, 19);
		contentPane.add(txtRg);
		txtRg.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Telefone");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_4.setBounds(9, 140, 64, 13);
		contentPane.add(lblNewLabel_4);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(85, 140, 96, 19);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Especialidade");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_5.setBounds(9, 173, 111, 13);
		contentPane.add(lblNewLabel_5);
		
		txtEspecialidade = new JTextField();
		txtEspecialidade.setBounds(120, 173, 96, 19);
		contentPane.add(txtEspecialidade);
		txtEspecialidade.setColumns(10);
		
		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setToolTipText("Finalizar cadastro");
		btnCadastro.setFont(new Font("Arial", Font.BOLD, 16));
		btnCadastro.setBounds(306, 221, 112, 30);
		contentPane.add(btnCadastro);
		
		btnCadastro.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				try {
					ConnectionBD conn = new ConnectionBD();
					PreparedStatement st;
					String query = "INSERT INTO medico (crm, nome, cpf, rg, telefone, especialidade) VALUES (?,?,?,?,?,?);";
					st = conn.abrir().prepareStatement(query);
					st.setString(1, txtCrm.getText());
					st.setString(2, txtNome.getText());
					st.setString(3, txtCpf.getText());
					st.setString(4, txtRg.getText());
					st.setString(5, txtTelefone.getText());
					st.setString(6, txtEspecialidade.getText());
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
					txtCrm.setText("");
					txtNome.setText("");
					txtCpf.setText("");
					txtRg.setText("");
					txtTelefone.setText("");
					txtEspecialidade.setText("");
					txtCrm.requestFocus();
					st.close();
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}
		});
		btnHome = new JButton("Home");
		btnHome.setFont(new Font("Arial", Font.BOLD, 12));
		btnHome.setBounds(306, 10, 96, 39);
		contentPane.add(btnHome);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h = new Home();
				CadastroMedico.this.dispose();
				h.setVisible(true);
			}
		});
	}
}

