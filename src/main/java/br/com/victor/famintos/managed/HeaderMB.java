package br.com.victor.famintos.managed;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
@ManagedBean
@SessionScoped
public class HeaderMB {
	private int stepIndex;

	public int getStepIndex() {
		return stepIndex;
	}

	public void setStepIndexByRemoteCommand() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		String indexString = map.get("index");
		stepIndex = Integer.valueOf(indexString);
	}
}
