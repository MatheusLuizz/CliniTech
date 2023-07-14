package br.edu.paulista.ifpe.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.edu.paulista.ifpe.model.entidades.Operador;

@SuppressWarnings("serial")
public class TelaLogin extends JFrame {
	
	Operador operador = new Operador("operador@gmail.com", "operador");
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private JButton btnLogin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLogin() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(252, 45, 97, 37);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(40, 143, 42, 39);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(40, 217, 51, 39);
		contentPane.add(lblNewLabel_2);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		txtEmail.setBounds(112, 148, 189, 25);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(112, 224, 189, 25);
		contentPane.add(txtSenha);
		
		btnLogin = new JButton("Entrar");
		btnLogin.setToolTipText("Realizar login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String senha = String.valueOf(txtSenha.getPassword());
				
				if (email.equals(operador.getEmail()) && senha.equals(operador.getSenha())) {
					Home h = new Home();
					TelaLogin.this.dispose();
					h.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "E-mail ou senha incorretos!");
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(40, 301, 85, 21);
		contentPane.add(btnLogin);
	}
}