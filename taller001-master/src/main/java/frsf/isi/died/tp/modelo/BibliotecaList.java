/**
 * 
 */
package frsf.isi.died.tp.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
//import java.lang.RuntimeException;

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
			Collections.sort(materiales,(m1, m2) -> (m1.precio().compareTo(m2.precio())));
		}
		else {
			Collections.sort(materiales);
		}
		
		
	}
	
	@Override
	public MaterialCapacitacion buscar(Integer precio) {
	Collections.sort(materiales ,(m1, m2) -> (m2.getCosto().compareTo(m1.getCosto())));
	
	
	//((MaterialCapacitacion)m1).getCosto().compareTo(((MaterialCapacitacion)m2).getCosto())
	//Integer.((MaterialCapacitacion)m1).getCosto().intValue(), (MaterialCapacitacion)m1).getCosto().intValue());
	
	return buscadorBinario(0, materiales.size()-1, precio);
	}
	
	private MaterialCapacitacion buscadorBinario(Integer i,Integer f, Integer c)throws RuntimeException{
		
		
		if(f>=i) {
		
			Integer centro = ((f+i)/2);
		
			if((materiales.get(centro).getCosto().intValue())==c) {
				return materiales.get(centro);
			}
			else {
				if((materiales.get(centro).getCosto().intValue())>c) {
					return buscadorBinario(i, centro-1, c);
				}
				else {
					return buscadorBinario(centro+1, f, c);
				}
			}
		}
		else {
			throw new RuntimeException("Material de precio " + c + " no encontrado");
		}
		
		
		
	}

}
