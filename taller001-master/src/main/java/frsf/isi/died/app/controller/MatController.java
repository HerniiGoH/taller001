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

	
	public List<Video> busqTitulo1(String titulo, Ordenamiento crit) {
		List<Video> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByTitulo(titulo);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esVideo()) {
					res.add((Video) mat);
				}
			}
		}	
		return this.ordenar1(res,crit);
	}
	public List<Video> busqCalif1(Integer calificacion, Ordenamiento crit) {	
		List<Video> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByCalif(calificacion);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esVideo()) {
					res.add((Video) mat);
				}
			}
		}
		return this.ordenar1(res,crit);
	}
	public List<Video> busqTema1(String tema, Ordenamiento crit) {
		List<Video> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByTema(tema);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esVideo()) {
					res.add((Video) mat);
				}
			}
		}
		return this.ordenar1(res,crit);
	}
	public List<Video> busqFecha1(Date min, Date max, Ordenamiento crit) {
		List<Video> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByDate(min, max);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esVideo()) {
					res.add((Video) mat);
				}
			}
		}
		return this.ordenar1(res,crit);
	}
	public List<Video> busqID1(Integer Id, Ordenamiento crit) {
		List<Video> res = new ArrayList();
		List<MaterialCapacitacion> res1 = materialDAO.findById1(Id);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esVideo()) {
					res.add((Video) mat);
				}
			}
		}
		return this.ordenar1(res,crit);
	}
	
	public List<Libro> busqTitulo(String titulo, Ordenamiento crit) {
		List<Libro> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByTitulo(titulo);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esLibro()) {
					res.add((Libro) mat);
				}
			}
		}
		return this.ordenar(res,crit);
	}
	public List<Libro> busqCalif(Integer calificacion, Ordenamiento crit) {	
		List<Libro> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByCalif(calificacion);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esLibro()) {
					res.add((Libro) mat);
				}
			}
		}
		return this.ordenar(res,crit);
	}
	public List<Libro> busqTema(String tema, Ordenamiento crit) {
		List<Libro> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByTema(tema);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esLibro()) {
					res.add((Libro) mat);
				}
			}
		}
		return this.ordenar(res,crit);
	}
	public List<Libro> busqFecha(Date min, Date max, Ordenamiento crit) {
		List<Libro> res = new ArrayList();
		List<MaterialCapacitacion> res1 = this.materialDAO.findByDate(min, max);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esLibro()) {
					res.add((Libro) mat);
				}
			}
		}
		return this.ordenar(res,crit);
	}
	public List<Libro> busqID(Integer Id, Ordenamiento crit) {
		List<Libro> res = new ArrayList();
		List<MaterialCapacitacion> res1 = materialDAO.findById1(Id);
		if(!res1.isEmpty()) {
			for(MaterialCapacitacion mat : res1) {
				if(mat.esLibro()) {
					res.add((Libro) mat);
				}
			}
		}
		return this.ordenar(res,crit);
	}
	
	private List<Libro> ordenar(List<Libro> lis, Ordenamiento criterio){
		List<Libro> res = new ArrayList();
		switch(criterio) {
		case TituloAlfabeticamente: 
			return ((MaterialCapacitacionDaoDefault) materialDAO).ordenPorTitulo(lis);
			
		case Calificacion: 
			return ((MaterialCapacitacionDaoDefault) materialDAO).ordenPorCalificacion(lis);
			
		case Precio: 
			return ((MaterialCapacitacionDaoDefault) materialDAO).ordenPorPrecio(lis);
			
		case FechaDePublicacion: 
			return ((MaterialCapacitacionDaoDefault) materialDAO).ordenPorFecha(lis);
			
		case Relevancia: 
			return ((MaterialCapacitacionDaoDefault) materialDAO).ordenPorRelevancia(lis);
			
		default: break;
		};
		return res;
	}
	
	private List<Video> ordenar1(List<Video> lis, Ordenamiento criterio){
		List<Video> res = new ArrayList();
		switch(criterio) {
		case TituloAlfabeticamente: 
			return ((MaterialCapacitacionDaoDefault) materialDAO).ordenPorTitulo1(lis);
			
		case Calificacion: 
			return ((MaterialCapacitacionDaoDefault) materialDAO).ordenPorCalificacion1(lis);
			
		case Precio: 
			return ((MaterialCapacitacionDaoDefault) materialDAO).ordenPorPrecio1(lis);
			
		case FechaDePublicacion: 
			return ((MaterialCapacitacionDaoDefault) materialDAO).ordenPorFecha1(lis);
			
		case Relevancia: 
			return ((MaterialCapacitacionDaoDefault) materialDAO).ordenPorRelevancia1(lis);
			
		default: break;
		};
		return res;
	}
	
	public void eliminar(Libro mat) {
		((MaterialCapacitacionDaoDefault) materialDAO).eliminar(mat);
		//this.panelMat.setListaLibros(materialDAO.listaLibros(), false);
	}
	
	public void eliminar1(Video mat) {
		((MaterialCapacitacionDaoDefault) materialDAO).eliminar1(mat);
		//this.panelMat.setListaVideos(materialDAO.listaVideos(), false);
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
	
	public void editarLibro(Libro viejo, Libro nuevo) {
		((MaterialCapacitacionDaoDefault) materialDAO).actualizar(viejo, nuevo);
	}
	
	public void editarVideo(Video viejo, Video nuevo) {
		((MaterialCapacitacionDaoDefault) materialDAO).actualizar1(viejo, nuevo);
	}
	
	public void addWishlist(Libro lib) throws Exception {
		((MaterialCapacitacionDaoDefault) materialDAO).addWishlist(lib);
	}
	
	public void addWishlist1(Video vid) throws Exception {
		((MaterialCapacitacionDaoDefault) materialDAO).addWishlist1(vid);
	}
	
	
}
