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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.paulista.ifpe.gui.tabelasDeEntidades.DetalhesPaciente;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.MarcarConsulta;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaConsulta;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaExame;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaMedico;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaPaciente;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaRemedio;
import br.edu.paulista.ifpe.model.temas.Temas;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class Home extends JFrame {

    private JPanel contentPane;
    private PainelDegrade painelAtalhos;
    private JButton btnPaciente;
    private JButton btnMedico;
    private JButton btnConsultas;
    private JButton btnInicio;
    private PainelDegrade painelBusca;
    private JPanel painelPaciente;
    private final JTextField txtBusca = new JTextField();
    private JLabel lblBuscar;
    private List<JTable> tabelasExibidas;
    private JButton btnRemedio;
    private JButton btnExame;
    private JToggleButton btnTema;
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home();
                    frame.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Não foi possível exibir a tela Home", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
    }

    public Home() {
    	setTitle("Tela Home");
        setResizable(false);
        tabelasExibidas = new ArrayList<JTable>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane = new JPanel(new MigLayout("fill", "[15%][grow]", "[65!][grow]"));
        setContentPane(contentPane);

        painelAtalhos = new PainelDegrade();
        painelAtalhos.setBounds(0, 0, 91, 865);
        

        btnPaciente = new JButton();
        btnPaciente.setToolTipText("Pacientes");
        btnPaciente.setForeground(new Color(13, 73, 151));
        btnPaciente.setBorderPainted(false);
        btnPaciente.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconePaciente.png")));

        btnMedico = new JButton();
        btnMedico.setToolTipText("<html>Médicos</html>");
        btnMedico.setForeground(new Color(13, 73, 151));
        btnMedico.setBorderPainted(false);
        btnMedico.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeMedico.png")));

        btnRemedio = new JButton();
        btnRemedio.setToolTipText("<html>Remédios</html>");
        btnRemedio.setForeground(new Color(13, 73, 151));
        btnRemedio.setBorderPainted(false);
        btnRemedio.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeRemedio.png")));

        btnExame = new JButton();
        btnExame.setToolTipText("Exames");
        btnExame.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeExame.png")));
        btnExame.setForeground(new Color(13, 73, 151));;
        btnExame.setBorderPainted(false);
        
        btnInicio = new JButton();
        btnInicio.setToolTipText("Inicio");
        btnInicio.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeMenu.png")));
        btnInicio.setForeground(new Color(13, 73, 151));
        btnInicio.setBackground(getBackground());
        btnInicio.setBorderPainted(false);
        
        btnConsultas = new JButton();
        btnConsultas.setToolTipText("Consultas");
        btnConsultas.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeConsulta.png")));
        btnConsultas.setForeground(new Color(13,73,151));
        btnConsultas.setBackground(getBackground());
        btnConsultas.setBorderPainted(false);
        
        contentPane.setLayout(null);
        GroupLayout gl_painelAtalhos = new GroupLayout(painelAtalhos);
        gl_painelAtalhos.setHorizontalGroup(
        	gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_painelAtalhos.createSequentialGroup()
        			.addGroup(gl_painelAtalhos.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(btnExame, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
        				.addComponent(btnRemedio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnMedico, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnPaciente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnInicio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnConsultas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addContainerGap(20, Short.MAX_VALUE))
        );
        gl_painelAtalhos.setVerticalGroup(
        	gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_painelAtalhos.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(btnInicio, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnPaciente, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnMedico, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnRemedio, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnExame, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
        			.addComponent(btnConsultas, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addContainerGap(569, Short.MAX_VALUE))
        );
        painelAtalhos.setLayout(gl_painelAtalhos);

        painelBusca = new PainelDegrade();
        painelBusca.setBounds(91, 0, 1473, 65);
        painelBusca.setColors(Color.GRAY, new Color(220, 220, 220));
        painelBusca.setLayout(null);
        contentPane.add(painelBusca);
        txtBusca.setToolTipText("Filtre pelo nome");
        txtBusca.setFont(new Font("Arial", Font.PLAIN, 15));
        txtBusca.setBounds(681, 22, 322, 33);
        painelBusca.add(txtBusca);
        txtBusca.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                realizarBusca();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                realizarBusca();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                realizarBusca();
            }
        });
        txtBusca.setColumns(10);

        lblBuscar = new JLabel("Buscar");
        lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblBuscar.setBounds(624, 26, 47, 23);
        painelBusca.add(lblBuscar);

        btnTema = new JToggleButton("");
        btnTema.setToolTipText("Tema escuro");
        btnTema.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeSol.png")));
        btnTema.setBounds(10, 10, 32, 33);
        painelBusca.add(btnTema);
        contentPane.add(painelAtalhos);
        lblBuscar.setVisible(false);
        txtBusca.setVisible(false);

        painelPaciente = new JPanel();
        painelPaciente.setBackground(new Color(245, 245, 245));
        painelPaciente.setBounds(91, 67, 1459, 743);
        contentPane.add(painelPaciente);
        painelPaciente.setLayout(new CardLayout(0, 0));
        
        txtBusca.setVisible(false);
		lblBuscar.setVisible(false);
		TelaInicio i = new TelaInicio();
		//i.atualizar();
		painelPaciente.removeAll();
		painelPaciente.setLayout(new CardLayout(0, 0));
		painelPaciente.add(i, BorderLayout.CENTER);
		painelPaciente.revalidate();
		painelPaciente.repaint();
        
        btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusca.setVisible(false);
				lblBuscar.setVisible(false);
				TelaInicio i = new TelaInicio();
				//i.atualizar();
				painelPaciente.removeAll();
				painelPaciente.setLayout(new CardLayout(0, 0));
				painelPaciente.add(i, BorderLayout.CENTER);
				painelPaciente.revalidate();
				painelPaciente.repaint();
			}
		});
        
        

        btnPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtBusca.setText("");
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
                Temas.atualizarEstiloTabelas(tabelasExibidas);
                p.setHome(Home.this);
            }
        });
        btnMedico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtBusca.setText("");
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
                Temas.atualizarEstiloTabelas(tabelasExibidas);
            }
        });
        btnRemedio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtBusca.setText("");
                TelaRemedio r = new TelaRemedio();
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
                Temas.atualizarEstiloTabelas(tabelasExibidas);
            }
        });
        btnExame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtBusca.setText("");
                TelaExame ex = new TelaExame();
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
                Temas.atualizarEstiloTabelas(tabelasExibidas);
            }
        });
        btnTema.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (btnTema.isSelected()) {
                	Temas.aplicarTemaEscuro(painelAtalhos, painelBusca, painelPaciente, btnTema, tabelasExibidas);
                    lblBuscar.setForeground(new Color(255, 105, 180));
                    btnTema.setToolTipText("Tema claro");
                } else {
                	Temas.aplicarTemaPadrao(painelAtalhos, painelBusca, painelPaciente, btnTema, tabelasExibidas);
                	lblBuscar.setForeground(Color.BLACK);
                    btnTema.setToolTipText("Tema escuro");
                }
                Temas.atualizarEstiloTabelas(tabelasExibidas);
            }
        });
        btnConsultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int opcao = JOptionPane.showOptionDialog(
                        null,
                        "Escolha uma opção:",
                        "Consultas",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Visualizar todas", "Marcar"},
                        "Visualizar"
                );

                if (opcao == JOptionPane.YES_OPTION) {
                	txtBusca.setText("");
                    TelaConsulta telaConsulta = new TelaConsulta();
                    telaConsulta.atualizar();
                    
                    lblBuscar.setVisible(true);
                    txtBusca.setVisible(true);
                    painelPaciente.removeAll();
                    painelPaciente.setLayout(new CardLayout(0, 0));
                    painelPaciente.add(telaConsulta, BorderLayout.CENTER);
                    painelPaciente.revalidate();
                    painelPaciente.repaint();

                    tabelasExibidas = new ArrayList<JTable>();
                    tabelasExibidas.add(telaConsulta.getTabela());
                    Temas.atualizarEstiloTabelas(tabelasExibidas);
                } else if (opcao == JOptionPane.NO_OPTION) {
                    MarcarConsulta mc = new MarcarConsulta();
                    mc.setLocationRelativeTo(null);
                    mc.setVisible(true);
                }
            }
        });
        
        btnInicio.setOpaque(false);
        btnInicio.setContentAreaFilled(false);
        
        btnPaciente.setOpaque(false);
        btnPaciente.setContentAreaFilled(false);

        btnMedico.setOpaque(false);
        btnMedico.setContentAreaFilled(false);

        btnRemedio.setOpaque(false);
        btnRemedio.setContentAreaFilled(false);

        btnExame.setOpaque(false);
        btnExame.setContentAreaFilled(false);

        btnTema.setOpaque(false);
        btnTema.setContentAreaFilled(false);
        
        btnConsultas.setOpaque(false);
        btnConsultas.setContentAreaFilled(false);
        
        contentPane.add(painelAtalhos, "width 15%, height 100%");
        contentPane.add(painelBusca, "width 85%, height 65%, growx"); // Utilizamos "growx" para que ocupe todo o espaço horizontal disponível
        contentPane.add(painelPaciente, "grow");
    }
    private void realizarBusca() {
        String textoBusca = txtBusca.getText();
        for (JTable tabela : tabelasExibidas) {
            TableModel modelo = tabela.getModel();
            aplicarFiltroBusca(tabela, modelo, textoBusca);
        }
    }

    private void aplicarFiltroBusca(JTable tabela, TableModel modeloTabela, String textoBusca) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(modeloTabela);
        tabela.setRowSorter(sorter);

        if (textoBusca.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            String regex = "(?i)" + Pattern.quote(textoBusca);
            sorter.setRowFilter(RowFilter.regexFilter(regex, 1)); // Assumindo que o nome está na primeira coluna (índice 0)
        }
    }
    public void exibirDetalhesPaciente() {
    	txtBusca.setText("");
        DetalhesPaciente dp = new DetalhesPaciente();
        dp.atualizar();
        
        lblBuscar.setVisible(true);
        txtBusca.setVisible(true);
        painelPaciente.removeAll();
        painelPaciente.setLayout(new CardLayout(0, 0));
        painelPaciente.add(dp, BorderLayout.CENTER);
        painelPaciente.revalidate();
        painelPaciente.repaint();

        tabelasExibidas = new ArrayList<JTable>();
        tabelasExibidas.add(dp.getTabela());
        Temas.atualizarEstiloTabelas(tabelasExibidas);
    }
}