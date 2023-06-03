package Telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Home extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel painel;
	private JToggleButton btnPaciente;
	private JToggleButton btnCadastro;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		painel = new JPanel();
		painel.setBounds(0, 10, 539, 76);
		contentPane.add(painel);
		
		btnPaciente = new JToggleButton("");
		btnPaciente.addActionListener(this);
		btnPaciente.setForeground(new Color(255, 255, 255));
		btnPaciente.setBackground(new Color(255, 255, 255));
		btnPaciente.setIcon(new ImageIcon("C:\\Users\\mathe\\Downloads\\paciente_resized.png"));
		
		btnCadastro = new JToggleButton("");
		btnCadastro.setBackground(new Color(255, 255, 255));
		btnCadastro.setForeground(new Color(255, 255, 255));
		btnCadastro.setIcon(new ImageIcon("C:\\Users\\mathe\\Downloads\\iconeCalendario_resized.png"));
		
		lblNewLabel = new JLabel("Pacientes");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblNewLabel_1 = new JLabel("Cadastro");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		GroupLayout gl_painel = new GroupLayout(painel);
		gl_painel.setHorizontalGroup(
			gl_painel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_painel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnPaciente, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_painel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCadastro)
						.addGroup(gl_painel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(353, Short.MAX_VALUE))
		);
		gl_painel.setVerticalGroup(
			gl_painel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painel.createSequentialGroup()
					.addGroup(gl_painel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnCadastro, 0, 0, Short.MAX_VALUE)
						.addComponent(btnPaciente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(12))
		);
		painel.setLayout(gl_painel);
	
	btnPaciente.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		Paciente p = new Paciente();
		Home.this.dispose();
		p.setVisible(true);
	}
	});
	btnCadastro.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Cadastro c = new Cadastro();
			Home.this.dispose();
			c.setVisible(true);
		}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
