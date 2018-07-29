/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.text.ParseException;
import java.util.*;

import frsf.isi.died.app.controller.Relevancia;
import frsf.isi.died.app.dao.util.CsvRecord;
import frsf.isi.died.tp.util.Ordenable;

/**
 * Representa de manera abstracta los materiales de capacitación
 * 
 * @author Albornoz Hernan, Moyano Guillermo, Tomas Emanuel 
 * https://github.com/HerniiGoH/taller01
 */
public abstract class MaterialCapacitacion implements Ordenable, Comparable, CsvRecord {
	protected Integer id;
	protected Integer calificacion;
	protected Integer cantCalif;
	protected Date fechaPublic;
	protected Relevancia relevancia;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public final Double valor() {
	
		return this.precio();
		
		/*funciona porque el m�todo est� implementado en los hijos de MaterialCapacitacion*/
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	
	public Integer getCalificacion() {
		return this.calificacion/this.cantCalif;
	}
	
	public Relevancia getRelevancia() {
		return this.relevancia;
	}
	
	public Date getFecha() {
		return this.fechaPublic;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the costo
	 */
	public Double getCosto() {
		return costo;
	}

	/**
	 * @param costo the costo to set
	 */
	public void setCosto(Double costo) {
		this.costo = costo;
	}

	/**
	 * Titulo del material
	 */
	protected String titulo;

	/**
	 * Costo básico que debe sumarse al precio por el mero hecho de publicarlo en el
	 * portal
	 */
	protected Double costo;
	

	/**
	 * Constructor por defecto
	 */
	public MaterialCapacitacion() {
		this(0,"en desarrollo",0.0,new Date());
	}

	/**
	 * Constructor que recibe como argumento un ID y un Titulo
	 * 
	 * @param id
	 * @param titulo
	 */
	public MaterialCapacitacion(Integer id, String titulo) {
		this(id,titulo,0.0, new Date());
	}

	/**
	 * Constructor que recibe como argumento un ID y un costo
	 * 
	 * @param id
	 * @param titulo
	 */
	public MaterialCapacitacion(Integer id,String titulo, Double costo, Date fecha) {
		this.id =id;
		this.titulo = titulo;
		this.costo = costo;
		this.calificacion=1;
		this.cantCalif=1;
		this.fechaPublic = fecha;
	}
	
	public MaterialCapacitacion(Integer id,String titulo, Double costo, Date fecha, Relevancia rele) {
		this.id =id;
		this.titulo = titulo;
		this.costo = costo;
		this.calificacion=1;
		this.cantCalif=1;
		this.fechaPublic = fecha;
		this.relevancia=rele;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialCapacitacion other = (MaterialCapacitacion) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	
	//TODO 01 implementar los metodos getters y setters y escribir el javadoc
	// AYUDA: para implementar estos metodos usar un atajo del IDE 
	// elegir el menu "Source" --> "Generate getters y setters" y elegir alli que metodos generar.
	

	/**
	 * El precio de un material se define según el tipo del material y toma como
	 * base el costo del mismo
	 * 
	 * @return
	 */
	public abstract Double precio();
	
	/**
	 * Retorna verdadero si es una instancia de libro, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esLibro();

	/**
	 * Retorna verdadero si es una instancia de video, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esVideo();
	
	@Override
	public String toString() {
		
		String str = "Titulo: "+this.titulo+" ; Precio: "+this.precio();
		
		return str;
	}
	
	@Override
	public int compareTo(Object obj) {
		
		if(this.equals(obj)) {
			return this.precio().compareTo(((MaterialCapacitacion)obj).precio());
		}
		
		return this.titulo.compareTo(((MaterialCapacitacion)obj).titulo);
	}
	//TODO 02: sobrescribir el metodo toString de la clase "Object"
	//	el método toString retorna un string que representa el material actual
	//  retornando el titulo, y el precio 	 * usando el formato : 
	// [Titulo: <titulo> ; Precio: <precio> ]

	public void loadFromStringRow(List<String> datos) throws ParseException {
		// TODO Auto-generated method stub
		
	}

	public List<String> asCsvRow() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// TODO 10: implementar Ordenable

}
