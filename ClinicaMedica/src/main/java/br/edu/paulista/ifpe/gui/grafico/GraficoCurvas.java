package br.edu.paulista.ifpe.gui.grafico;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Path2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import br.edu.paulista.ifpe.data.ConnectionBD;
import br.edu.paulista.ifpe.gui.grafico.graficoBlank.GraficoEmBranco;
import br.edu.paulista.ifpe.gui.grafico.graficoBlank.RenderizarGraficoEmBranco;
import br.edu.paulista.ifpe.gui.grafico.graficoBlank.SeriesSize;

@SuppressWarnings("serial")
public class GraficoCurvas extends JPanel {

	DecimalFormat df = new DecimalFormat("#,##0.##");
	private List<LegendaModelo> legendas = new ArrayList<>();
	private List<ModeloGrafico> modelo = new ArrayList<>();
	private final Animator animador;
	private float animar;
	GraficoEmBranco graficoEmBranco;
	private JPanel painelLegenda;

	public GraficoCurvas() {
		initComponents();
		setOpaque(false);
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fracao) {
				animar = fracao;
				repaint();
			}
		};
		animador = new Animator(800, target);
		animador.setResolution(0);
		animador.setAcceleration(0.5f);
		animador.setDeceleration(0.5f);
		graficoEmBranco.getNiceScale().setMaxTicks(5);
		graficoEmBranco.setRenderizarGraficoEmBranco(new RenderizarGraficoEmBranco() {
			@Override
			public int getMaximoLegendas() {
				return legendas.size();
			}

			@Override
			public String getTextoLabel(int index) {
				return modelo.get(index).getLabel();
			}

			@Override
			public void serieRenderizacao(GraficoEmBranco grafico, Graphics2D g2, SeriesSize tamanho, int indice) {
			}

			@Override
			public void serieRenderizacao(GraficoEmBranco grafico, Graphics2D g2, SeriesSize tamanho, int indice,
					List<Path2D.Double> gradiente) {
				for (int i = 0; i < legendas.size(); i++) {
					double tamanhoY;
					double tamanhoX;
					double x = tamanho.getX() + tamanho.getWidth() / 2f;
					if (indice == 0) {
						tamanhoY = grafico.getValoresDeSerie(0, tamanho.getHeight()) * animar;
						tamanhoY = tamanho.getY() + tamanho.getHeight() - tamanhoY;
						tamanhoX = x - tamanho.getWidth() / 2;
					} else {
						tamanhoY = grafico.getValoresDeSerie(modelo.get(indice - 1).getValores()[i],
								tamanho.getHeight()) * animar;
						tamanhoY = tamanho.getY() + tamanho.getHeight() - tamanhoY;
						tamanhoX = x - tamanho.getWidth();
					}
					double s = tamanhoX + tamanho.getWidth() / 4;
					double seriesValues = grafico.getValoresDeSerie(modelo.get(indice).getValores()[i],
							tamanho.getHeight()) * animar;
					double yy = tamanho.getY() + tamanho.getHeight() - seriesValues;
					gradiente.get(i).append(new CubicCurve2D.Double(tamanhoX, tamanhoY, s, tamanhoY, s, yy, x, yy),
							true);
					if (indice == grafico.getContagemLabel() - 1) {
						tamanhoX = x;
						tamanhoY = yy;
						s = tamanhoX + tamanho.getWidth() / 4;
						seriesValues = grafico.getValoresDeSerie(0, tamanho.getHeight()) * animar;
						yy = tamanho.getY() + tamanho.getHeight() - seriesValues;
						gradiente.get(i).append(new CubicCurve2D.Double(tamanhoX, tamanhoY, s, tamanhoY, s, yy,
								x + tamanho.getWidth() / 2, yy), true);
					}
				}
			}

			@Override
			public void renderizarGraficos(Graphics2D g2, List<Path2D.Double> gradiente) {
				g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
				for (int i = 0; i < gradiente.size(); i++) {
					Color c = legendas.get(i).getColorLight();
					g2.setPaint(new GradientPaint(0, 0, legendas.get(i).getCor(), 0, getHeight(),
							new Color(c.getRed(), c.getGreen(), c.getBlue(), 0)));
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
					g2.fill(gradiente.get(i));
					g2.setPaint(new GradientPaint(0, 0, legendas.get(i).getCor(), getWidth(), 0,
							legendas.get(i).getColorLight()));
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
					g2.draw(gradiente.get(i));
				}
			}

			public boolean mouseMoving(GraficoEmBranco grafico, MouseEvent evt, Graphics2D g2, SeriesSize tamanho,
					int indice) {
				int mouseX = evt.getX();
				double larguraColuna = tamanho.getWidth() / grafico.getContagemLabel();
				int indiceMouse = (int) ((mouseX - tamanho.getX()) / larguraColuna);

				if (indiceMouse >= 0 && indiceMouse < grafico.getContagemLabel()) {
					if (indiceMouse == indice || indiceMouse == indice + 1) {
						double valor = 0;
						for (int i = 0; i < legendas.size(); i++) {
							valor = modelo.get(indiceMouse).getValores()[i];
						}
						String tooltipText = "Numero de atendimentos: " + df.format(valor);
						graficoEmBranco.setToolTipText(tooltipText);
					} else {
						graficoEmBranco.setToolTipText(null);
					}
				}

				return false;
			}
		});
	}

	public void adicionarLegenda(String name, Color color, Color color1) {
		LegendaModelo dado = new LegendaModelo(name, color, color1);
		legendas.add(dado);
		painelLegenda.add(new LegendaDoItem(dado));
		painelLegenda.repaint();
		painelLegenda.revalidate();
	}

	public void adicionarDados() {
		adicionarCurva();
	}

	public void adicionarMedico(int id) {
		adicionarCurvaMedico(id);
	}

	public void adicionarCurvaMedico(int idMedico) {
		String sql = "SELECT ano, mes, quantidade_exames_marcados, quantidade_consultas, "
				+ "quantidade_exames_marcados + quantidade_consultas AS total_geral " + "FROM " + "( " + "    SELECT "
				+ "        EXTRACT(YEAR FROM data) AS ano, " + "        EXTRACT(MONTH FROM data) AS mes, "
				+ "        COUNT(CASE WHEN tabela = 'exame_marcado' THEN 1 END) AS quantidade_exames_marcados, "
				+ "        COUNT(CASE WHEN tabela = 'consulta' THEN 1 END) AS quantidade_consultas " + "    FROM "
				+ "        ( " + "            SELECT data, 'exame_marcado' AS tabela FROM exame_marcado "
				+ "            WHERE id_medico = " + idMedico + " " + "            UNION ALL "
				+ "            SELECT data, 'consulta' AS tabela FROM consulta " + "            WHERE id_medico = "
				+ idMedico + " " + "        ) AS todas_as_tabelas " + "    GROUP BY "
				+ "        EXTRACT(YEAR FROM data), " + "        EXTRACT(MONTH FROM data) " + ") AS resultados_por_mes "
				+ "WHERE " + "    ano = 2023 " + "ORDER BY " + "    mes";

		adicionarCurvaNoGrafico(sql, "Atendimentos");
	}

	private void adicionarCurva() {
		String sql = "SELECT ano, mes, quantidade_exames_marcados, quantidade_consultas, "
				+ "quantidade_exames_marcados + quantidade_consultas AS total_geral " + "FROM " + "( " + "    SELECT "
				+ "        EXTRACT(YEAR FROM data) AS ano, " + "        EXTRACT(MONTH FROM data) AS mes, "
				+ "        COUNT(CASE WHEN tabela = 'exame_marcado' THEN 1 END) AS quantidade_exames_marcados, "
				+ "        COUNT(CASE WHEN tabela = 'consulta' THEN 1 END) AS quantidade_consultas " + "    FROM "
				+ "        ( " + "            SELECT data, 'exame_marcado' AS tabela FROM exame_marcado "
				+ "            UNION ALL " + "            SELECT data, 'consulta' AS tabela FROM consulta "
				+ "        ) AS todas_as_tabelas " + "    GROUP BY " + "        EXTRACT(YEAR FROM data), "
				+ "        EXTRACT(MONTH FROM data) " + ") AS resultados_por_mes " + "WHERE " + "    ano = 2023 "
				+ "ORDER BY " + "    mes";

		adicionarCurvaNoGrafico(sql, "Atendimentos");
	}

	private void adicionarCurvaNoGrafico(String sql, String nomeCurva) {
		ConnectionBD connectionBD = new ConnectionBD();
		Connection connection = connectionBD.abrir();

		if (connection != null) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();

				List<ModeloGrafico> dadosGrafico = new ArrayList<>();

				while (resultSet.next()) {
					String mes = getNomeMes(resultSet.getInt("mes"));
					double total = resultSet.getDouble("total_geral");

					ModeloGrafico dado = new ModeloGrafico(mes, new double[] { total });
					dadosGrafico.add(dado);
				}

				resultSet.close();
				preparedStatement.close();
				connectionBD.fechar();

				for (ModeloGrafico dado : dadosGrafico) {
					adicionarDado(dado);
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados!");
		}
	}

	private String getNomeMes(int numeroMes) {
		String[] nomesMeses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto",
				"Setembro", "Outubro", "Novembro", "Dezembro" };
		if (numeroMes >= 1 && numeroMes <= 12) {
			return nomesMeses[numeroMes - 1];
		} else {
			return "Mês Inválido";
		}
	}

	public void adicionarDado(ModeloGrafico dado) {
		modelo.add(dado);
		graficoEmBranco.setContagemLabel(modelo.size());
		double max = dado.getValoresMaximos();
		if (max > graficoEmBranco.getValoresMaximos()) {
			graficoEmBranco.setValoresMaximos(max);
		}
		graficoEmBranco.repaint();
	}

	public void clear() {
		animar = 0;
		graficoEmBranco.setContagemLabel(0);
		modelo.clear();
		repaint();
	}

	public void start() {
		if (!animador.isRunning()) {
			animador.start();
		}
	}

	private void initComponents() {

		graficoEmBranco = new GraficoEmBranco();
		painelLegenda = new JPanel();

		setBackground(new java.awt.Color(255, 255, 255));

		painelLegenda.setOpaque(false);
		painelLegenda.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(painelLegenda, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
								.addComponent(graficoEmBranco, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(graficoEmBranco, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
						.addGap(0, 0, 0).addComponent(painelLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
	}
}