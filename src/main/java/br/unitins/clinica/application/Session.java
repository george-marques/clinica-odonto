package br.unitins.clinica.application;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Session {
	
private static Session session = null;
	
	private Session() {
		// nao permitir a instancia convencional 
	}
	
	public static Session getInstance() {
		if (session == null) 
			session = new Session();
		return session;
	}
	
	private ExternalContext getExternalContext() {
		if (FacesContext.getCurrentInstance() == null) 
			throw new RuntimeException("Nao funciona o FacesContext.");
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public void set(String key, Object value) {
		getExternalContext().getSessionMap().put(key, value);
	}
	
	public Object get(String key) {
		return getExternalContext().getSessionMap().get(key);
	}
	
	public void invalidateSession() {
		getExternalContext().invalidateSession();
	}

}
