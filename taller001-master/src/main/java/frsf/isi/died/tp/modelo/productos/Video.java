/**
 * 
 */
package frsf.isi.died.tp.modelo.productos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import frsf.isi.died.app.controller.Relevancia;

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
	public Video(Integer id, String titulo, Double costo, Integer duracion, Date fecha) {
		super(id,titulo,costo,fecha);
		this.duracionVideo = duracion;
	}
	
	public Video(Integer id, String titulo, Double costo, Integer duracion, Date fecha,Relevancia rele) {
		super(id,titulo,costo,fecha,rele);
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
		lista.add(this.titulo.toString());
		lista.add(this.costo.toString());
		lista.add(this.duracionVideo.toString());
		lista.add(this.fechaPublic.toString());
		lista.add(this.calificacion.toString());
		lista.add(this.cantCalif.toString());
		lista.add(this.relevancia.toString());
		return lista;
	}
	

	@Override
	public void loadFromStringRow(List<String> datos) {
		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		this.id =Integer.valueOf(datos.get(0));
		this.titulo = datos.get(1);
		this.costo =Double.valueOf(datos.get(2));
		this.duracionVideo =Integer.valueOf(datos.get(3));
		try {
			this.fechaPublic = formatter.parse(datos.get(4));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.calificacion = Integer.valueOf(datos.get(5));
		this.cantCalif = Integer.valueOf(datos.get(6));
		this.relevancia = Relevancia.valueOf(datos.get(7));
	}
}
