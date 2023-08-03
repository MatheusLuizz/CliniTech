package br.edu.paulista.ifpe.gui.dialogos;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.edu.paulista.ifpe.core.GeradorReceitaMedica;
import br.edu.paulista.ifpe.core.LimiteCaracteres;
import br.edu.paulista.ifpe.gui.componentesCustomizados.CampoTextoRedondo;
import br.edu.paulista.ifpe.gui.componentesCustomizados.ComboBoxCustomizada;
import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelDegrade;

@SuppressWarnings("serial")
public class TelaReceita extends JDialog {

	private PainelDegrade contentPane;
	private JLabel lblNewLabel;
	private JTextField txtPaciente;
	private JLabel r1;
	private JTextField txtRemedio1;
	private JLabel r2;
	private JTextField txtRemedio2;
	private JLabel r3;
	private JTextField txtRemedio3;
	private JLabel r4;
	private JTextField txtRemedio4;
	private JLabel r5;
	private JTextField txtRemedio5;
	@SuppressWarnings("rawtypes")
	private JComboBox boxQuantidade;
	private JLabel lblQuantidade;
	private JLabel dosagem1;
	private JLabel dosagem2;
	private JLabel dosagem3;
	private JLabel dosagem4;
	private JLabel dosagem5;
	private JTextField txtDosagem1;
	private JTextField txtDosagem2;
	private JTextField txtDosagem3;
	private JTextField txtDosagem4;
	private JTextField txtDosagem5;
	private JButton btnGerar;
	@SuppressWarnings("unused")
	private int idMedico;
	private GeradorReceitaMedica geradorReceita = new GeradorReceitaMedica();
	private final JPanel panel = new JPanel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaReceita frame = new TelaReceita(124);
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao tentar gerar receita");
				}
			}
		});
	}
	
	public Component getComponentePeloNome(JPanel panel, String name) {
	    for (Component comp : panel.getComponents()) {
	        if (comp.getName() != null && comp.getName().equals(name)) {
	            return comp;
	        }
	    }
	    return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaReceita(int idMedico) {
		super();
		this.idMedico = idMedico;
		setModal(true);
		setType(Type.UTILITY);
		setTitle("Gerar receita");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 504, 300);
		contentPane = new PainelDegrade();
		contentPane.setColors(new Color(0, 128, 255), new Color(50, 205, 50));
        contentPane.repaint();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Nome do paciente");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 42, 102, 13);
		contentPane.add(lblNewLabel);
		
		txtPaciente = new CampoTextoRedondo(10);
		txtPaciente.setFont(new Font("Arial", Font.BOLD, 12));
		txtPaciente.setBounds(122, 38, 114, 19);
		contentPane.add(txtPaciente);
		txtPaciente.setColumns(10);
		
		lblQuantidade = new JLabel("Qt. medicacoes");
		lblQuantidade.setFont(new Font("Arial", Font.BOLD, 12));
		lblQuantidade.setBounds(21, 82, 169, 13);
		contentPane.add(lblQuantidade);
		
		boxQuantidade = new ComboBoxCustomizada();
		boxQuantidade.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		boxQuantidade.setSelectedIndex(0);
		boxQuantidade.setFont(new Font("Arial", Font.BOLD, 12));
		boxQuantidade.setBounds(111, 78, 127, 21);
		contentPane.add(boxQuantidade);
		boxQuantidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valorSelecionadoStr = (String) boxQuantidade.getSelectedItem();
                int num = Integer.parseInt(valorSelecionadoStr);
                for (int i = 1; i <= 5; i++) {
                    setCamposVisiveis(i, false);
                }

                for (int i = 1; i <= num; i++) {
                    setCamposVisiveis(i, true);
                }    
        }
    });
		
		btnGerar = new JButton("Gerar receita");
		btnGerar.setFont(new Font("Arial", Font.BOLD, 12));
		btnGerar.setBounds(305, 26, 120, 21);
		contentPane.add(btnGerar);
		panel.setBounds(10, 92, 468, 161);
		panel.setOpaque(false);
		contentPane.add(panel);
		panel.setLayout(null);
		
		r1 = new JLabel("Remedio 1");
		r1.setBounds(10, 39, 68, 13);
		panel.add(r1);
		r1.setFont(new Font("Arial", Font.BOLD, 12));
		
		r2 = new JLabel("Remedio 2");
		r2.setBounds(10, 62, 68, 13);
		panel.add(r2);
		r2.setFont(new Font("Arial", Font.BOLD, 12));
		
		r3 = new JLabel("Remedio 3");
		r3.setBounds(10, 85, 68, 13);
		panel.add(r3);
		r3.setFont(new Font("Arial", Font.BOLD, 12));
		
		r4 = new JLabel("Remedio 4");
		r4.setBounds(10, 108, 68, 13);
		panel.add(r4);
		r4.setFont(new Font("Arial", Font.BOLD, 12));
		
		r5 = new JLabel("Remedio 4");
		r5.setBounds(10, 131, 68, 13);
		panel.add(r5);
		r5.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtRemedio1 = new CampoTextoRedondo(10);
		txtRemedio1.setBounds(83, 39, 114, 19);
		panel.add(txtRemedio1);
		txtRemedio1.setFont(new Font("Arial", Font.BOLD, 12));
		txtRemedio1.setColumns(10);
		txtRemedio1.setName("txtRemedio1");
		
		txtRemedio2 = new CampoTextoRedondo(10);
		txtRemedio2.setBounds(83, 62, 114, 19);
		panel.add(txtRemedio2);
		txtRemedio2.setFont(new Font("Arial", Font.BOLD, 12));
		txtRemedio2.setVisible(false);
		txtRemedio2.setColumns(10);
		txtRemedio2.setName("txtRemedio2");

		
		txtRemedio3 = new CampoTextoRedondo(10);
		txtRemedio3.setBounds(83, 85, 114, 19);
		panel.add(txtRemedio3);
		txtRemedio3.setFont(new Font("Arial", Font.BOLD, 12));
		txtRemedio3.setVisible(false);
		txtRemedio3.setColumns(10);
		txtRemedio3.setName("txtRemedio3");

		
		txtRemedio4 = new CampoTextoRedondo(10);
		txtRemedio4.setBounds(83, 108, 114, 19);
		panel.add(txtRemedio4);
		txtRemedio4.setFont(new Font("Arial", Font.BOLD, 12));
		txtRemedio4.setVisible(false);
		txtRemedio4.setColumns(10);
		txtRemedio4.setName("txtRemedio4");

		
		txtRemedio5 = new CampoTextoRedondo(10);
		txtRemedio5.setBounds(83, 131, 114, 19);
		panel.add(txtRemedio5);
		txtRemedio5.setFont(new Font("Arial", Font.BOLD, 12));
		txtRemedio5.setVisible(false);
		txtRemedio5.setColumns(10);
		txtRemedio5.setName("txtRemedio5");

		
		dosagem1 = new JLabel("Dosagem");
		dosagem1.setBounds(219, 39, 63, 13);
		panel.add(dosagem1);
		dosagem1.setFont(new Font("Arial", Font.BOLD, 12));
		
		dosagem2 = new JLabel("Dosagem");
		dosagem2.setBounds(219, 62, 63, 13);
		panel.add(dosagem2);
		dosagem2.setFont(new Font("Arial", Font.BOLD, 12));
		
		dosagem3 = new JLabel("Dosagem");
		dosagem3.setBounds(219, 85, 63, 13);
		panel.add(dosagem3);
		dosagem3.setFont(new Font("Arial", Font.BOLD, 12));
		
		dosagem4 = new JLabel("Dosagem");
		dosagem4.setBounds(219, 108, 63, 13);
		panel.add(dosagem4);
		dosagem4.setFont(new Font("Arial", Font.BOLD, 12));
		
		dosagem5 = new JLabel("Dosagem");
		dosagem5.setBounds(219, 131, 63, 13);
		panel.add(dosagem5);
		dosagem5.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtDosagem1 = new CampoTextoRedondo(10);
		txtDosagem1.setBounds(292, 36, 96, 19);
		panel.add(txtDosagem1);
		txtDosagem1.setColumns(10);
		txtDosagem1.setName("txtDosagem1");

		txtDosagem2 = new CampoTextoRedondo(10);
		txtDosagem2.setBounds(292, 59, 96, 19);
		panel.add(txtDosagem2);
		txtDosagem2.setVisible(false);
		txtDosagem2.setColumns(10);
		txtDosagem2.setName("txtDosagem2");
		
		txtDosagem3 = new CampoTextoRedondo(10);
		txtDosagem3.setBounds(292, 82, 96, 19);
		panel.add(txtDosagem3);
		txtDosagem3.setVisible(false);
		txtDosagem3.setColumns(10);
		txtDosagem3.setName("txtDosagem3");
		
		txtDosagem4 = new CampoTextoRedondo(10);
		txtDosagem4.setBounds(292, 105, 96, 19);
		panel.add(txtDosagem4);
		txtDosagem4.setVisible(false);
		txtDosagem4.setColumns(10);
		txtDosagem4.setName("txtDosagem4");
		
		txtDosagem5 = new CampoTextoRedondo(10);
		txtDosagem5.setBounds(292, 128, 96, 19);
		panel.add(txtDosagem5);
		txtDosagem5.setVisible(false);
		txtDosagem5.setColumns(10);
		txtDosagem5.setName("txtDosagem5");

		dosagem5.setVisible(false);
		dosagem4.setVisible(false);
		dosagem3.setVisible(false);
		dosagem2.setVisible(false);
		r5.setVisible(false);
		r4.setVisible(false);
		r3.setVisible(false);
		r2.setVisible(false);
		
		LimiteCaracteres limiteCaracteres = new LimiteCaracteres();
		limiteCaracteres.adicionarLimiteCaracteres(txtPaciente, 100);
		limiteCaracteres.adicionarLimiteCaracteres(txtDosagem1, 50);
		limiteCaracteres.adicionarLimiteCaracteres(txtDosagem2, 50);
		limiteCaracteres.adicionarLimiteCaracteres(txtDosagem3, 50);
		limiteCaracteres.adicionarLimiteCaracteres(txtDosagem4, 50);
		limiteCaracteres.adicionarLimiteCaracteres(txtDosagem5, 50);
		limiteCaracteres.adicionarLimiteCaracteres(txtRemedio1, 50);
		limiteCaracteres.adicionarLimiteCaracteres(txtRemedio2, 50);
		limiteCaracteres.adicionarLimiteCaracteres(txtRemedio3, 50);
		limiteCaracteres.adicionarLimiteCaracteres(txtRemedio4, 50);
		limiteCaracteres.adicionarLimiteCaracteres(txtRemedio5, 50);
		
		btnGerar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Obtém a quantidade selecionada no JComboBox
		        String valorSelecionadoStr = (String) boxQuantidade.getSelectedItem();
		        String paciente = txtPaciente.getText();
		        int num = Integer.parseInt(valorSelecionadoStr);
		        // Crie os arrays para armazenar os nomes e dosagens dos remédios
		        String[] remedios = new String[num];
		        String[] dosagens = new String[num];
		        int index = 0; // Variável para controlar o índice no array de remédios e dosagens

		        for (int i = 1; i <= num; i++) {
		            Component componenteNome = getComponentePeloNome(panel, "txtRemedio" + i);
		            Component componenteDosagem = getComponentePeloNome(panel, "txtDosagem" + i);

		            if (componenteNome instanceof JTextField && componenteDosagem instanceof JTextField) {
		                JTextField campoNome = (JTextField) componenteNome;
		                JTextField campoDosagem = (JTextField) componenteDosagem;

		                if (campoNome.isVisible() && campoDosagem.isVisible() && !campoNome.getText().isEmpty() && !campoDosagem.getText().isEmpty()) {
		                    String nomeRemedio = campoNome.getText();
		                    String dosagemRemedio = campoDosagem.getText();
		                    remedios[index] = nomeRemedio;
		                    dosagens[index] = dosagemRemedio;
		                    index++;
		                }
		            }
		        }

		        geradorReceita.gerarReceitaMedica(idMedico, paciente, remedios, dosagens);
		        if (geradorReceita != null) {
		            JOptionPane.showMessageDialog(null, "Receita gerada com sucesso!");
		        } else {
		            JOptionPane.showMessageDialog(null, "Falha ao gerar a receita médica.");
		        }
		        dispose();
		    }
		});
	}
	private void setCamposVisiveis(int numeroCampo, boolean visivel) {
	    switch (numeroCampo) {
	        case 1:
	            r1.setVisible(visivel);
	            txtRemedio1.setVisible(visivel);
	            dosagem1.setVisible(visivel);
	            txtDosagem1.setVisible(visivel);
	            break;
	        case 2:
	            r2.setVisible(visivel);
	            txtRemedio2.setVisible(visivel);
	            dosagem2.setVisible(visivel);
	            txtDosagem2.setVisible(visivel);
	            break;
	        case 3:
	            r3.setVisible(visivel);
	            txtRemedio3.setVisible(visivel);
	            dosagem3.setVisible(visivel);
	            txtDosagem3.setVisible(visivel);
	            break;
	        case 4:
	            r4.setVisible(visivel);
	            txtRemedio4.setVisible(visivel);
	            dosagem4.setVisible(visivel);
	            txtDosagem4.setVisible(visivel);
	            break;
	        case 5:
	            r5.setVisible(visivel);
	            txtRemedio5.setVisible(visivel);
	            dosagem5.setVisible(visivel);
	            txtDosagem5.setVisible(visivel);
	            break;
	        default:
	            break;
	    }
	}
}
