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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.paulista.ifpe.gui.tabelasDeEntidades.DetalhesPaciente;
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
                }
            }
        });
    }

    public Home() {
        setResizable(true);
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
        btnPaciente.setToolTipText("Exibir os pacientes cadastrados");
        btnPaciente.setForeground(new Color(13, 73, 151));
        btnPaciente.setBorderPainted(false);
        btnPaciente.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconePaciente.png")));

        btnMedico = new JButton();
        btnMedico.setToolTipText("Exibir os médios cadastrados");
        btnMedico.setForeground(new Color(13, 73, 151));
        btnMedico.setBorderPainted(false);
        btnMedico.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeMedico.png")));

        btnRemedio = new JButton();
        btnRemedio.setToolTipText("Exibir os remédios cadastrados");
        btnRemedio.setForeground(new Color(13, 73, 151));
        btnRemedio.setBorderPainted(false);
        btnRemedio.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeRemedio.png")));

        btnExame = new JButton();
        btnExame.setToolTipText("Exibir exames");
        btnExame.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeExame.png")));
        btnExame.setForeground(new Color(13, 73, 151));;
        btnExame.setBorderPainted(false);

        GroupLayout gl_painelAtalhos = new GroupLayout(painelAtalhos);
        gl_painelAtalhos.setHorizontalGroup(
        	gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_painelAtalhos.createSequentialGroup()
        			.addGroup(gl_painelAtalhos.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(btnExame, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
        				.addComponent(btnRemedio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnMedico, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnPaciente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addContainerGap(20, Short.MAX_VALUE))
        );
        gl_painelAtalhos.setVerticalGroup(
        	gl_painelAtalhos.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_painelAtalhos.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(btnPaciente, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnMedico, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnRemedio, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnExame, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(569, Short.MAX_VALUE))
        );
        painelAtalhos.setLayout(gl_painelAtalhos);
        contentPane.setLayout(null);

        painelBusca = new PainelDegrade();
        painelBusca.setBounds(91, 0, 1473, 65);
        painelBusca.setColors(Color.GRAY, new Color(220, 220, 220));
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
        btnPaciente.setOpaque(false);
        btnPaciente.setContentAreaFilled(false);
        btnPaciente.setBorderPainted(false);

        btnMedico.setOpaque(false);
        btnMedico.setContentAreaFilled(false);
        btnMedico.setBorderPainted(false);

        btnRemedio.setOpaque(false);
        btnRemedio.setContentAreaFilled(false);
        btnRemedio.setBorderPainted(false);

        btnExame.setOpaque(false);
        btnExame.setContentAreaFilled(false);
        btnExame.setBorderPainted(false);

        btnTema.setOpaque(false);
        btnTema.setContentAreaFilled(false);
        btnTema.setBorderPainted(false);
        
        contentPane.add(painelAtalhos, "width 15%, height 100%");
        contentPane.add(painelBusca, "width 85%, height 65%, growx"); // Utilizamos "growx" para que ocupe todo o espaço horizontal disponível
        contentPane.add(painelPaciente, "grow");
    }

    private void aplicarFiltroBusca(JTable tabela, TableModel modeloTabela, String textoBusca) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloTabela);
        tabela.setRowSorter(sorter);

        if (textoBusca.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo de busca está vazio");
            sorter.setRowFilter(null);
        } else {
            String regex = "(?i)" + Pattern.quote(textoBusca);
            sorter.setRowFilter(RowFilter.regexFilter(regex, 0)); // Assumindo que o nome está na primeira coluna
            // (índice 0)
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
