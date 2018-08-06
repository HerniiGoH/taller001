package frsf.isi.died.app.controller;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.grafo.AristaView;
import frsf.isi.died.app.vista.grafo.ControlPanel;
import frsf.isi.died.app.vista.grafo.GrafoPanel;
import frsf.isi.died.app.vista.grafo.VerticeView;
import frsf.isi.died.tp.estructuras.Arista;
import frsf.isi.died.tp.estructuras.Grafo;
import frsf.isi.died.tp.estructuras.Vertice;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class GrafoController {

	private GrafoPanel vistaGrafo;
	private ControlPanel vistaControl;
	private MaterialCapacitacionDao materialDao;

	public GrafoController(GrafoPanel panelGrf, ControlPanel panelCtrl) {
		this.vistaGrafo = panelGrf;
		this.vistaControl = panelCtrl;
		this.vistaControl.setController(this);
		this.materialDao = new MaterialCapacitacionDaoDefault();
		this.vistaControl.armarPanel(((MaterialCapacitacionDaoDefault) materialDao).listaMateriales1());
		this.vistaGrafo.setController(this);
	}

	public void crearVertice(Integer coordenadaX, Integer coordenadaY, Color color, MaterialCapacitacion mc) {
		VerticeView v = new VerticeView(coordenadaX, coordenadaY, color);
		v.setId(mc.getId());
		v.setNombre(mc.getTitulo());
		this.vistaGrafo.agregar(v);
		this.vistaGrafo.repaint();
	}

	public void crearArista(AristaView arista) {
		this.materialDao.crearCamino(arista.getOrigen().getId(), arista.getDestino().getId());
		this.vistaGrafo.agregar(arista);
		this.vistaGrafo.repaint();
	}

	public void buscarCamino(Integer nodo1, Integer nodo2, Integer saltos) {
		List<MaterialCapacitacion> camino = this.materialDao.buscarCamino(nodo1, nodo2, saltos);
		this.vistaGrafo.caminoPintar(camino);
		this.vistaGrafo.repaint();
	}


	public List<MaterialCapacitacion> listaVertices() {
		return ((MaterialCapacitacionDaoDefault)materialDao).listaMateriales1();
	}
	
	public void dibujarAristas() {
		int id;
		AristaView aux = new AristaView();
		List<MaterialCapacitacion> mats = ((MaterialCapacitacionDaoDefault)materialDao).obtenerAristas();
		List<VerticeView> vertices = this.vistaGrafo.getVertices();
		while(!mats.isEmpty()) {
			id = mats.get(0).getId();
			for(VerticeView ver : vertices) {
				if(ver.getId().equals(id)) {
					aux.setOrigen(ver);
					//break;
				}
			}
			id = mats.get(1).getId();
			for(VerticeView ver : vertices) {
				if(ver.getId().equals(id)) {
					aux.setDestino(ver);
					//break;
				}
			}
			this.crearArista(aux);
			mats.remove(0); mats.remove(0);
		}
	}
}
