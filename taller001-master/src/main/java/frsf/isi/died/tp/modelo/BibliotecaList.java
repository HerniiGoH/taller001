/**
 * 
 */
package frsf.isi.died.tp.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.util.ListasService;

/**
 * @author Guillermo
 *
 */
public class BibliotecaList implements Biblioteca {

	/**
	 * 
	 */
	
	private ArrayList <MaterialCapacitacion> materiales;
	
	public BibliotecaList() {
		this.materiales = new ArrayList<>();
	}

	@Override
	public void agregar(MaterialCapacitacion material) {
		materiales.add(material);
	}

	@Override
	public Integer cantidadMateriales() {
		return materiales.size();
	}

	@Override
	public Integer cantidadLibros() {
		int cont=0;
		for(MaterialCapacitacion mat : materiales) {
			if(mat.esLibro()) {
				cont++;
			}
		}
		
		return cont;
	}

	@Override
	public Integer cantidadVideos() {
		int cont=0;
		for(MaterialCapacitacion mat : materiales) {
			if(mat.esVideo()) {
				cont++;
			}
		}
		
		return cont;
	}

	@Override
	public Collection<MaterialCapacitacion> materiales() {
		return this.materiales;
	}

	@Override
	public void imprimir() {
		
		for(MaterialCapacitacion mat : materiales) {
			System.out.print(mat.toString());
			}
		}
		

	@Override
	public void ordenarPorPrecio(Boolean b) {
		
		if(b) {
			Collections.sort(materiales,(m1, m2) -> ((MaterialCapacitacion)m1).precio().compareTo(((MaterialCapacitacion)m2).precio()));
		}
		else {
			Collections.sort(materiales);
		}
		
		
	}

}
