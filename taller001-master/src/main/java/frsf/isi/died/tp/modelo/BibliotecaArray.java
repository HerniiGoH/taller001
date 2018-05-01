package frsf.isi.died.tp.modelo;

import java.util.Arrays;
import java.util.Collection;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.util.ListaServiceRadix;
import frsf.isi.died.tp.util.ListasService;


/**
 * Esta clase implementa la interface Biblioteca y todas sus operaciones, gestionando todos los
 * @see MaterialCapacitacion en un arreglo.
 * @author mdominguez
 *
 */
public class BibliotecaArray implements Biblioteca{

	/**
	 * Arreglo donde se almacenan todos los materiales de capacitacion
	 */
	private MaterialCapacitacion[] materialCapacitacion;
	/**
	 * Variable interna de control que se utiliza para llevar registro de cuantos materiales 
	 * se llevan insertados. Se utiliza para evitar insertar un nuevo material fuera de rango
	 * y tambien para responder la consulta acerca de cuantos materiales hay insertados
	 */
	private Integer cantidadMaterial;
	
	public ListasService listaService;
	
	// TODO 12: crear una variable de tipo ListaService que apuntará a una instancia del servicio de operaciones de lista
	
	
	public BibliotecaArray() {
		cantidadMaterial=0;
		this.materialCapacitacion= new MaterialCapacitacion[10];
		// TODO 13: inicializar la variable de tipo ListaService para que apunte el servicio de operaciones de listas
		listaService = new ListaServiceRadix(this.materialCapacitacion);
	}

	@Override
	public void agregar(MaterialCapacitacion material) {
		// TODO 06: se agrega un material al arreglo de materiales de capacitacion si hay espacio en el arreglo
		// caso contrario el metodo no agrega ningun elemento y termina su ejecución
		if(this.materialCapacitacion[this.materialCapacitacion.length-1] == null) {
			int i = 0;
			while(this.materialCapacitacion[i]!=null) {
				i++;
			}
			this.materialCapacitacion[i]=material;	
		}
		
	}

	@Override
	public Integer cantidadMateriales() {
		Integer i = 0;
		while(this.materialCapacitacion[i]!=null) {
			i++;
		}
		return i;
	}



	@Override
	public Integer cantidadLibros() {
		Integer i = 0; int cont = 0;
		while(this.materialCapacitacion[i]!=null) {
			if(this.materialCapacitacion[i].esLibro()) {
				cont++;
			}
			i++;
		}
		return cont;
	}



	@Override
	public Integer cantidadVideos() {
		Integer i = 0; int cont = 0;
		while(this.materialCapacitacion[i]!=null) {
			if(this.materialCapacitacion[i].esVideo()) {
				cont++;
			}
			i++;
		}
		return cont;
	}

	@Override
	public void imprimir() {		
		this.listaService.imprimir(); 
	}
	
	public void ordenar() {		
		this.listaService.ordenar();
	}
		

	@Override
	public void ordenarPorPrecio(Boolean b) {
		this.listaService.ordenar(); 
	}


	@Override
	public Collection<MaterialCapacitacion> materiales() {
		return Arrays.asList(this.materialCapacitacion);
	}
	
	@Override
	public MaterialCapacitacion buscar(Integer precio) {
	// TODO Auto-generated method stub
	return null;
	}



}
