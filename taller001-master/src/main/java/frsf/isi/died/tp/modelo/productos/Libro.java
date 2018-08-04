/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import frsf.isi.died.app.controller.Relevancia;

/**
 * Representa un libro en el sistema de biblioteca digital
 * @author mdominguez
 */
public class Libro extends MaterialCapacitacion {
	/**
	 * Precio al que el libro se compro a la editorial que lo vende
	 */
	private Double precioCompra;
	/**
	 * Cantidad de paginas del libro
	 */
	private Integer paginas;

	/**
	 * Constructor por defecto del Libro
	 */
	public Libro() {
	}

	/**
	 * permite crerar un libro solo con su titulo e identificador
	 * @param id es el identificador del libro
	 * @param titulo es el titulo del libro
	 */
	public Libro(Integer id, String titulo) {
		this(id, titulo,0.0,0.0,0,new Date());
	}

	/**
	 * permite crear un libro con todos sus parametros
	 * @param id es el identificador del libro
	 * @param titulo es el titulo del libro
	 * @param costo es el costo de ofrecerlo online
	 * @param precioCompra es el precio al que se adquirió el libro a la editorial
	 * @param paginas cantidad de paginas del libro
	 */
	public Libro(Integer id, String titulo, Double costo, Double precioCompra, Integer paginas, Date fecha) {
		super(id, titulo, costo, fecha);
		this.precioCompra = precioCompra;
		this.paginas = paginas;
	}
	
	public Libro(Integer id, String titulo, Double costo, Double precioCompra, Integer paginas, Date fecha,Relevancia rele, String tema) {
		super(id, titulo, costo, fecha,rele);
		this.precioCompra = precioCompra;
		this.paginas = paginas;
		this.tema = tema;
	}

	/**
	 * retorna el precio al que el libro fue comprado
	 * @return precio al que el libro fue comprado
	 */
	public Double getPrecioCompra() {
		return precioCompra;
	}

	/**
	 * asigna el precio de compra del libro
	 * @param precioCompra
	 */
	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	/**
	 * retorna la cantidad de paginas del libro
	 * @return cantidad de paginas
	 */
	public Integer getPaginas() {
		return paginas;
	}

	/**
	 * asigna la cantidad de paginas del libro
	 * @param paginas
	 */
	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	@Override
	public Boolean esLibro() {
		return true;
	}

	@Override
	public Boolean esVideo() {
		return false;
	}

	/**
	 * El precio de un libro se calcula segun la siguiente formula
	 * PRECIO = costo + (precio de compra * FACTOR_PAGINAS)
	 * FACTOR_PAGINAS es un 3% cada 150 paginas.
	 * 
	 * Por ejemplo, para un libro cuyo costo de publicacion es de $50 y cuyo precio de compra
	 * es de $100 si tiene 460 paginas se calculará su precio final como
	 * 
	 * PRECIO = 50.0 + (100.0 * (1.0+ (0.03 * 460/150))); 
	 * PRECIO = 50.0 + (100.0 * (1.0 + 0.09)) = 50.0 + 109.0 = 159.0
	 */
	@Override
	public Double precio() {
		return this.getCosto()+(this.precioCompra*(1+((this.paginas/150)*0.03)));
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof Libro) {
			return (this.titulo.toLowerCase().equals(((Libro) obj).titulo.toLowerCase()));
		}
		
		return false;
	}
	
	@Override
	public List<String> asCsvRow() {
		List<String> lista = new ArrayList<String>();
		lista.add(this.id+"");
		lista.add(this.tema.toString());
		lista.add(this.costo.toString());
		lista.add(this.paginas.toString());
		lista.add(this.precioCompra.toString());
		lista.add(this.fechaPublic.toString());
		lista.add(this.calificacion.toString());
		lista.add(this.cantCalif.toString());
		lista.add(this.relevancia.toString());
		lista.add(this.titulo.toString());
		return lista;
	}

	@Override
	public void loadFromStringRow(List<String> datos) {
		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		this.id =Integer.valueOf(datos.get(0));
		this.titulo = datos.get(1);
		this.costo =Double.valueOf(datos.get(2));
		this.paginas =Integer.valueOf(datos.get(3));
		this.precioCompra =Double.valueOf(datos.get(4));
		try {
			this.fechaPublic = formatter.parse(datos.get(5));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.calificacion = Integer.valueOf(datos.get(6));
		this.cantCalif = Integer.valueOf(datos.get(7));
		this.relevancia = Relevancia.valueOf(datos.get(8));
		
	}
	

}