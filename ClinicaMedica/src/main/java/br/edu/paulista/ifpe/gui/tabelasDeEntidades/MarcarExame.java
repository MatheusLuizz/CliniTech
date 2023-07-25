package br.edu.paulista.ifpe.gui.tabelasDeEntidades;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import br.edu.paulista.ifpe.gui.PainelDegrade;

@SuppressWarnings("serial")
public class MarcarExame extends JDialog {

	private PainelDegrade contentPane = new PainelDegrade();
	private JButton btnCadastro = new JButton();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MarcarExame dialog = new MarcarExame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "<html>Erro ao abrir a pagina de marcação</html>", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Create the dialog.
	 */
	public MarcarExame() {
		super();
		setModal(true);
		setTitle("Marcar exame");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new PainelDegrade();
		contentPane.setColors(new Color(0, 128, 255), new Color(50, 205, 50));
        contentPane.repaint();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setBounds(160, 200, 120, 30);
		btnCadastro.setToolTipText("Finalizar cadastro");
		contentPane.add(btnCadastro);
	}

}
