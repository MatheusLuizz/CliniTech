package br.edu.paulista.ifpe.gui.tabelasDeEntidades;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import br.edu.paulista.ifpe.data.PacienteDAO;
import br.edu.paulista.ifpe.gui.CadastroPaciente;
import br.edu.paulista.ifpe.model.entidades.Paciente;
import br.edu.paulista.ifpe.model.tablemodel.DetalhesPacienteTableModel;

@SuppressWarnings("serial")
public class DetalhesPaciente extends JPanel {

	private JScrollPane scrollPane;
	private JTable tabela;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalhesPaciente frame = new DetalhesPaciente();
					frame.atualizar();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Não foi possível exibir os detalhes do paciente", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public DetalhesPaciente() {
		setBounds(100, 100, 800, 500);
		setLayout(new BorderLayout());

		JPanel tabelaAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

		add(tabelaAcoes, BorderLayout.NORTH);

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tabela = new JTable();
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabela.setModel(new DetalhesPacienteTableModel());
		tabela.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setViewportView(tabela);
	}

	public JTable getTabela() {
		return tabela;
	}

	public TableModel getModeloTabela() {
		return tabela.getModel();
	}

	public void atualizar() {
		try {
			PacienteDAO dao = new PacienteDAO();
			List<Paciente> lista = dao.buscar(new Paciente());

			DetalhesPacienteTableModel modelo = (DetalhesPacienteTableModel) tabela.getModel();

			modelo.limpar();

			// Converte a lista de pacientes em lista de detalhes de pacientes
			// antes de adicionar ao modelo da tabela
			
			modelo.adicionar(lista);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar buscar os detalhes do paciente");
		}
	}
}
