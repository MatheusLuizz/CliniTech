package br.edu.paulista.ifpe.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.paulista.ifpe.core.LimiteCaracteres;
import br.edu.paulista.ifpe.core.util.cadastroMedicos.CadastroMedicoListener;
import br.edu.paulista.ifpe.core.util.cadastroMedicos.InserirMedico;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.CampoTextoRedondo;

@SuppressWarnings("serial")
public class CadastroMedico extends JDialog {

	private PainelDegrade contentPane;
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
	private CadastroMedicoListener listener;
	private JLabel lblNewLabel_6;
	private JButton btnUpload;
	private byte[] fileData;
	private JLabel lblNewLabel_7;
	private JTextField txtSenha;
	
	public void setListener(CadastroMedicoListener listener) {
        this.listener = listener;
    }

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
		contentPane = new PainelDegrade();
		contentPane.setColors(new Color(0, 128, 255), new Color(50, 205, 50));
        contentPane.repaint();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("CRM");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 49, 44, 20);
		contentPane.add(lblNewLabel);

		try {
			MaskFormatter mascaraCrm = new MaskFormatter("######CRM/??");
			txtCrm = new CampoTextoFormatadoRedondo(mascaraCrm, 10);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do CRM", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtCrm.setBounds(64, 51, 96, 19);
		contentPane.add(txtCrm);
		txtCrm.setColumns(10);

		lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 20, 44, 20);
		contentPane.add(lblNewLabel_1);

		txtNome = new CampoTextoRedondo(10);
		txtNome.setBounds(64, 22, 96, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 78, 35, 20);
		contentPane.add(lblNewLabel_2);

		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			txtCpf = new CampoTextoFormatadoRedondo(mascaraCpf, 10);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do CPF", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtCpf.setBounds(64, 80, 96, 19);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);

		lblNewLabel_3 = new JLabel("RG");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3.setBounds(9, 111, 45, 13);
		contentPane.add(lblNewLabel_3);

		try {
			MaskFormatter mask = new MaskFormatter("########"); // Define a máscara para 7 ou 8 dígitos numéricos
			txtRg = new CampoTextoFormatadoRedondo(mask, 10);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do RG", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtRg.setBounds(64, 109, 96, 19);
		contentPane.add(txtRg);
		txtRg.setColumns(10);

		lblNewLabel_4 = new JLabel("Telefone");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 138, 64, 13);
		contentPane.add(lblNewLabel_4);

		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##) #####-####");
			txtTelefone = new CampoTextoFormatadoRedondo(mascaraTelefone, 10);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação do celular", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		txtTelefone.setBounds(82, 136, 96, 19);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		lblNewLabel_5 = new JLabel("Especialidade");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_5.setBounds(10, 165, 111, 13);
		contentPane.add(lblNewLabel_5);

		txtEspecialidade = new CampoTextoRedondo(10);
		txtEspecialidade.setBounds(137, 163, 96, 19);
		contentPane.add(txtEspecialidade);
		txtEspecialidade.setColumns(10);

		// Definindo limite de caracteres nos campos de texto
		LimiteCaracteres limiteCaracteres = new LimiteCaracteres();
		limiteCaracteres.adicionarLimiteCaracteres(txtNome, 100);
		limiteCaracteres.adicionarLimiteCaracteres(txtEspecialidade, 100);

		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setToolTipText("Finalizar cadastro");
		btnCadastro.setFont(new Font("Arial", Font.BOLD, 16));
		btnCadastro.setBounds(165, 223, 120, 30);
		contentPane.add(btnCadastro);
		
		lblNewLabel_6 = new JLabel("Assinatura Eletrênica");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_6.setBounds(6, 196, 154, 13);
		contentPane.add(lblNewLabel_6);
		
		btnUpload = new JButton("Upload");
		btnUpload.setFont(new Font("Arial", Font.BOLD, 14));
		btnUpload.setBounds(200, 192, 85, 21);
		btnUpload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(CadastroMedico.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    fileData = readFileContent(selectedFile);
                }
            }
        });
		
		contentPane.add(btnUpload);
		
		lblNewLabel_7 = new JLabel("senha");
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_7.setBounds(188, 25, 51, 13);
		contentPane.add(lblNewLabel_7);
		
		txtSenha = new CampoTextoRedondo(10);
		txtSenha.setBounds(249, 22, 96, 19);
		contentPane.add(txtSenha);
		txtSenha.setColumns(10);

		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final String crm = txtCrm.getText();
				final String nome = txtNome.getText();
				final String cpf = txtCpf.getText();
				final String rg = txtRg.getText();
				final String telefone = txtTelefone.getText();
				final String especialidade = txtEspecialidade.getText();
				final String senha = txtSenha.getText();
				
				if (fileData == null) {
                    JOptionPane.showMessageDialog(null, "Selecione um arquivo antes de cadastrar.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

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
				} else if (senha.isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo Senha é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					InserirMedico i = new InserirMedico();
					i.inserirDados(crm, nome, cpf, rg, telefone, especialidade, fileData, senha);
					if (listener != null) {
		                listener.medicoCadastrado();
		            }
					dispose();
				}
			}
		});
	}
	private byte[] readFileContent(File file) {
	    try (FileInputStream fis = new FileInputStream(file);
	         ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

	        byte[] buffer = new byte[1024];
	        int bytesRead;
	        while ((bytesRead = fis.read(buffer)) != -1) {
	            bos.write(buffer, 0, bytesRead);
	        }
	        return bos.toByteArray();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}