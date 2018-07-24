/**
 * 
 */
package frsf.isi.died.tp.modelo.productos;

import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof Video) {
			return (this.titulo.toLowerCase().equals(((Video) obj).titulo.toLowerCase()));
		}
		
		return false;
	}
	
	@Override
	public List<String> asCsvRow() {
		List<String> lista = new ArrayList<String>();
		lista.add(this.id+"");
		lista.add("\""+this.titulo.toString()+"\"");
		lista.add(this.costo.toString());
		lista.add(this.duracionVideo.toString());
		return lista;
	}
	

	@Override
	public void loadFromStringRow(List<String> datos) {
		this.id =Integer.valueOf(datos.get(0));
		this.titulo = datos.get(1);
		this.costo =Double.valueOf(datos.get(2));
		this.duracionVideo =Integer.valueOf(datos.get(3));
	}
}
