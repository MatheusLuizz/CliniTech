package br.edu.paulista.ifpe.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.paulista.ifpe.gui.tabelasDeEntidades.CadastroExames;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.MarcarConsulta;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.MarcarExame;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaConsulta;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaExame;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaExameMarcado;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaMedico;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaPaciente;
import br.edu.paulista.ifpe.gui.tabelasDeEntidades.TelaRemedio;
import br.edu.paulista.ifpe.model.temas.Temas;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class Home extends JFrame {

    private JPanel contentPane;
    private JLabel lblNome;
    private PainelDegrade painelAtalhos;
    private JButton btnPaciente;
    private JButton btnMedico;
    private JButton btnConsultas;
    private JButton btnInicio;
    private PainelDegrade painelBusca;
    private JPanel painelPrincipal;
    private final JTextField txtBusca = new JTextField();
    private JLabel lblBuscar;
    private List<JTable> tabelasExibidas;
    private JButton btnRemedio;
    private JButton btnExame;
    private JToggleButton btnTema;
    private JButton btnExameMarcado;
    private JButton btnLogout;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home("Sei lá");
                    frame.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Não foi possível exibir a tela Home", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
    }

    public Home(String nomeAutenticado) {
    	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar o visual para seu sistema");
        }
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
        btnInicio.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeClinitech.png")));
        btnInicio.setForeground(new Color(13, 73, 151));
        btnInicio.setBackground(getBackground());
        btnInicio.setBorderPainted(false);
        
        btnConsultas = new JButton();
        btnConsultas.setToolTipText("Consultas");
        btnConsultas.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeConsulta.png")));
        btnConsultas.setForeground(new Color(13,73,151));
        btnConsultas.setBackground(getBackground());
        btnConsultas.setBorderPainted(false);
        
        btnExameMarcado = new JButton();
        btnExameMarcado.setToolTipText("Exames marcados");
        btnExameMarcado.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeMarcarExame.png")));
        btnExameMarcado.setBackground(getBackground());
        btnExameMarcado.setBorderPainted(false);
        
        btnLogout = new JButton();
        btnLogout.setToolTipText("Logout");
        btnLogout.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeLogout.png")));
        btnLogout.setBackground(getBackground());
        btnLogout.setBorderPainted(false);
        
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
        				.addComponent(btnConsultas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnExameMarcado, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnLogout, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        			.addComponent(btnExameMarcado, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
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
        
        lblNome = new JLabel(nomeAutenticado);
        lblNome.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNome.setBounds(1280, 26, 120, 23);
        painelBusca.add(lblNome);

        btnTema = new JToggleButton("");
        btnTema.setToolTipText("Tema escuro");
        btnTema.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeSol.png")));
        btnTema.setBounds(10, 10, 32, 33);
        painelBusca.add(btnTema);
        contentPane.add(painelAtalhos);
        lblBuscar.setVisible(false);
        txtBusca.setVisible(false);

        painelPrincipal = new JPanel();
        painelPrincipal.setBackground(new Color(245, 245, 245));
        painelPrincipal.setBounds(91, 67, 1459, 865);
        contentPane.add(painelPrincipal);
        painelPrincipal.setLayout(new CardLayout(0, 0));
        
        txtBusca.setVisible(false);
		lblBuscar.setVisible(false);
		TelaInicio i = new TelaInicio();
		painelPrincipal.removeAll();
		painelPrincipal.setLayout(new CardLayout(0, 0));
		painelPrincipal.add(i, BorderLayout.CENTER);
		painelPrincipal.revalidate();
		painelPrincipal.repaint();
        
        btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusca.setVisible(false);
				lblBuscar.setVisible(false);
				TelaInicio i = new TelaInicio();
				Component[] components = i.getComponents();
		        for (Component component : components) {
		            if (component instanceof JTable) {
		                tabelasExibidas.add((JTable) component);
		            }
		        }
		        
		        painelPrincipal.removeAll();
				painelPrincipal.setLayout(new CardLayout(0, 0));
				painelPrincipal.add(i, BorderLayout.CENTER);
				
				painelPrincipal.revalidate();
				painelPrincipal.repaint();
			}
		});
        
        

        btnPaciente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                int opcao = JOptionPane.showOptionDialog(
                        null,
                        "Escolha uma opção:",
                        "Pacientes",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Visualizar todos", "Adicionar"},
                        "Visualizar"
                );

                if (opcao == JOptionPane.YES_OPTION) {
                	txtBusca.setText("");
                    TelaPaciente telaPaciente = new TelaPaciente();
                    telaPaciente.atualizar();
                    
                    lblBuscar.setVisible(true);
                    txtBusca.setVisible(true);
                    painelPrincipal.removeAll();
                    painelPrincipal.setLayout(new CardLayout(0, 0));
                    painelPrincipal.add(telaPaciente);
                    painelPrincipal.revalidate();
                    painelPrincipal.repaint();

                    tabelasExibidas = new ArrayList<JTable>();
                    tabelasExibidas.add(telaPaciente.getTabela());
                    Temas.atualizarEstiloTabelas(tabelasExibidas);
                } else if (opcao == JOptionPane.NO_OPTION) {
                    CadastroPaciente cp = new CadastroPaciente();
                    cp.setLocationRelativeTo(null);
                    cp.setVisible(true);
                }
            }
        });
        btnMedico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                int opcao = JOptionPane.showOptionDialog(
                        null,
                        "Escolha uma opção:",
                        "Medicos",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Visualizar todos", "Adicionar"},
                        "Visualizar"
                );

                if (opcao == JOptionPane.YES_OPTION) {
                	txtBusca.setText("");
                    TelaMedico telaMedico = new TelaMedico();
                    telaMedico.atualizar();
                    
                    lblBuscar.setVisible(true);
                    txtBusca.setVisible(true);
                    painelPrincipal.removeAll();
                    painelPrincipal.setLayout(new CardLayout(0, 0));
                    painelPrincipal.add(telaMedico);
                    painelPrincipal.revalidate();
                    painelPrincipal.repaint();

                    tabelasExibidas = new ArrayList<JTable>();
                    tabelasExibidas.add(telaMedico.getTabela());
                    Temas.atualizarEstiloTabelas(tabelasExibidas);
                } else if (opcao == JOptionPane.NO_OPTION) {
                    CadastroMedico cr = new CadastroMedico();
                    cr.setLocationRelativeTo(null);
                    cr.setVisible(true);
                }
            }
        });
        btnRemedio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                int opcao = JOptionPane.showOptionDialog(
                        null,
                        "Escolha uma opção:",
                        "Remedios",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Visualizar todos", "Adicionar"},
                        "Visualizar"
                );

                if (opcao == JOptionPane.YES_OPTION) {
                	txtBusca.setText("");
                    TelaRemedio telaRemedio = new TelaRemedio();
                    telaRemedio.atualizar();
                    
                    lblBuscar.setVisible(true);
                    txtBusca.setVisible(true);
                    painelPrincipal.removeAll();
                    painelPrincipal.setLayout(new CardLayout(0, 0));
                    painelPrincipal.add(telaRemedio);
                    painelPrincipal.revalidate();
                    painelPrincipal.repaint();

                    tabelasExibidas = new ArrayList<JTable>();
                    tabelasExibidas.add(telaRemedio.getTabela());
                    Temas.atualizarEstiloTabelas(tabelasExibidas);
                } else if (opcao == JOptionPane.NO_OPTION) {
                    CadastroRemedios cr = new CadastroRemedios();
                    cr.setLocationRelativeTo(null);
                    cr.setVisible(true);
                }
            }
        });
        btnExame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                int opcao = JOptionPane.showOptionDialog(
                        null,
                        "Escolha uma opção:",
                        "Exames",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Visualizar todos", "Adicionar"},
                        "Visualizar"
                );

                if (opcao == JOptionPane.YES_OPTION) {
                	txtBusca.setText("");
                    TelaExame telaExame = new TelaExame();
                    telaExame.atualizar();
                    
                    lblBuscar.setVisible(true);
                    txtBusca.setVisible(true);
                    painelPrincipal.removeAll();
                    painelPrincipal.setLayout(new CardLayout(0, 0));
                    painelPrincipal.add(telaExame);
                    painelPrincipal.revalidate();
                    painelPrincipal.repaint();

                    tabelasExibidas = new ArrayList<JTable>();
                    tabelasExibidas.add(telaExame.getTabela());
                    Temas.atualizarEstiloTabelas(tabelasExibidas);
                } else if (opcao == JOptionPane.NO_OPTION) {
                    CadastroExames ce = new CadastroExames();
                    ce.setLocationRelativeTo(null);
                    ce.setVisible(true);
                }
            }
        });
        btnTema.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (btnTema.isSelected()) {
                	Temas.aplicarTemaEscuro(painelAtalhos, painelBusca, btnTema, tabelasExibidas);
                    lblBuscar.setForeground(new Color(255, 105, 180));
                    btnTema.setToolTipText("Tema claro");
                } else {
                	Temas.aplicarTemaPadrao(painelAtalhos, painelBusca, painelPrincipal, btnTema, tabelasExibidas);
                	lblBuscar.setForeground(Color.BLACK);
                    btnTema.setToolTipText("Tema escuro");
                }
                Temas.atualizarEstiloTabelas(tabelasExibidas);
                btnTema.setFocusPainted(false);
                
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
                    painelPrincipal.removeAll();
                    painelPrincipal.setLayout(new CardLayout(0, 0));
                    painelPrincipal.add(telaConsulta, BorderLayout.CENTER);
                    painelPrincipal.revalidate();
                    painelPrincipal.repaint();

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
        btnExameMarcado.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 int opcao = JOptionPane.showOptionDialog(
                         null,
                         "Escolha uma opção:",
                         "Exames marcados",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.QUESTION_MESSAGE,
                         null,
                         new String[]{"Visualizar todos", "Marcar"},
                         "Visualizar"
                 );

                 if (opcao == JOptionPane.YES_OPTION) {
                 	txtBusca.setText("");
                     TelaExameMarcado tem = new TelaExameMarcado();
                     tem.atualizar();
                     
                     lblBuscar.setVisible(true);
                     txtBusca.setVisible(true);
                     painelPrincipal.removeAll();
                     painelPrincipal.setLayout(new CardLayout(0, 0));
                     painelPrincipal.add(tem, BorderLayout.CENTER);
                     painelPrincipal.revalidate();
                     painelPrincipal.repaint();

                     tabelasExibidas = new ArrayList<JTable>();
                     tabelasExibidas.add(tem.getTabela());
                     Temas.atualizarEstiloTabelas(tabelasExibidas);
                 } else if (opcao == JOptionPane.NO_OPTION) {
                     MarcarExame me = new MarcarExame();
                     me.setLocationRelativeTo(null);
                     me.setVisible(true);
                 }
             }
        });
        btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaLogin tl = new TelaLogin();
				tl.setVisible(true);
			}
		});
        
        btnInicio.setOpaque(false);
        btnInicio.setContentAreaFilled(false);
        btnInicio.setFocusPainted(false);
        
        btnPaciente.setOpaque(false);
        btnPaciente.setContentAreaFilled(false);
        btnPaciente.setFocusPainted(false);

        btnMedico.setOpaque(false);
        btnMedico.setContentAreaFilled(false);
        btnMedico.setFocusPainted(false);

        btnRemedio.setOpaque(false);
        btnRemedio.setContentAreaFilled(false);
        btnRemedio.setFocusPainted(false);

        btnExame.setOpaque(false);
        btnExame.setContentAreaFilled(false);
        btnExame.setFocusPainted(false);

        btnTema.setOpaque(false);
        btnTema.setContentAreaFilled(false);
        btnTema.setFocusPainted(false);
        btnTema.setFocusable(false);
        
        btnConsultas.setOpaque(false);
        btnConsultas.setContentAreaFilled(false);
        btnConsultas.setFocusPainted(false);
        
        btnExameMarcado.setOpaque(false);
        btnExameMarcado.setContentAreaFilled(false);
        btnExameMarcado.setFocusPainted(false);
        
        btnLogout.setOpaque(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setFocusPainted(false);
        
        contentPane.add(painelAtalhos, "width 15%, height 100%");
        contentPane.add(painelBusca, "width 85%, height 65%, growx"); // Utilizamos "growx" para que ocupe todo o espaço horizontal disponível
        contentPane.add(painelPrincipal, "grow");
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
}