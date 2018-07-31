package frsf.isi.died.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.MatPanel;
import frsf.isi.died.app.vista.material.WishPanel;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public class WishController {

	private WishPanel panelWish;
	private MaterialCapacitacionDao materialDAO;
	
	public WishController(WishPanel panel) {
		this.panelWish = panel;
		this.panelWish.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanel() {
		
		Queue<Libro> libs = new PriorityQueue(((MaterialCapacitacionDaoDefault) materialDAO).getWishlistLibros());
		
		List<Libro> libros = new ArrayList();
		
		while(!libs.isEmpty())  {
			libros.add(libs.poll());
		}
		
		Queue<Video> vids = new PriorityQueue(((MaterialCapacitacionDaoDefault) materialDAO).getWishlistVideos());
		
		List<Video> videos = new ArrayList();
		
		while(!vids.isEmpty()) {
			videos.add(vids.poll());
		}
		
		this.panelWish.setListaLibros(libros,false);
		
		this.panelWish.setListaVideos(videos,false);
		
		this.panelWish.construir();
	}

	public WishPanel getPanelWish() {
		return panelWish;
	}

	public void setPanelmat(WishPanel panelwish) {
		this.panelWish = panelwish;
	}
	
	
	/*public void addWishlist(Libro lib) throws Exception {
		((MaterialCapacitacionDaoDefault) materialDAO).addWishlist(lib);
	}
	
	public void addWishlist1(Video vid) throws Exception {
		((MaterialCapacitacionDaoDefault) materialDAO).addWishlist1(vid);
	}*/
	
	
}
