package frsf.isi.died.app.dao;

import java.util.Date;
import java.util.List;

import frsf.isi.died.app.vista.arbol.Arbol;
import frsf.isi.died.app.vista.arbol.Nodo;
import frsf.isi.died.app.vista.filtro.Filtro;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public interface MaterialCapacitacionDao {

	public void agregarLibro(Libro mat);
	public void agregarVideo(Video mat);
	public List<Libro> listaLibros();
	public List<Video> listaVideos();
	public List<MaterialCapacitacion> listaMateriales();
	public MaterialCapacitacion findById(Integer id);
	public List<MaterialCapacitacion> findById1(Integer id);
	public List<MaterialCapacitacion> findByTitulo(String titulo);
	public List<MaterialCapacitacion> findByCalif(Integer calif);
	public List<MaterialCapacitacion> findByDate(Date fechaMin, Date fechaMax);
	public List<MaterialCapacitacion> findByTema(String tema);
	public void crearCamino(Integer idOrigen, Integer idDestino);
	public List<MaterialCapacitacion> buscarCamino(Integer idOrigen, Integer idDestino, Integer saltos);
	public List<String> getCapitulos(String titulo);
	public List<String> getSecciones(String titulo, String capitulo);
	public void addMetadato(String titulo, Nodo n);
	public void addResumen(String titulo, Nodo n);
	public void addCapitulo(String titulo, Nodo n);
	public void addCapSeccion(String titulo, String capitulo, Nodo n);
	public void addCapSeccionParrafo(String titulo, String capitulo, String seccion, Nodo n);
	public void addCapMetadato(String titulo, String capitulo, Nodo n);
	public List<Arbol> filtrar(List<Filtro> filtrar);
}
