package frsf.isi.died.app.controller;



import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.arbol.Arbol;
import frsf.isi.died.app.vista.arbol.Nodo;
import frsf.isi.died.app.vista.filtro.Filtro;
import frsf.isi.died.app.vista.material.BusquedaPanel;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.app.vista.material.BusquedaPanel;

public class BusquedaController {

	private BusquedaPanel docPanel;
	private MaterialCapacitacionDao materialDAO;
	List<Filtro> filtros = new ArrayList();
	List<Arbol> arboles = new ArrayList();
		
	public BusquedaController(BusquedaPanel panel) {
		this.docPanel = panel;
		this.docPanel.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void crearPanel() {		
		this.docPanel.construir();
	}

	public BusquedaPanel getPanelBusqueda() {
		return docPanel;
	}

	public void setPanelArbol(BusquedaPanel paneldoc) {
		this.docPanel = paneldoc;
	}
	
	public void addFiltro(Filtro f) {
		filtros.add(f);
	}
	
	
	
	public List<MaterialCapacitacion> filtrar() {
		
		List<Arbol> arbol = (materialDAO).filtrar(filtros);
		
		List<MaterialCapacitacion> resultado = new ArrayList();
		
		for(Arbol a : arbol) {
			resultado.addAll(materialDAO.findByTitulo(a.getRaiz().getValor()));
		}
		
		return resultado;
	}
	
	
	
}
