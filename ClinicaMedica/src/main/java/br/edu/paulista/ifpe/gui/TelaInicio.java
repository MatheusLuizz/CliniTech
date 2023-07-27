package br.edu.paulista.ifpe.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;



@SuppressWarnings("serial")
public class TelaInicio extends JPanel {
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JPanel panel_2;
	private JLabel lblNewLabel_2;
	private JTextField textField_1;
	private JScrollPane scrollPane_2;
	private JTable table_1;
	private JPanel panel_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicio tela = new TelaInicio();
					tela.setVisible(true);
					//frame.atualizar();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Não foi possível exibir a tela inicial", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	public TelaInicio() {
        setLayout(null);

        panel_1 = new JPanel();
        panel_1.setBounds(597, 455, 555, 240);
        panel_1.setLayout(null);

        lblNewLabel_1 = new JLabel("Pacientes Agendados");
        lblNewLabel_1.setBounds(0, 6, 194, 24);
        panel_1.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 17));

        textField = new JTextField();
        textField.setBounds(340, 5, 194, 32);
        panel_1.add(textField);
        textField.setColumns(10);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 66, 535, 174);
        panel_1.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        	},
        	new String[] {
        		"Nome", "Atendimento", "Hora"
        	}
        ));
        scrollPane.setViewportView(table);

        add(panel_1);

        panel = new PainelDegrade();
        panel.setBounds(0, 0, 1179, 50);
        add(panel);
        panel.setLayout(null);

        lblNewLabel = new JLabel("Dashboard");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setBounds(10, 10, 118, 30);
        panel.add(lblNewLabel);
        
        panel_2 = new JPanel();
        panel_2.setBounds(10, 455, 555, 258);
        add(panel_2);
        panel_2.setLayout(null);
        
        lblNewLabel_2 = new JLabel("Proximos atendimentos");
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 17));
        lblNewLabel_2.setBounds(0, 1, 192, 41);
        panel_2.add(lblNewLabel_2);
        
        textField_1 = new JTextField();
        textField_1.setBounds(349, 10, 196, 32);
        panel_2.add(textField_1);
        textField_1.setColumns(10);
        
        scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(10, 52, 535, 206);
        panel_2.add(scrollPane_2);
        
        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        	},
        	new String[] {
        		"Nome", "Atendimento", "Hora"
        	}
        ));
        scrollPane_2.setViewportView(table_1);
        // Definir o tamanho preferido da tabela_1 para que ela preencha o espaço disponível no scrollPane_2
        table_1.setPreferredSize(new Dimension(scrollPane_2.getWidth(), table_1.getPreferredSize().height));
        
        panel_3 = new PainelRedondo();
        panel_3.setBounds(10, 60, 1168, 385);
        add(panel_3);
    }
}