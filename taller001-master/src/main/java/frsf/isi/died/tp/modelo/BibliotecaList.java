/**
 * 
 */
package frsf.isi.died.tp.modelo;

import java.util.ArrayList;
import java.util.Collection;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void imprimir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ordenarPorPrecio(Boolean b) {
		// TODO Auto-generated method stub
		
	}

}
