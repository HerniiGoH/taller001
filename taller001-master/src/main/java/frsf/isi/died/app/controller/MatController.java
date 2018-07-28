package frsf.isi.died.app.controller;

import java.util.Date;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.MatPanel;

public class MatController {

	private MatPanel panelMat;
	private MaterialCapacitacionDao materialDAO;
	
	public MatController(MatPanel panel) {
		this.panelMat = panel;
		this.panelMat.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void busqTitulo(String titulo) {	
		
	}
	public void busqCalif(Integer calificacion) {	
	
	}
	public void busqTema(String tema) {	
	
	}
	public void busqFecha(Date min, Date max) {	
	
	}
	
	public void crearPanel() {		
		this.panelMat.setListaLibros(materialDAO.listaLibros(),false);
		this.panelMat.setListaVideos(materialDAO.listaVideos(),false);
		this.panelMat.construir();
	}

	public MatPanel getPanelMat() {
		return panelMat;
	}

	public void setPanelmat(MatPanel panelmat) {
		this.panelMat = panelmat;
	}
	
	
}
