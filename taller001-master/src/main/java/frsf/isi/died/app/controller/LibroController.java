package frsf.isi.died.app.controller;

import java.util.Date;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.tp.modelo.productos.Libro;

public class LibroController {

	private LibroPanel panelLibro;
	private MaterialCapacitacionDao materialDAO;
	
	public LibroController(LibroPanel panel) {
		this.panelLibro = panel;
		this.panelLibro.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void agregarLibro(String titulo,Double costo,Double precio,Integer paginas, Date fecha, Relevancia rele) {	
		Libro l = new Libro(0,titulo, costo, precio, paginas,fecha,rele) ;
		materialDAO .agregarLibro(l);
		this.panelLibro.setListaLibros(materialDAO.listaLibros(),true);
	}
	
	public void crearPanel() {		
		this.panelLibro.setListaLibros(materialDAO.listaLibros(),false);
		this.panelLibro.construir();
	}

	public LibroPanel getPanelLibro() {
		return panelLibro;
	}

	public void setPanelLibro(LibroPanel panelLibro) {
		this.panelLibro = panelLibro;
	}
	
	
}
