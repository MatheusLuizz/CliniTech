package br.edu.paulista.ifpe.gui.componentesCustomizados;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import br.edu.paulista.ifpe.gui.Home;

@SuppressWarnings("serial")
public class ComboBoxCustomizada<E> extends JComboBox<E> {
	public ComboBoxCustomizada() {
		setUI(new ComboBoxModernaUI());
	}

	public void setModernModel(E[] items) {
		DefaultComboBoxModel<E> model = new DefaultComboBoxModel<>(items);
		setModel(model);
	}

	private class ComboBoxModernaUI extends javax.swing.plaf.basic.BasicComboBoxUI {
		@Override
		protected JButton createArrowButton() {
			JButton button = new JButton();
			button.setBackground(Color.WHITE);
			button.setIcon(
					new ImageIcon(Home.class.getResource("/br/edu/paulista/ifpe/model/images/iconeComboBox.png")));
			button.setBorder(BorderFactory.createEmptyBorder());
			return button;
		}

		@Override
		protected ComboPopup createPopup() {
			return (ComboPopup) new ModernComboPopup(comboBox);
		}
	}

	private class ModernComboPopup extends BasicComboPopup {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ModernComboPopup(JComboBox combo) {
			super(combo);
		}

		@Override
		protected JScrollPane createScroller() {
			JScrollPane scroller = new JScrollPane(list);
			scroller.getVerticalScrollBar().setUI(new ModernScrollBarUI());
			scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			return scroller;
		}
	}

	private class ModernScrollBarUI extends javax.swing.plaf.basic.BasicScrollBarUI {
		private final Dimension emptyDim = new Dimension();

		@Override
		protected JButton createDecreaseButton(int orientation) {
			return new JButton() {
				@Override
				public Dimension getPreferredSize() {
					return emptyDim;
				}
			};
		}

		@Override
		protected JButton createIncreaseButton(int orientation) {
			return new JButton() {
				@Override
				public Dimension getPreferredSize() {
					return emptyDim;
				}
			};
		}

		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
			g.setColor(Color.LIGHT_GRAY);
			((Graphics2D) g).fill(thumbBounds);
		}
	}
}