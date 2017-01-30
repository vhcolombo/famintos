package br.com.victor.famintos.managed;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.victor.famintos.bo.impl.ProfissionalBoBean;
import br.com.victor.famintos.model.impl.Profissional;
import br.com.victor.famintos.util.FacesUtil;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
@ManagedBean
@ViewScoped
public class ProfissionalMB {
	private Profissional profissional = new Profissional();

	@EJB
	private ProfissionalBoBean profissionalBoBean;

	public ProfissionalMB() {
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public void buttonActionListener() {
		try {
			profissional = profissionalBoBean.buscaAdicionaProfissional(profissional);
		} catch (Exception e) {
			FacesUtil.showErrorMessage(e.getMessage());
		}
	}

	public String buttonAction() {
		return "stepTwo?faces-redirect=true";
	}
}
