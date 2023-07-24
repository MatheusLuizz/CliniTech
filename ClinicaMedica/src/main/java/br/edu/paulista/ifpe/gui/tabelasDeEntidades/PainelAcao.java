package br.edu.paulista.ifpe.gui.tabelasDeEntidades;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;

import br.edu.paulista.ifpe.core.BotaoAcao;

@SuppressWarnings("serial")
public class PainelAcao<T> extends JPanel {
    private BotaoAcao btnVisualizar;
    private BotaoAcao btnEditar;
    private BotaoAcao btnExcluir;
    private Object objeto;
    private TableActionEvent evento;
    private String idMedico;

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }
    private String idPaciente;
    public void setIdPaciente(String idPaciente) {
    	this.idPaciente = idPaciente;
    }
	
	public PainelAcao() {
		setBackground(new Color(255, 255, 255));
		
		btnVisualizar = new BotaoAcao();
		btnVisualizar.setIcon(new ImageIcon(PainelAcao.class.getResource("/br/edu/paulista/ifpe/model/images/view.png")));
		
		add(btnVisualizar);
		btnEditar = new BotaoAcao();
		btnEditar.setIcon(new ImageIcon(PainelAcao.class.getResource("/br/edu/paulista/ifpe/model/images/edit.png")));
		
		add(btnEditar);
		btnExcluir = new BotaoAcao();
		btnExcluir.setIcon(new ImageIcon(PainelAcao.class.getResource("/br/edu/paulista/ifpe/model/images/delete.png")));
		
		add(btnExcluir);
	}
	public JPanel getPanel () {
		return this;
	}
	public void initEvent(JTable table, TableActionEvent evento, int linha) {
        this.evento = evento;
        

        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                evento.onView(table.getEditingRow());
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                evento.onEdit(table.getEditingRow());
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                evento.onDelete(table.getEditingRow());
            }
        });
    }

	
}
