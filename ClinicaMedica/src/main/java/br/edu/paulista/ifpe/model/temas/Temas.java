package br.edu.paulista.ifpe.model.temas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

import br.edu.paulista.ifpe.gui.Home;
import br.edu.paulista.ifpe.gui.PainelDegrade;

public class Temas {
	private static boolean modoEscuroAtivado = false;

	public static void aplicarTemaEscuro(PainelDegrade painelAtalhos, PainelDegrade painelBusca, JPanel painelPaciente,
			JToggleButton btnTema, List<JTable> tabelasExibidas) {
		modoEscuroAtivado = true;

		painelAtalhos.setColors(Color.decode("#16161D"), Color.decode("#211F4D"));
		Color corDeFundo = new Color(40, 42, 54);
		Color corTexto = new Color(248, 248, 242);
		Color corBorda = new Color(68, 71, 90);
		painelBusca.setColors(Color.decode("#282a36"), Color.decode("#4e4c6d"));
		Color corBusca = new Color(59, 61, 76);
		Color corSelecaoTabela = new Color(70, 130, 180);
		Color corTextoTabela = new Color(248, 248, 242);
		btnTema.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeLua.png")));
		
		
		painelPaciente.setBackground(corDeFundo);

		painelBusca.setBorder(BorderFactory.createLineBorder(corBorda));
		painelPaciente.setBorder(BorderFactory.createLineBorder(corBorda));

		UIManager.put("TableHeader.background", corBusca);
		UIManager.put("TableHeader.foreground", corTexto);
		UIManager.put("Table.selectionBackground", corSelecaoTabela);
		UIManager.put("Table.selectionForeground", corTextoTabela);

		atualizarEstiloTabelas(tabelasExibidas);

		// Percorre os componentes do painelPaciente e aplica o estilo escuro
		// individualmente
		Component[] components = painelPaciente.getComponents();
		for (Component component : components) {
			if (component instanceof JComponent) {
				JComponent jComponent = (JComponent) component;
				jComponent.setBackground(corDeFundo);
				jComponent.setForeground(corTexto);
				jComponent.setBorder(BorderFactory.createLineBorder(corBorda));
			}
		}
	}

	public static void aplicarTemaPadrao(PainelDegrade painelAtalhos, PainelDegrade painelBusca, JPanel painelPaciente,
			JToggleButton btnTema, List<JTable> tabelasExibidas) {
		modoEscuroAtivado = false;

		btnTema.setIcon(new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeSol.png")));

		painelAtalhos.setColors(Color.decode("#1CB5E0"), Color.decode("#000046"));
		painelBusca.setColors(Color.GRAY, new Color(220, 220, 220));
		painelPaciente.setBackground(null);

		painelBusca.setBorder(null);
		painelPaciente.setBorder(null);

		atualizarEstiloTabelas(tabelasExibidas);
	}

	public static void atualizarEstiloTabelas(List<JTable> tabelas) {
		Color corFundoTabela;
		Color corTextoTabela;
		Color corSelecaoTabela;

		if (modoEscuroAtivado == true) {
			corFundoTabela = new Color(40, 42, 54);
			corTextoTabela = new Color(248, 248, 242);
			corSelecaoTabela = new Color(68, 71, 90);
		} else {
			corFundoTabela = Color.WHITE;
			corTextoTabela = Color.BLACK;
			corSelecaoTabela = new Color(135, 206, 235);
		}

		for (JTable tabela : tabelas) {
			tabela.setBackground(corFundoTabela);
			tabela.setForeground(corTextoTabela);
			tabela.setSelectionBackground(corSelecaoTabela);

			JTableHeader header = tabela.getTableHeader();
			header.setBackground(corFundoTabela);
			header.setForeground(corTextoTabela);

			Container container = tabela.getParent();
			if (container instanceof JViewport) {
				container = container.getParent();
				if (container instanceof JScrollPane) {
					JScrollPane scrollPane = (JScrollPane) container;
					scrollPane.getViewport().setBackground(corFundoTabela);
					scrollPane.getViewport().setForeground(corTextoTabela);
				}
			}
		}
	}
}
