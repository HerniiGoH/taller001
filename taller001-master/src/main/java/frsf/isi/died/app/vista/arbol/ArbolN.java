package frsf.isi.died.app.vista.arbol;

import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.app.controller.TipoArbol;

public class ArbolN extends Arbol {
	
	private Nodo raiz;
	private List<Arbol> hijos = new ArrayList<Arbol>();
	

	public ArbolN(Nodo r) {
		
		raiz = r;
		
		switch((TipoArbol)r.getTipo()) {
		case Titulo:{
			Nodo n1 = new Nodo("Metadatos",TipoArbol.Metadatos);
			this.add(n1);
			Nodo n2 = new Nodo("Resumen", TipoArbol.Resumen);		
			this.add(n2);
			break;
		}
		case Capitulos:{
			Nodo n1 = new Nodo("Metadatos",TipoArbol.Metadatos);
			this.add(n1);
			break;
		}
		default: break;
		}
		
		
		
	}
	
	public ArbolN(String titulo) {
		
		
		
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
	
	public List<Arbol> Hijos() {
		return hijos;
	}


	public void setHijos(List<Arbol> hijos) {
		this.hijos = hijos;
	}


	@Override
	public void add(Nodo nodo) {
		this.hijos.add(new ArbolVacio(nodo));
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
				return aux.contiene(unValor);
			}
		}
		return false;
		
	}


	@Override
	public boolean equals(Arbol unArbol) {
		if(!unArbol.esVacio() && this.raiz.getValor().toLowerCase().equals(unArbol.getRaiz().getValor().toLowerCase()) && this.hijos.equals(unArbol.hijos()))
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
				if((aux).getRaiz().getTipo().equals(n.getTipo())) {
					encontrado = true;
					break;
				}
			}
			if(!encontrado) {
				if(this.hijos.get(0).esVacio()) {
					hijos.add(0, new ArbolN(hijos.remove(0).getRaiz()));
					this.hijos.get(0).add(n);
				}
				else{
					this.hijos.get(0).add(n);
				}
			}
		}else {
			if(this.hijos.get(0).esVacio()) {
				hijos.add(0, new ArbolN(hijos.remove(0).getRaiz()));
				this.hijos.get(0).add(n);
			}
			else{
				this.hijos.get(0).add(n);
			}
		}

	}
	
	public void addResumen(Nodo n) {
		if(this.hijos.get(1).esVacio()) {
			hijos.add(1, new ArbolN(hijos.remove(1).getRaiz()));
			this.hijos.get(1).add(n);
		}
		else{
			this.hijos.get(1).add(n);
		}
	}
	
	public void addCapitulo(Nodo n) {
		this.add(n);
		/*Nodo nodo = new Nodo("Metadato", TipoArbol.Metadatos);
		for(Arbol aux : hijos) {
			if(((ArbolN)aux).raiz.getValor().toLowerCase().equals(n.getValor().toLowerCase())){
				aux.add(nodo);
			}
		}*/
	}
	public void addCapSeccion (String cap, Nodo n) {
		
		for(int i=0; i<hijos.size(); i++) {
			if((hijos.get(i)).getRaiz().getValor().toLowerCase().equals(cap.toLowerCase())) {
				if(hijos.get(i).esVacio()) {
					hijos.add(i, new ArbolN(hijos.remove(i).getRaiz()));
					hijos.get(i).add(n);
				}
				else {
					hijos.get(i).add(n);
				}
				//System.out.println(hijos.get(i).hijos().get(1).getRaiz().getValor());
			}
		}
		
	}
	
	public void addCapSeccionParrafo(String cap,String sec,Nodo n) {
		
		for(Arbol aux : hijos) {
			if((aux).getRaiz().getValor().toLowerCase().equals(cap.toLowerCase())) {
				
				for(int i=0; i<aux.hijos().size(); i++) {
					if((aux.hijos().get(i)).getRaiz().getValor().toLowerCase().equals(sec.toLowerCase())) {
					
						if(aux.hijos().get(i).esVacio()) {
							aux.hijos().add(i, new ArbolN(aux.hijos().remove(i).getRaiz()));
							aux.hijos().get(i).add(n);
						}
						else {
							aux.hijos().get(i).add(n);
						}
						
						break;
					}
				}
			}
		}
	}
	
	public void addCapMetadatos (String cap, Nodo n) {
		
		boolean encontrado = false;
		
		for(Arbol aux : hijos) {
			if((aux).getRaiz().getValor().toLowerCase().equals(cap.toLowerCase())) {
				if(n.getTipo().equals(TipoArbol.Palabras_Claves)) {
					for(Arbol aux1 : (aux).hijos().get(0).hijos())
					if((aux1).getRaiz().getTipo().equals(TipoArbol.Palabras_Claves)) {
						encontrado = true;
						break;
					}
				}
				if(!encontrado) {
					if((aux).hijos().get(0).esVacio()) {
						(aux).hijos().add(0, new ArbolN((aux).hijos().remove(0).getRaiz()));
						(aux).hijos().get(0).add(n);
					}
					else{
						(aux).hijos().get(0).add(n);
					}
					(aux).hijos().get(0).add(n);
				}
			}
		}
	}
	
}
	
