package frsf.isi.died.app.controller;

import java.util.Date;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.VideoPanel;
import frsf.isi.died.tp.modelo.productos.Video;

public class VideoController {

	private VideoPanel panelVideo;
	private MaterialCapacitacionDao materialDAO;
	
	public VideoController(VideoPanel panel) {
		this.panelVideo = panel;
		this.panelVideo.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void agregarvideo(String titulo,Double costo, Integer duracionVideo, Date fecha, Relevancia rele) {	
		Video l = new Video(0,titulo, costo, duracionVideo, fecha, rele);
		materialDAO .agregarVideo(l);
		this.panelVideo.setListaVideos(materialDAO.listaVideos(),true);
	}
	
	public void crearPanel() {		
		this.panelVideo.setListaVideos(materialDAO.listaVideos(),false);
		this.panelVideo.construir();
	}

	public VideoPanel getPanelVideo() {
		return panelVideo;
	}

	public void setPanelvideo(VideoPanel panelvideo) {
		this.panelVideo = panelvideo;
	}
	
	
}
