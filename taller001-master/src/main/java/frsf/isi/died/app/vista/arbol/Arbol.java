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
}