package frsf.isi.died.app.controller;

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

	
	/*public void buscarLibro(String titulo,Double costo, Integer duracionMat) {	
		Mat l = new Mat(0,titulo, costo, duracionMat);
		materialDAO .agregarMat(l);
		this.panelMat.setListaMats(materialDAO.listaMateriales(),true);
	}*/
	
	public void crearPanel() {		
		//this.panelMat.setListaMats(materialDAO.listaMateriales(),false);
		this.panelMat.construir();
	}

	public MatPanel getPanelMat() {
		return panelMat;
	}

	public void setPanelmat(MatPanel panelmat) {
		this.panelMat = panelmat;
	}
	
	
}
