package frsf.isi.died.app.vista.arbol;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ArbolVacio extends Arbol {

    public ArbolVacio() {    }

    @Override
    public boolean esVacio() {
        return true;
    }

    @Override
    public boolean contiene(Nodo unValor) {
        return false;
    }

    @Override
    public boolean equals(Arbol unArbol) {
        return unArbol.esVacio();
    }

    @Override
    public Integer profundidad() {
        return 0;
    }

    @Override
    public void add(Nodo mat) {
        // no hace nada
    }

	@Override
	public List<Arbol> hijos() {
		return (new ArrayList<Arbol>());
	}
    


}