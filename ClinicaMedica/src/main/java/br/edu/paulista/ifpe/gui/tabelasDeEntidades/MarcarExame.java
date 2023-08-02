package br.edu.paulista.ifpe.gui.tabelasDeEntidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.edu.paulista.ifpe.data.ConnectionBD;
import br.edu.paulista.ifpe.gui.PainelDegrade;

@SuppressWarnings("serial")
public class MarcarExame extends JDialog {

	private PainelDegrade contentPane = new PainelDegrade();
	private JButton btnCadastro = new JButton();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField txtIdPaciente;
	private JTextField txtIdMedico;
	private JTextField txtIdExame;
	private JTextField txtData;
	private JTextField txtHora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MarcarExame dialog = new MarcarExame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "<html>Erro ao abrir a pagina de marcação</html>", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Create the dialog.
	 */
	public MarcarExame() {
		super();
		setModal(true);
		setTitle("Marcar exame");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new PainelDegrade();
		contentPane.setColors(new Color(0, 128, 255), new Color(50, 205, 50));
        contentPane.repaint();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setBounds(160, 200, 120, 30);
		btnCadastro.setToolTipText("Finalizar cadastro");
		contentPane.add(btnCadastro);
		
		lblNewLabel = new JLabel("Id do paciente");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 10, 108, 13);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Id do médico");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 43, 91, 13);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Id do exame");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 76, 91, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Data");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 109, 91, 13);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Hora");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 142, 91, 13);
		contentPane.add(lblNewLabel_4);
		
		txtIdPaciente = new JTextField();
		txtIdPaciente.setBounds(137, 8, 96, 19);
		contentPane.add(txtIdPaciente);
		txtIdPaciente.setColumns(10);
		
		txtIdMedico = new JTextField();
		txtIdMedico.setBounds(137, 43, 96, 19);
		contentPane.add(txtIdMedico);
		txtIdMedico.setColumns(10);
		
		txtIdExame = new JTextField();
		txtIdExame.setBounds(137, 74, 96, 19);
		contentPane.add(txtIdExame);
		txtIdExame.setColumns(10);
		
		txtData = new JTextField();
		txtData.setBounds(73, 107, 96, 19);
		contentPane.add(txtData);
		txtData.setColumns(10);
		
		txtHora = new JTextField();
		txtHora.setBounds(73, 140, 96, 19);
		contentPane.add(txtHora);
		txtHora.setColumns(10);
		btnCadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final int idPaciente = Integer.parseInt(txtIdPaciente.getText());
				final int idMedico = Integer.parseInt(txtIdMedico.getText());
				final int idExame = Integer.parseInt(txtIdExame.getText());
				final String data = txtData.getText();
				final String  hora = txtHora.getText();
				
				if (txtIdPaciente.getText().isEmpty()) {
					
				} else if (txtIdMedico.getText().isEmpty()) {
					
				} else if (txtIdExame.getText().isEmpty()){
					
				} else if (data.isEmpty()){
					
				} else if (hora.isEmpty()){
					
				} else {
					ConnectionBD connectionBD = new ConnectionBD();
		            Connection connection = connectionBD.abrir();

		            if (connection != null) {
		                try {
		                    String insertQuery = "INSERT INTO exame_marcado (id_paciente, id_medico, id_exame, data, hora) VALUES (?, ?, ?, ?, ?)";

		                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
		                    preparedStatement.setInt(1, idPaciente);
		                    preparedStatement.setInt(2, idMedico);
		                    preparedStatement.setInt(3, idExame);
		                    preparedStatement.setString(4, data);
		                    preparedStatement.setString(5, hora);

		                    int linhasInseridas = preparedStatement.executeUpdate();

		                    if (linhasInseridas > 0) {
		                        JOptionPane.showMessageDialog(null, "Exame marcado com sucesso.");
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Falha ao marcar o exame.");
		                    }

		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(null, "Os IDs devem ser números inteiros.");
		                } catch (SQLException ex) {
		                    JOptionPane.showMessageDialog(null, "Erro ao marcar o exame: ");
		                } finally {
		                    connectionBD.fechar();
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.");
		            }
					dispose();
				}
				
			}
		});
	}
	

}
