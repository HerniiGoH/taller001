package frsf.isi.died.app.vista.arbol;

import frsf.isi.died.app.controller.TipoArbol;

public class Nodo {
	
	private String valor;
	private TipoArbol tipo;
	
	public Nodo(TipoArbol ta) {
		tipo = ta;
	}
	
	public Nodo(String v, TipoArbol ta) {
		valor = v;
		tipo = ta;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public TipoArbol getTipo() {
		return tipo;
	}

	public void setTipo(TipoArbol tipo) {
		this.tipo = tipo;
	}

}
