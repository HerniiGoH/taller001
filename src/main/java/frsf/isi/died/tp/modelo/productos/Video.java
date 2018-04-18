/**
 * 
 */
package frsf.isi.died.tp.modelo.productos;

/**
 * @author admin
 *
 */
public class Video extends MaterialCapacitacion {
	public static Double costoPorSeg = 0.15;
	public Integer duracionVideo;
	/* (non-Javadoc)
	 * @see frsf.isi.died.tp.modelo.productos.MaterialCapacitacion#precio()
	 */
	/**
	 * Constructor por defecto
	 */
	public Video() {
		this.id = 0;
		this.titulo = "en desarrollo";
		this.costo = 0.0;
		this.duracionVideo = 0;
	}
	/**
	 * Constructor por dos parametros
	 */
	public Video(Integer id, String titulo) {
		this.id = id;
		this.titulo = titulo;
		this.costo = 0.0;
		this.duracionVideo = 0;
	}
	/**
	 * Constructor por todos los parametros
	 */
	public Video(Integer id, String titulo, Double costo, Integer duracion) {
		this.id = id;
		this.titulo = titulo;
		this.costo = costo;
		this.duracionVideo = duracion;
	}
	
	@Override
	public Double precio() {
		// TODO Auto-generated method stub
		return this.costo+(this.duracionVideo*this.costoPorSeg);
	}

	/* (non-Javadoc)
	 * @see frsf.isi.died.tp.modelo.productos.MaterialCapacitacion#esLibro()
	 */
	@Override
	public Boolean esLibro() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see frsf.isi.died.tp.modelo.productos.MaterialCapacitacion#esVideo()
	 */
	@Override
	public Boolean esVideo() {
		// TODO Auto-generated method stub
		return true;
	}

}
