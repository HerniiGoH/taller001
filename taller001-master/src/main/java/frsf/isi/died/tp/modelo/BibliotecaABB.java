package frsf.isi.died.tp.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import frsf.isi.died.tp.estructuras.Arbol;
import frsf.isi.died.tp.estructuras.ArbolBinarioBusqueda;
import frsf.isi.died.tp.estructuras.ArbolVacio;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class BibliotecaABB implements Biblioteca {

	private Arbol materiales;
	private Boolean flagOrdenarPorPrecio;
	private Comparator<MaterialCapacitacion> comparaTitulo= (mc1,mc2)-> mc1.getTitulo().compareTo(mc2.getTitulo());
	private Comparator<MaterialCapacitacion> comparaPrecio= (mc1,mc2)-> mc1.precio().intValue()- mc2.precio().intValue();
	private Comparator<MaterialCapacitacion> comparaCalificacion = (mc1,mc2)-> mc1.getCalificacion().intValue()- mc2.getCalificacion().intValue();
	private Comparator<MaterialCapacitacion> comparaID = (mc1,mc2)-> mc1.getId().intValue()- mc2.getId().intValue();
	private Comparator<MaterialCapacitacion> comparaFecha = (mc1,mc2)-> mc1.getFecha().compareTo(mc2.getFecha());
	private Comparator<MaterialCapacitacion> comparaRelevancia = (mc1,mc2)-> {
		if(mc1.getRelevancia().toString().equals("Alta")) {
			return 1;
		}
		else {
			if(mc2.getRelevancia().toString().equals("Alta")) {
				return -1;
			}
			else {
				if(mc1.getRelevancia().toString().equals("Media")) {
					return 1;
				}
				else {
					if(mc2.getRelevancia().toString().equals("Media")) {
						return -1;
					}
					else {
						return 1;
					}
				}
			}
		}
	};
	
	public BibliotecaABB() {
		this.materiales = new ArbolVacio();
		flagOrdenarPorPrecio= false;
	}
	
	public Comparator<MaterialCapacitacion> getCompTit(){
		return this.comparaTitulo;
	}
	public Comparator<MaterialCapacitacion> getCompID(){
		return this.comparaID;
	}
	public Comparator<MaterialCapacitacion> getCompPrecio(){
		return this.comparaPrecio;
	}
	public Comparator<MaterialCapacitacion> getCompCalif(){
		return this.comparaCalificacion;
	}
	public Comparator<MaterialCapacitacion> getCompFecha(){
		return this.comparaFecha;
	}
	public Comparator<MaterialCapacitacion> getCompRelev(){
		return this.comparaRelevancia;
	}
	
	@Override
	public void agregar(MaterialCapacitacion material) {
		if(this.materiales.esVacio()) this.materiales = new ArbolBinarioBusqueda(material, comparaTitulo);
		else{
			if(materiales.tamanio()<10)this.materiales.add(material);
		}
	}

	@Override
	public Integer cantidadMateriales() {
		return materiales.tamanio();
	}

	@Override
	public Integer cantidadLibros() {
		return materiales.tamanioLibros();
	}

	@Override
	public Integer cantidadVideos() {
		return materiales.tamanioVideos();
	}

	@Override
	public Collection<MaterialCapacitacion> materiales() {
		return this.materiales.inOrden();		
	}

	@Override
	public void imprimir() {
		materiales.imprimir();
	}

	@Override
	public void ordenarPorPrecio(Boolean b) {
		if((flagOrdenarPorPrecio && b) || (!flagOrdenarPorPrecio && !b ) ) {
			// no hago nada porque ya estaba ordenando por precio
			// y me pide que ordene por precio por lo tanto retorno
			return;
		}

		if(flagOrdenarPorPrecio && !b) {
			this.flagOrdenarPorPrecio = false;
			this.ordenarPorTitulo();
		}
		if(!flagOrdenarPorPrecio && b) {
			this.flagOrdenarPorPrecio= true;
			this.ordenarPorPrecio();
		}		
	}

	@Override
	public MaterialCapacitacion buscar(Integer precio) {
		// TODO Auto-generated method stub
		if(!flagOrdenarPorPrecio) this.ordenarPorPrecio(true);
		return this.materiales.buscar(precio);		
	}
	
	public Collection<MaterialCapacitacion> rango(Double costoMinimo,Double costoMax){
		if(!flagOrdenarPorPrecio) this.ordenarPorPrecio(true); 				
		return this.materiales.rango(costoMinimo, costoMax);
	}
	
	private void ordenarPorPrecio() {
		// Creo un nuevo arbol que ordena comparando por PRECIO.
		// Obtengo la lista del �rbol acutal.
		// Paso cada elemento de la lista al nuevo �rbol.
		// ahora el nuevo �rbol cuando lo recorra ordenado, mostrar� los 
		// datos ordenados por PRECIO la pr�xima vez que se invoque en 
		// BibliotecaABB el m�todo imprimir() o materiales()
		
		//TODO Completar
		
		List<MaterialCapacitacion> lista = new ArrayList<MaterialCapacitacion>();
		lista= this.materiales.inOrden();
		
		this.materiales = new ArbolBinarioBusqueda(lista.get(0), comparaPrecio);
		
		lista.remove(0);
		
		for(MaterialCapacitacion mat : lista) {
			this.agregar(mat);
		}
		
	}
	
	private void ordenarPorTitulo() {
		// Creo un nuevo arbol que ordena comparando por titulo.
		// Obtengo la lista del �rbol acutal.
		// Paso cada elemento de la lista al nuevo �rbol.
		// ahora el nuevo �rbol cuando lo recorra ordenado, mostrar� los 
		// datos ordenados por titulo la pr�xima vez que se invoque en 
		// BibliotecaABB el m�todo imprimir() o materiales()

		//TODO Completar
		
		List<MaterialCapacitacion> lista = new ArrayList<MaterialCapacitacion>();
		lista= this.materiales.inOrden();
		
		this.materiales = new ArbolBinarioBusqueda(lista.get(0), comparaTitulo);
		
		lista.remove(0);
		
		
		
		for(MaterialCapacitacion mat : lista) {
			this.agregar(mat);
		}
	}

}
