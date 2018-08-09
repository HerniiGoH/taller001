package frsf.isi.died.app.vista.arbol;

import frsf.isi.died.app.controller.TipoArbol;

import java.lang.*;

public class Nodo {
	
	private String valor;
	private TipoArbol tipo;
	
	public Nodo(String v, TipoArbol ta) {
		this.valor = v;
		this.tipo = ta;
	}

	public String getValor() {
		return this.valor;
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
