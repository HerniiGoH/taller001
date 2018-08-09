package frsf.isi.died.app.vista.arbol;

import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.app.controller.TipoArbol;

public class ArbolN extends Arbol {
	
	private Nodo raiz;
	private List<Arbol> hijos = new ArrayList<Arbol>();
	

	public ArbolN(Nodo r) {
		
		raiz = r;
		
	}
	
	public ArbolN(String titulo) {
		
		Nodo n = new Nodo(titulo, TipoArbol.Titulo);
		raiz = n;
		Nodo n1 = new Nodo("Metadatos",TipoArbol.Metadatos);
		this.add(n1);
		Nodo n2 = new Nodo("Resumen", TipoArbol.Resumen);		
		this.add(n2);
		
	}


	@Override
	public boolean esVacio() {
		return false;
	}


	public Nodo getRaiz() {
		return raiz;
	}


	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}


	public List<Arbol> getHijos() {
		return hijos;
	}


	public void setHijos(List<Arbol> hijos) {
		this.hijos = hijos;
	}


	@Override
	public void add(Nodo nodo) {
		this.hijos.add(new ArbolN(nodo));
	}


	@Override
	public List<Arbol> hijos() {
		return hijos;
	}


	@Override
	public boolean contiene(Nodo unValor) {
		if(this.raiz == unValor) {
			return true;
		} else {
			for(Arbol aux : hijos) {
				aux.contiene(unValor);
			}
		}
		return false;
		
	}


	@Override
	public boolean equals(Arbol unArbol) {
		if(this.raiz == ((ArbolN)unArbol).raiz && this.hijos.equals(unArbol.hijos()))
			return true;
		else
			return false;
	}


	@Override
	public Integer profundidad() {
		Integer prof = 0; 
		for (Arbol aux : hijos) {
			prof = prof + aux.profundidad();
		}
		return prof + 1;
	}
	
	public void addMetadato(Nodo n) {
		boolean encontrado = false;
		if(!n.getTipo().equals(TipoArbol.Autor)) {
			for(Arbol aux : this.hijos.get(0).hijos()) {
				if(((ArbolN)aux).raiz.getTipo().equals(n.getTipo())) {
					encontrado = true;
					break;
				}
			}
			if(!encontrado)
				this.hijos.get(0).add(n);
		
		}else {
			this.hijos.get(0).add(n);
		}

	}
	public void addResumen(Nodo n) {
		this.hijos.get(1).add(n);
	}
	public void addCapitulo(Nodo n) {
		this.add(n);
		Nodo nodo = new Nodo("Metadato", TipoArbol.Metadatos);
		for(Arbol aux : hijos) {
			if(((ArbolN)aux).raiz.getValor().toLowerCase().equals(n.getValor().toLowerCase())){
				aux.add(nodo);
			}
		}
	}
	public void addCapSeccion (String cap, Nodo n) {
		
		for(Arbol aux : hijos) {
			if(((ArbolN)aux).getRaiz().getValor().toLowerCase().equals(cap.toLowerCase())) {
				((ArbolN)aux).add(n);
			}
		}
		
	}
	public void addCapSeccionParrafo(String cap,String sec,Nodo n) {
		
		for(Arbol aux : hijos) {
			if(((ArbolN)aux).raiz.getValor().toLowerCase().equals(cap.toLowerCase())) {
				
				for(Arbol aux1 : ((ArbolN)aux).hijos)
					if(((ArbolN)aux1).raiz.getValor().toLowerCase().equals(sec.toLowerCase())) {
					
						((ArbolN)aux1).add(n);
						break;
					
				}
			}
		}
	}
	public void addCapMetadatos (String cap, Nodo n) {
		
		boolean encontrado = false;
		
		for(Arbol aux : hijos) {
			if(((ArbolN)aux).raiz.getValor().toLowerCase().equals(cap.toLowerCase())) {
				if(n.getTipo().equals(TipoArbol.Palabras_Claves)) {
					for(Arbol aux1 : ((ArbolN)aux).hijos.get(0).hijos())
					if(((ArbolN)aux1).raiz.getTipo().equals(TipoArbol.Palabras_Claves)) {
						encontrado = true;
						break;
					}
				}
					if(!encontrado)
						((ArbolN)aux).hijos.get(0).add(n);
			}else {
				((ArbolN)aux).hijos.get(0).add(n);
			}
		}
	}
}
	
