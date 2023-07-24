package br.edu.paulista.ifpe.gui.grafico;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LegendaDoItem extends JPanel {
	private CorLabel lblCor;
	private JLabel lblNome;

	public LegendaDoItem(LegendaModelo dado) {
		initComponents();
		setOpaque(false);
		lblCor.setBackground(dado.getCor());
		lblCor.setForeground(dado.getColorLight());
		lblNome.setText(dado.getNome());
	}

	private void initComponents() {

		lblCor = new CorLabel();
		lblNome = new JLabel();

		lblCor.setText("CorEtiqueta1");

		lblNome.setForeground(new java.awt.Color(180, 180, 180));
		lblNome.setText("Nome");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(lblCor, javax.swing.GroupLayout.PREFERRED_SIZE, 16,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lblNome)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(lblNome).addGap(0, 0, Short.MAX_VALUE))
						.addComponent(lblCor, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
	}
}
