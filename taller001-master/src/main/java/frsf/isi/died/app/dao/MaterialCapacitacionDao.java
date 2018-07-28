package frsf.isi.died.app.dao;

import java.util.Date;
import java.util.List;

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
	public List<MaterialCapacitacion> findByTitulo(String titulo);
	public List<MaterialCapacitacion> findByCalif(Integer calif);
	public List<MaterialCapacitacion> findByDate(Date fechaMin, Date fechaMax);
	public List<MaterialCapacitacion> findByTema(String tema);
	public void crearCamino(Integer idOrigen, Integer idDestino);
	public List<MaterialCapacitacion> buscarCamino(Integer idOrigen, Integer idDestino, Integer saltos);
}
