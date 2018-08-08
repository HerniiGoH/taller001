package frsf.isi.died.app.controller;



import java.util.List;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.arbol.Nodo;
import frsf.isi.died.app.vista.material.ArbolDatosPanel;

public class ArbolController {

	private ArbolDatosPanel arbolPanel;
	private MaterialCapacitacionDao materialDAO;
	
		
	public ArbolController(ArbolDatosPanel panel) {
		this.arbolPanel = panel;
		this.arbolPanel.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	public void crearPanel() {
		this.crearPanel(((MaterialCapacitacionDaoDefault) this.materialDAO).getTitulo());
	}
	
	public void crearPanel(String t) {		
		this.arbolPanel.construir(t);
	}

	public ArbolDatosPanel getPanelArbol() {
		return arbolPanel;
	}

	public void setPanelArbol(ArbolDatosPanel panelarbol) {
		this.arbolPanel = panelarbol;
	}
	
	public List<String> getCapitulos(String titulo){
				return materialDAO.getCapitulos(titulo);
	
	}
	public List<String> getSecciones(String titulo, String capitulo){
		return materialDAO.getSecciones(titulo, capitulo);

}

	public void addMetadato(String titulo, Nodo n) {
		materialDAO.addMetadato(titulo,n);
	}
	
	public void addResumen(String titulo, Nodo n) {
		materialDAO.addResumen(titulo,n);
	}
	
	public void addCapitulo(String titulo, Nodo n) {
		materialDAO.addCapitulo(titulo,n);
	}
	
	public void addCapSeccion(String titulo, String capitulo, Nodo n) {
		materialDAO.addCapSeccion(titulo, capitulo, n);
	}
	
	public void addCapSeccionParrafo(String titulo, String capitulo, String seccion, Nodo n) {
		materialDAO.addCapSeccionParrafo(titulo, capitulo, seccion, n);
	}
	
	public void addCapMetadato(String titulo, String capitulo, Nodo n) {
		materialDAO.addCapMetadato(titulo, capitulo, n);
	}
	
	
	
	
}
