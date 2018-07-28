package frsf.isi.died.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.MatPanel;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public class MatController {

	private MatPanel panelMat;
	private MaterialCapacitacionDao materialDAO;
	
	public MatController(MatPanel panel) {
		this.panelMat = panel;
		this.panelMat.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public List<Video> busqTitulo1(String titulo) {
		List<Video> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByTitulo(titulo);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esVideo()) {
					res.add((Video) mat);
				}
			}
		}
		return res;
	}
	public List<Video> busqCalif1(Integer calificacion) {	
		List<Video> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByCalif(calificacion);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esVideo()) {
					res.add((Video) mat);
				}
			}
		}
		return res;
	}
	public List<Video> busqTema1(String tema) {
		List<Video> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByTema(tema);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esVideo()) {
					res.add((Video) mat);
				}
			}
		}
		return res;
	}
	public List<Video> busqFecha1(Date min, Date max) {
		List<Video> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByDate(min, max);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esVideo()) {
					res.add((Video) mat);
				}
			}
		}
		return res;
	}
	public List<Video> busqID1(Integer Id) {
		List<Video> res = new ArrayList();
		List<MaterialCapacitacion> res1 = materialDAO.findById1(Id);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esVideo()) {
					res.add((Video) mat);
				}
			}
		}
		return res;
	}
	
	public List<Libro> busqTitulo(String titulo) {
		List<Libro> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByTitulo(titulo);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esLibro()) {
					res.add((Libro) mat);
				}
			}
		}
		return res;
	}
	public List<Libro> busqCalif(Integer calificacion) {	
		List<Libro> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByCalif(calificacion);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esLibro()) {
					res.add((Libro) mat);
				}
			}
		}
		return res;
	}
	public List<Libro> busqTema(String tema) {
		List<Libro> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByTema(tema);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esLibro()) {
					res.add((Libro) mat);
				}
			}
		}
		return res;
	}
	public List<Libro> busqFecha(Date min, Date max) {
		List<Libro> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByDate(min, max);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esLibro()) {
					res.add((Libro) mat);
				}
			}
		}
		return res;
	}
	public List<Libro> busqID(Integer Id) {
		List<Libro> res = new ArrayList();
		List<MaterialCapacitacion> res1 = materialDAO.findById1(Id);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esLibro()) {
					res.add((Libro) mat);
				}
			}
		}
		return res;
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
