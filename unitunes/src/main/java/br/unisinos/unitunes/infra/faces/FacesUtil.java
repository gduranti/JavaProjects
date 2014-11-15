package br.unisinos.unitunes.infra.faces;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public final class FacesUtil {

	public static void addInfoMessage(String message) {
		addMessage(message, FacesMessage.SEVERITY_INFO);
	}

	public static void addWarnMessage(String message) {
		addMessage(message, FacesMessage.SEVERITY_WARN);
	}
	
	public static void addErrorMessage(String message) {
		addMessage(message, FacesMessage.SEVERITY_ERROR);
	}
	
	private static void addMessage(String message, Severity severity) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(severity, message, null));
	}
	
}
