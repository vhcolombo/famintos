package br.com.victor.famintos.util;

import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe de Configuração e utilidades do faces.
 * 
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
public class FacesUtil {

	/**
	 * Retorna parametro passado por request.
	 * 
	 * @param name
	 * @return
	 */
	public static String getRequestParameter(String name) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}

	/**
	 * Envia mensgaem de sucesso para a tela.
	 * 
	 * @param message
	 */
	public static void showSuccessMessage(String message) {
		showMessage(FacesMessage.SEVERITY_INFO, message);
	}

	/**
	 * Envia mensgaem de sucesso para a tela.
	 * 
	 * @param id
	 * @param message
	 */
	public static void showSuccessMessage(String id, String message) {
		showMessage(id, FacesMessage.SEVERITY_INFO, message);
	}

	/**
	 * Envia mensgaem de warning para a tela.
	 * 
	 * @param message
	 */
	public static void showAlertMessage(String message) {
		showMessage(FacesMessage.SEVERITY_WARN, message);
	}

	/**
	 * Envia mensgaem de warning para a tela.
	 * 
	 * @param id
	 * @param message
	 */
	public static void showAlertMessage(String id, String message) {
		showMessage(id, FacesMessage.SEVERITY_WARN, message);
	}

	/**
	 * @param message
	 * @param obj
	 */
	public static void showAlertMessage(String message, String... obj) {
		message = convertBundle(message, obj);
		showMessage(FacesMessage.SEVERITY_WARN, message);
	}

	/**
	 * Envia mensgaem de erro para a tela.
	 * 
	 * @param message
	 */
	public static void showErrorMessage(String message) {
		showMessage(FacesMessage.SEVERITY_ERROR, message);
	}

	/**
	 * Envia mensgaem de erro para a tela.
	 * 
	 * @param id
	 * @param message
	 */
	public static void showErrorMessage(String id, String message) {
		showMessage(id, FacesMessage.SEVERITY_ERROR, message);
	}

	/**
	 * @param message
	 * @param obj
	 */
	public static void showErrorMessage(String id, String message, String obj) {
		message = convertBundle(message, obj);
		showMessage(id, FacesMessage.SEVERITY_ERROR, message);
	}

	/**
	 * Método privado que envia as mensagens para a tela.
	 * 
	 * @param id
	 * @param severity
	 * @param message
	 */
	private static void showMessage(String id, FacesMessage.Severity severity, String message) {
		FacesMessage facesMessage = new FacesMessage(severity, "", message);
		FacesContext.getCurrentInstance().addMessage(id, facesMessage);
	}

	/**
	 * Método que retorna o contexto externo do faces.
	 * 
	 * @return
	 */
	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	/**
	 * Método que retorna o mapa de sessï¿½o do faces.
	 * 
	 * @return
	 */
	public static Map<String, Object> getSessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	/**
	 * Método que retorna o <code>HttpServletRequest</code> de servlet do faces.
	 * 
	 * @return
	 */
	public static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	/**
	 * Método que retorna o <code>HttpServletResponse</code> de servlet do faces.
	 * 
	 * @return
	 */
	public static HttpServletResponse getServletResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	/**
	 * Método que retorna o bundle a ser usado.
	 * 
	 * @param bundleName
	 * @param key
	 * @return
	 */
	public static String getBundleKey(String bundleName, String key) {
		return FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(),
			bundleName).getString(key);
	}

	/**
	 * Busca a mensagem correspondente ao bundle no arquivo de propriedades.
	 * 
	 * @param messageKey
	 * @return
	 */
	public static String getMessage(String messageKey) {
		ResourceBundle i18n = getBundle(FacesContext.getCurrentInstance().getViewRoot().getLocale());
		try {
			return i18n.getString(messageKey);
		} catch (MissingResourceException mre) {
			return "???" + messageKey + "???";
		}
	}

	/**
	 * Busca a mensagem correspondente ao bundle no arquivo de propriedades.
	 * 
	 * @param messageKey
	 * @return
	 */
	public static String getMessage(String messageKey, String... obj) {
		ResourceBundle i18n = getBundle(FacesContext.getCurrentInstance().getViewRoot().getLocale());
		try {
			String message = convertBundle(i18n.getString(messageKey), obj);
			return message;
		} catch (MissingResourceException mre) {
			return "???" + messageKey + "???";
		}
	}

	/**
	 * Retorna o locale para usar o bundle.
	 * 
	 * @param locale
	 * @return
	 */
	private static ResourceBundle getBundle(Locale locale) {
		return ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle(), locale);
	}

	/**
	 * Método privado que envia as mensagens para a tela.
	 * 
	 * @param severity
	 * @param message
	 */
	private static void showMessage(FacesMessage.Severity severity, String message) {
		showMessage("messages", severity, message);
	}

	/**
	 * @param msg
	 * @param obj
	 * @return
	 */
	private final static String convertBundle(String msg, String... obj) {
		if (obj != null && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				msg = msg.replace("{" + i + "}", obj[i]);
			}
		}
		return msg;
	}
}