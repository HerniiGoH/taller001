package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.videoPanel;
import frsf.isi.died.tp.modelo.productos.video;

public class VideoController {

	private videoPanel panelVideo;
	private MaterialCapacitacionDao materialDAO;
	
	public videoController(videoPanel panel) {
		this.panelVideo = panel;
		this.panelVideo.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void agregarvideo(String titulo,Double costo, Integer duracionVideo) {	
		Video l = new Video(0,titulo, costo, duracionVideo);
		materialDAO .agregarvideo(l);
		this.panelVideo.setListavideos(materialDAO.listavideos(),true);
	}
	
	public void crearPanel() {		
		this.panelVideo.setListavideos(materialDAO.listavideos(),false);
		this.panelVideo.construir();
	}

	public videoPanel getPanelvideo() {
		return panelVideo;
	}

	public void setPanelvideo(videoPanel panelvideo) {
		this.panelVideo = panelvideo;
	}
	
	
}
