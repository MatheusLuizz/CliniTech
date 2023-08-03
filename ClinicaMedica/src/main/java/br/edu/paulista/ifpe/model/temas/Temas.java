package br.edu.paulista.ifpe.model.temas;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import br.edu.paulista.ifpe.gui.componentesCustomizados.PainelDegrade;

public class Temas {
	private static boolean modoEscuroAtivado = false;

	@SuppressWarnings("unused")
	public static void aplicarTemaEscuro(PainelDegrade painelAtalhos, PainelDegrade painelBusca, JToggleButton btnTema,
			List<JTable> tabelasExibidas) {
		modoEscuroAtivado = true;

		Color corBorda = new Color(68, 71, 90);
		Color corDeFundo = new Color(40, 42, 54);
		Color corTexto = new Color(248, 248, 242);

		Color corInicioAtalhosEscuro = Color.decode("#16161D");
		Color corFimAtalhosEscuro = Color.decode("#211F4D");

		Color corInicioBuscaEscuro = Color.decode("#282a36");
		Color corFimBuscaEscuro = Color.decode("#4e4c6d");

		Color corInicioPadrao = Color.decode("#1CB5E0");
		Color corFimPadrao = Color.decode("#000046");

		Color corInicioPadraoBusca = Color.GRAY;
		Color corFimPadraoBusca = new Color(220, 220, 220);

		Color corBordaPacienteEscuro = new Color(68, 71, 90);
		painelBusca.setBorder(BorderFactory.createLineBorder(corBorda));

		aplicarTransicaoCores(painelAtalhos, corInicioPadrao, corFimPadrao, corInicioAtalhosEscuro,
				corFimAtalhosEscuro);

		aplicarTransicaoCores(painelBusca, corInicioPadraoBusca, corFimPadraoBusca, corInicioBuscaEscuro,
				corFimBuscaEscuro);

		atualizarEstiloTabelas(tabelasExibidas);

		animarBotaoTema(btnTema, true);
	}

	public static void aplicarTemaPadrao(PainelDegrade painelAtalhos, PainelDegrade painelBusca, JPanel painelPaciente,
			JToggleButton btnTema, List<JTable> tabelasExibidas) {
		modoEscuroAtivado = false;

		Color corInicioAtalhosEscuro = Color.decode("#16161D");
		Color corFimAtalhosEscuro = Color.decode("#211F4D");

		Color corInicioBuscaEscuro = Color.decode("#282a36");
		Color corFimBuscaEscuro = Color.decode("#4e4c6d");

		Color corInicioAtalhosPadrao = Color.decode("#1CB5E0");
		Color corFimAtalhosPadrao = Color.decode("#000046");

		Color corInicioBuscaPadrao = Color.GRAY;
		Color corFimBuscaPadrao = new Color(220, 220, 220);

		aplicarTransicaoCores(painelAtalhos, corInicioAtalhosEscuro, corFimAtalhosEscuro, corInicioAtalhosPadrao,
				corFimAtalhosPadrao);

		aplicarTransicaoCores(painelBusca, corInicioBuscaEscuro, corFimBuscaEscuro, corInicioBuscaPadrao,
				corFimBuscaPadrao);

		atualizarEstiloTabelas(tabelasExibidas);
		painelPaciente.setBackground(null);

		painelBusca.setBorder(null);
		painelPaciente.setBorder(null);

		animarBotaoTema(btnTema, false);
	}

	@SuppressWarnings("serial")
	public static void atualizarEstiloTabelas(List<JTable> tabelas) {
		Color corFundoTabela;
		Color corTextoTabela;
		Color corSelecaoTabela;

		if (modoEscuroAtivado) {
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
			tabela.setSelectionForeground(corTextoTabela);

			JTableHeader header = tabela.getTableHeader();
			header.setBackground(new Color(189, 147, 240));
			header.setForeground(corTextoTabela);
			header.setDefaultRenderer(new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
							column);

					if (isSelected) {
						component.setForeground(Color.WHITE);
					} else {
						component.setForeground(corTextoTabela);
					}

					return component;
				}
			});
			DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
			cellRenderer.setBackground(corFundoTabela);
			cellRenderer.setForeground(corTextoTabela);
			tabela.setDefaultRenderer(Object.class, cellRenderer);

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

	private static void animarBotaoTema(JToggleButton btnTema, boolean temaEscuro) {
		Icon icon = btnTema.getIcon();
		if (!(icon instanceof ImageIcon)) {
			return;
		}

		Image image = ((ImageIcon) icon).getImage();

		TimingTarget target = new TimingTargetAdapter() {
			private float alpha = 1.0f;

			@Override
			public void timingEvent(float fraction) {
				alpha = 1.0f - fraction;

				Image fadedImage = ajustarAlpha(image, (int) (alpha * 255));
				ImageIcon newIcon = new ImageIcon(fadedImage);
				btnTema.setIcon(newIcon);
			}

			@Override
			public void end() {
				Icon newIcon;
				if (temaEscuro) {
					newIcon = new ImageIcon(Temas.class.getResource("/br/edu/paulista/ifpe/model/images/iconeLua.png"));
				} else {
					newIcon = new ImageIcon(Temas.class.getResource("/br/edu/paulista/ifpe/model/images/iconeSol.png"));
				}
				btnTema.setIcon(newIcon);

				Image nextImage = ((ImageIcon) newIcon).getImage();
				Image nextFadedImage = ajustarAlpha(nextImage, 0);
				ImageIcon nextIcon = new ImageIcon(nextFadedImage);
				btnTema.setIcon(nextIcon);

				TimingTarget nextTarget = new TimingTargetAdapter() {
					private float nextAlpha = 0.0f;

					@Override
					public void timingEvent(float fraction) {
						nextAlpha = fraction;

						Image nextUnfadedImage = ajustarAlpha(nextImage, (int) (nextAlpha * 255));
						ImageIcon nextUnfadedIcon = new ImageIcon(nextUnfadedImage);
						btnTema.setIcon(nextUnfadedIcon);
					}
				};

				Animator nextAnimator = new Animator(400, nextTarget);
				nextAnimator.setResolution(10);
				nextAnimator.start();
			}
		};

		Animator animator = new Animator(400, target);
		animator.setResolution(10);
		animator.start();
	}

	private static void aplicarTransicaoCores(PainelDegrade painel, Color corInicioPadrao, Color corFimPadrao,
			Color corInicioEscuro, Color corFimEscuro) {
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				Color corInicioAtual = interpolateColor(corInicioPadrao, corInicioEscuro, fraction);
				Color corFimAtual = interpolateColor(corFimPadrao, corFimEscuro, fraction);

				painel.setColors(corInicioAtual, corFimAtual);
				painel.repaint();
			}

			@Override
			public void end() {
			}
		};

		Animator animator = new Animator(400, target);
		animator.setResolution(10);
		animator.start();
	}

	private static Color interpolateColor(Color startColor, Color endColor, float fraction) {
		int red = (int) (startColor.getRed() + fraction * (endColor.getRed() - startColor.getRed()));
		int green = (int) (startColor.getGreen() + fraction * (endColor.getGreen() - startColor.getGreen()));
		int blue = (int) (startColor.getBlue() + fraction * (endColor.getBlue() - startColor.getBlue()));
		return new Color(red, green, blue);
	}

	private static Image ajustarAlpha(Image image, int alpha) {
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setComposite(AlphaComposite.SrcOver.derive(alpha / 255.0f));
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		return bufferedImage;
	}
}
