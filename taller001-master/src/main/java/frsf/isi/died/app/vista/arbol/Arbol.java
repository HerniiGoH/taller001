package frsf.isi.died.app.vista.arbol;

import java.util.List;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import java.util.Comparator;

public abstract class Arbol {

	public abstract boolean esVacio();

	public abstract void add(Nodo nodo);

	public abstract List<Arbol> hijos();
	
	public abstract boolean contiene(Nodo unValor);

	public abstract boolean equals(Arbol unArbol);

	public abstract Integer profundidad();

	public Nodo getRaiz() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addMetadato(Nodo n) {
		// TODO Auto-generated method stub
		
	}

	public void addResumen(Nodo n) {
		// TODO Auto-generated method stub
		
	}

	public void addCapitulo(Nodo n) {
		// TODO Auto-generated method stub
		
	}

	public void addCapSeccion(String capitulo, Nodo n) {
		// TODO Auto-generated method stub
		
	}

	public void addCapSeccionParrafo(String capitulo, String seccion, Nodo n) {
		// TODO Auto-generated method stub
		
	}

	public void addCapMetadatos(String capitulo, Nodo n) {
		// TODO Auto-generated method stub
		
	}
}