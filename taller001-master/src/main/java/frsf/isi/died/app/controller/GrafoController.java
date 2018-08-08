package frsf.isi.died.app.controller;

import java.awt.Color;
import java.util.ArrayList;
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
	private List<MaterialCapacitacion> materiales;

	public GrafoController(GrafoPanel panelGrf, ControlPanel panelCtrl) {
		this.vistaGrafo = panelGrf;
		this.vistaControl = panelCtrl;
		this.vistaControl.setController(this);
		this.materialDao = new MaterialCapacitacionDaoDefault();
		this.materiales = ((MaterialCapacitacionDaoDefault) materialDao).listaMateriales1();
		this.vistaControl.armarPanel(new ArrayList(materiales));
		this.vistaGrafo.setController(this);
	}

	public void crearVertice(Integer coordenadaX, Integer coordenadaY, Color color, MaterialCapacitacion mc) {
		VerticeView v = new VerticeView(coordenadaX, coordenadaY, color);
		v.setId(mc.getId());
		v.setNombre(mc.getTitulo());
		this.vistaGrafo.agregar(v);
		this.vistaGrafo.repaint();
	}

	public void crearArista(AristaView arista, boolean b) {
		this.materialDao.crearCamino(arista.getOrigen().getId(), arista.getDestino().getId());
		if(b) {
			((MaterialCapacitacionDaoDefault) this.materialDao).actualizarPR(this.materiales);
		}
		this.vistaGrafo.agregar(arista);
		this.vistaGrafo.repaint();
	}

	public void buscarCamino(Integer nodo1, Integer nodo2, Integer saltos) {
		List<MaterialCapacitacion> camino = this.materialDao.buscarCamino(nodo1, nodo2, saltos);
		this.vistaGrafo.caminoPintar(camino);
		this.vistaGrafo.repaint();
	}
	
	public List<List<MaterialCapacitacion>> buscarCamino(Integer nodo1, Integer nodo2) {
		List<List<MaterialCapacitacion>> camino = ((MaterialCapacitacionDaoDefault) materialDao).buscarCamino(nodo1, nodo2);
		List<MaterialCapacitacion>aux = camino.remove(0);
		this.vistaGrafo.caminoPintar(aux);
		this.vistaGrafo.repaint();
		camino.add(aux);
		return camino;
	}


	public List<MaterialCapacitacion> listaVertices() {
		return materiales;
	}
	
	public void dibujarAristas() {
		int id;
		VerticeView v,v1;
		v = v1 = new VerticeView();
		List<MaterialCapacitacion> mats = new ArrayList(((MaterialCapacitacionDaoDefault)materialDao).obtenerAristas());
		List<VerticeView> vertices = new ArrayList(this.vistaGrafo.getVertices());
		if(!mats.isEmpty()) {
			while(!mats.isEmpty()) {
				id = mats.remove(0).getId();
				for(VerticeView ver : vertices) {
					if(ver.getId().equals(id)) {
						v = (ver);
						break;
					}
				}
				id = mats.remove(0).getId();
				for(VerticeView ver1 : vertices) {
					if(ver1.getId().equals(id)) {
						v1 = (ver1);
						break;
					}
				}
				this.crearArista(new AristaView(v,v1),false);
			//mats.remove(0); mats.remove(0);
			}
		}
	}
	
	public GrafoPanel getPanel() {
		return this.vistaGrafo;
	}
}
