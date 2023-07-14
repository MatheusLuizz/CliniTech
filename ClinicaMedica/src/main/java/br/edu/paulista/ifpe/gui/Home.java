package br.edu.paulista.ifpe.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.paulista.ifpe.model.cardTables.TelaMedico;
import br.edu.paulista.ifpe.model.cardTables.TelaPaciente;

@SuppressWarnings("serial")
public class Home extends JFrame {

	private JPanel contentPane;
	private JPanel painelAtalhos;
	private JButton btnPaciente;
	private JButton btnMedico;
	private JPanel painelBusca;
	private JPanel painelPaciente;
	private final JTextField txtBusca = new JTextField();
	private JLabel lblBuscar;
	private List<JTable> tabelasExibidas;
	private JButton btnRemedio;
	private JButton btnExame;

	private void aplicarFiltroBusca(JTable tabela, TableModel modeloTabela, String textoBusca) {
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabela);
	    tabela.setRowSorter(sorter);

	    if (textoBusca.isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "O campo de busca está vazio");
	        sorter.setRowFilter(null);
	    } else {
	        String regex = "(?i)" + Pattern.quote(textoBusca);
	        sorter.setRowFilter(RowFilter.regexFilter(regex, 0)); // Assumindo que o nome está na primeira coluna (índice 0)
	    }
	}
	
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
		tabelasExibidas = new ArrayList<JTable>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		painelAtalhos = new JPanel();
		painelAtalhos.setBounds(0, 0, 91, 810);
		painelAtalhos.setBackground(new Color(13, 73, 151));
		
		btnPaciente = new JButton("");
		btnPaciente.setToolTipText("Exibir os pacientes cadastrados");
		btnPaciente.setForeground(new Color(13, 73, 151));
		btnPaciente.setBackground(new Color(13, 73, 151));
		btnPaciente.setBorderPainted(false);
		btnPaciente.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconePaciente.png")));
		
		btnMedico = new JButton("");
		btnMedico.setForeground(new Color(13, 73, 151));
		btnMedico.setBackground(new Color(13, 73, 151));
		btnMedico.setBorderPainted(false);
		btnMedico.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeMedico.png")));
		
		btnRemedio = new JButton("");
		btnRemedio.setForeground(new Color(13, 73, 151));
		btnRemedio.setBackground(new Color(13, 73, 151));
		btnRemedio.setBorderPainted(false);
		btnRemedio.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeRemedio.png")));
		
		btnExame = new JButton("");
		btnExame.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeExame.png")));
		btnExame.setForeground(new Color(13, 73, 151));
		btnExame.setBackground(new Color(13, 73, 151));
		btnExame.setBorderPainted(false);
		GroupLayout gl_painelAtalhos = new GroupLayout(painelAtalhos);
		gl_painelAtalhos.setHorizontalGroup(
			gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelAtalhos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExame, GroupLayout.PREFERRED_SIZE, 71, Short.MAX_VALUE)
						.addGroup(gl_painelAtalhos.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnRemedio, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(btnMedico, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(btnPaciente, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_painelAtalhos.setVerticalGroup(
			gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelAtalhos.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPaciente, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnMedico, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRemedio)
					.addGap(18)
					.addComponent(btnExame, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(502, Short.MAX_VALUE))
		);
		painelAtalhos.setLayout(gl_painelAtalhos);
		contentPane.setLayout(null);
		
		painelBusca = new JPanel();
		painelBusca.setBounds(91, 0, 1473, 65);
		painelBusca.setBackground(new Color(211, 211, 211));
		painelBusca.setLayout(null);
		contentPane.add(painelBusca);
		txtBusca.setToolTipText("Digite o nome e pressione a tecla \"ENTER\"");
		txtBusca.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBusca.setBounds(681, 22, 322, 33);
		painelBusca.add(txtBusca);
		txtBusca.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String textoBusca = txtBusca.getText();
	            for (JTable tabela : tabelasExibidas) {
	                TableModel modelo = tabela.getModel();
	                aplicarFiltroBusca(tabela, modelo, textoBusca);
	            }
	        }
	    });
		txtBusca.setColumns(10);
		
		lblBuscar = new JLabel("Buscar");
		lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		lblBuscar.setBounds(624, 26, 47, 23);
		painelBusca.add(lblBuscar);
		contentPane.add(painelAtalhos);
		lblBuscar.setVisible(false);
		txtBusca.setVisible(false);
		
		painelPaciente = new JPanel();
		painelPaciente.setBounds(91, 67, 1459, 743);
		contentPane.add(painelPaciente);
		painelPaciente.setLayout(new CardLayout(0, 0));
	
	btnPaciente.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			TelaPaciente p = new TelaPaciente();
            p.atualizar();
            lblBuscar.setVisible(true);
    		txtBusca.setVisible(true);
            painelPaciente.removeAll();
            painelPaciente.setLayout(new CardLayout(0, 0));
            painelPaciente.add(p, BorderLayout.CENTER);
            painelPaciente.revalidate();
            painelPaciente.repaint();

            tabelasExibidas = new ArrayList<JTable>();
            tabelasExibidas.add(p.getTabela());
	    }
	});
	btnMedico.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			TelaMedico m = new TelaMedico();
            m.atualizar();
            lblBuscar.setVisible(true);
    		txtBusca.setVisible(true);
            painelPaciente.removeAll();
            painelPaciente.setLayout(new CardLayout(0, 0));
            painelPaciente.add(m, BorderLayout.CENTER);
            painelPaciente.revalidate();
            painelPaciente.repaint();

            tabelasExibidas = new ArrayList<JTable>();
            tabelasExibidas.add(m.getTabela());
	    }
	});
	btnRemedio.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Remedios r = new Remedios();
            r.atualizar();
            lblBuscar.setVisible(true);
    		txtBusca.setVisible(true);
            painelPaciente.removeAll();
            painelPaciente.setLayout(new CardLayout(0, 0));
            painelPaciente.add(r, BorderLayout.CENTER);
            painelPaciente.revalidate();
            painelPaciente.repaint();

            tabelasExibidas = new ArrayList<JTable>();
            tabelasExibidas.add(r.getTabela());
	    }
	});
	btnExame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Exames ex = new Exames();
            ex.atualizar();
            lblBuscar.setVisible(true);
    		txtBusca.setVisible(true);
            painelPaciente.removeAll();
            painelPaciente.setLayout(new CardLayout(0, 0));
            painelPaciente.add(ex, BorderLayout.CENTER);
            painelPaciente.revalidate();
            painelPaciente.repaint();

            tabelasExibidas = new ArrayList<JTable>();
            tabelasExibidas.add(ex.getTabela());
	    }
	});
	}
}