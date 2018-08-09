package frsf.isi.died.app.vista.filtro;

import frsf.isi.died.app.controller.TipoArbol;
import frsf.isi.died.app.vista.arbol.Arbol;
import frsf.isi.died.app.vista.arbol.ArbolN;

public class Filtro {
	
	private String filtro;
	private String tipoPrincipal;
	private String tipo;
	
	public Filtro(String f, String tp, String t) {
	
		filtro = f;
		tipoPrincipal = tp;
		tipo = t;
		
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String getTipoPrincipal() {
		return tipoPrincipal;
	}

	public void setTipoPrincipal(String tipoPrincipal) {
		this.tipoPrincipal = tipoPrincipal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean filtro(Arbol arbol) {
		
		if(this.tipoPrincipal.toLowerCase().equals(TipoArbol.Metadatos.toString().toLowerCase())) {
			
			if(this.tipo.toLowerCase().equals(TipoArbol.Autor.toString().toLowerCase())) {
			
				for(Arbol aux : (arbol.hijos().get(0)).hijos()) {
				
					if(((aux).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Autor.toString().toLowerCase()))&&(aux).getRaiz().getValor().toLowerCase().equals(filtro.toLowerCase())){
					
						return true;
					
				}
				
			}
				
			}else if(this.tipo.toLowerCase().equals(TipoArbol.Editorial.toString().toLowerCase())) {
				
				for(Arbol aux : (arbol.hijos().get(0)).hijos()) {
					
					if(((aux).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Editorial.toString().toLowerCase()))&&(aux).getRaiz().getValor().toLowerCase().equals(filtro.toLowerCase())){
						
						return true;
						
					}
				}
				
			}else if(this.tipo.toLowerCase().equals(TipoArbol.Fecha_Publicacion.toString().toLowerCase())) {
				
				for(Arbol aux : (arbol.hijos().get(0)).hijos()) {
					
					if(((aux).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Fecha_Publicacion.toString().toLowerCase()))&&(aux).getRaiz().getValor().toLowerCase().equals(filtro.toLowerCase())){
						
						return true;
						
					}
				}
				
			}else if(this.tipo.toLowerCase().equals(TipoArbol.Palabras_Claves.toString().toLowerCase())) {
				
				for(Arbol aux : (arbol.hijos().get(0)).hijos()) {
					
					if(((aux).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Palabras_Claves.toString().toLowerCase()))&&(aux).getRaiz().getValor().toLowerCase().equals(filtro.toLowerCase())){
						
						return true;
						
					}
				}
				
			}
			
		}else if(this.tipoPrincipal.toLowerCase().equals(TipoArbol.Resumen.toString().toLowerCase())) {
			
			for(Arbol aux : (arbol.hijos().get(1)).hijos()) {
				
				if(((aux).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Resumen.toString().toLowerCase()))&&(aux).getRaiz().getValor().toLowerCase().equals(filtro.toLowerCase())){
					
					return true;
					
				}
			}
			
		}else if(this.tipoPrincipal.toLowerCase().equals(TipoArbol.Capitulos.toString().toLowerCase())) {
			
			if(this.tipo.toLowerCase().equals(TipoArbol.Capitulos.toString().toLowerCase())) {
				
				for(Arbol aux : arbol.hijos()) {
					
					if(((aux).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Capitulos.toString().toLowerCase()))&&(aux).getRaiz().getValor().toLowerCase().equals(filtro.toLowerCase())){
						
						return true;
						
					}
				}
			
				
				
			}else if(this.tipo.toLowerCase().equals(TipoArbol.Seccion.toString().toLowerCase())) {
				
				for(Arbol aux : arbol.hijos()) {
					
					if((aux).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Capitulos.toString().toLowerCase())){
						
						for(Arbol aux1 : (aux).hijos()) {
							
							if(((aux1).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Seccion.toString().toLowerCase()))&&(aux1).getRaiz().getValor().toLowerCase().equals(filtro.toLowerCase())){
								
								return true;
								
							}
						}
						
					}
				}
				
			}else if(this.tipo.toLowerCase().equals(TipoArbol.Parrafo.toString().toLowerCase())) {
				
				for(Arbol aux : arbol.hijos()) {
					
					if((aux).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Capitulos.toString().toLowerCase())){
						
						for(Arbol aux1 : (aux).hijos()) {
							
							if((aux1).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Seccion.toString().toLowerCase())){
								
								for(Arbol aux2 : (aux1).hijos()) {
									
									if(((aux2).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Parrafo.toString().toLowerCase()))&&(aux2).getRaiz().getValor().toLowerCase().equals(filtro.toLowerCase())){
										
										return true;
										
									}
								}
								
							}
						}
						
					}
				}
				
			}else if(this.tipo.toLowerCase().equals(TipoArbol.Palabras_Claves.toString().toLowerCase())) {
				
				for(Arbol aux : arbol.hijos()) {
					
					if((aux).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Capitulos.toString().toLowerCase())){
						
						for(Arbol aux1 : ((aux).hijos().get(0)).hijos()) {
							
							if(((aux1).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Palabras_Claves.toString().toLowerCase()))&&(aux1).getRaiz().getValor().toLowerCase().equals(filtro.toLowerCase())){
								
								return true;
								
							}
						}
						
					}
				}
				
			}else if(this.tipo.toLowerCase().equals(TipoArbol.Sitios_Web_Relacionados.toString().toLowerCase())) {
				
				for(Arbol aux : arbol.hijos()) {
					
					if((aux).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Capitulos.toString().toLowerCase())){
						
						for(Arbol aux1 : ((aux).hijos().get(0)).hijos()) {
							
							if(((aux1).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Sitios_Web_Relacionados.toString().toLowerCase()))&&(aux1).getRaiz().getValor().toLowerCase().equals(filtro.toLowerCase())){
								
								return true;
								
							}
						}
						
					}
				}
				
			}else if(this.tipo.toLowerCase().equals(TipoArbol.Sitios_Web_Ejercicios_Relacionados.toString().toLowerCase())) {
				
				for(Arbol aux : arbol.hijos()) {
					
					if((aux).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Capitulos.toString().toLowerCase())){
						
						for(Arbol aux1 : ((aux).hijos().get(0)).hijos()) {
							
							if(((aux1).getRaiz().getTipo().toString().toLowerCase().equals(TipoArbol.Sitios_Web_Ejercicios_Relacionados.toString().toLowerCase()))&&(aux1).getRaiz().getValor().toLowerCase().equals(filtro.toLowerCase())){
								
								return true;
								
							}
						}
						
					}
				}
				
			}
			
		}
		
			
			
			
		return false;
		
		
	}
	
	

}
