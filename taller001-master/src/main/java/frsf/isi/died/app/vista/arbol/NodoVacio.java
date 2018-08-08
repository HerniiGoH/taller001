package frsf.isi.died.app.vista.arbol;

import frsf.isi.died.app.controller.TipoArbol;

public class NodoVacio {
	
	private String valor;
	private Tipo tipo;

	public NodoVacio(Tipo ta) {
		valor = "";
		tipo = ta;
	}

	public Tipo getTipo() {
		return tipo;
	}


}
