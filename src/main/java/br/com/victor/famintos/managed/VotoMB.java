package br.com.victor.famintos.managed;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;

import br.com.victor.famintos.exception.StepsException;
import br.com.victor.famintos.model.impl.Voto;
import br.com.victor.famintos.util.FacesUtil;

import br.com.victor.famintos.ejb.impl.VotoSessionBean;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
@ManagedBean
@SessionScoped
public class VotoMB {
	private Voto voto = new Voto();

	@EJB
	private VotoSessionBean service;

	/**
	 * Retorna a lista de votos
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Voto> getVotos() throws Exception {
		return service.findAll();
	}

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}

	private BarChartModel lineModel;

	@PostConstruct
	public void init() {
	}

	public String buttonAction() {
		try {
			if (voto.getProfissional() == null) {
				throw new StepsException("Profissional não informado");
			}
			service.persist(voto);

			return "stepThree?faces-redirect=true";
		} catch (Exception e) {
			FacesUtil.showErrorMessage(e.getMessage());
		}
		return null;
	}
	
	public BarChartModel getLineModel() {
		createLineModels();
		return lineModel;
	}

	private void createLineModels() {
		lineModel = initCategoryModel();
		lineModel.setTitle("Gráfico do resultado");
		lineModel.setLegendPosition("e");
		lineModel.setShowPointLabels(true);
		lineModel.getAxes().put(AxisType.X, new CategoryAxis("Dia"));
		Axis yAxis = lineModel.getAxis(AxisType.Y);
		yAxis.setLabel("Votos");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	private BarChartModel initCategoryModel() {
		BarChartModel model = new BarChartModel();
		try {
			Map<String, Map<String, Integer>> resultado = service.getVotosMap();

			Map<String, ChartSeries> serieMap = new HashMap<String, ChartSeries>();
			for (String dia : resultado.keySet()) {
				Map<String, Integer> mapRestaurante = resultado.get(dia);
				for (String restaurante : mapRestaurante.keySet()) {
					if (!serieMap.containsKey(restaurante)) {
						serieMap.put(restaurante, new ChartSeries(restaurante));
					}
					ChartSeries serie = serieMap.get(restaurante);
					serie.set(dia, mapRestaurante.get(restaurante));
				}
			}
			for (ChartSeries serie : serieMap.values()) {
				model.addSeries(serie);
			}
		} catch (Exception e) {
			FacesUtil.showErrorMessage(e.getMessage());
		}
		return model;
	}

	/**
	 * Retorna o restaurante mais votado do dia de hoje
	 * 
	 * @return
	 */
	public String getRestauranteDoDia() {
		String retorno = null;
		try {
			retorno = service.getRestauranteDoDia(new Date());
		} catch (Exception e) {
			FacesUtil.showErrorMessage(e.getMessage());
		}

		return retorno;
	}
}
