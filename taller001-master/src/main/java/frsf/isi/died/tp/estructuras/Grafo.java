/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.estructuras;
import java.util.*;

/**
 *
 * @author mdominguez
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class Grafo<T> {

	protected List<Arista<T>> aristas;
	protected List<Vertice<T>> vertices;

	/**
	 * 
	 */
	public Grafo(){
		this.aristas = new ArrayList<Arista<T>>();
		this.vertices = new ArrayList<Vertice<T>>();
	}

	/**
	 * @param nodo
	 */
	public void addNodo(T nodo){
		this.addNodo(new Vertice<T>(nodo));
	}

	/**
	 * @param nodo
	 */
	public void addNodo(Vertice<T> nodo){
		this.vertices.add(nodo);
	}
	
	/**
	 * @param n1
	 * @param n2
	 */
	public void conectar(T n1,T n2){
		this.conectar(getNodo(n1), getNodo(n2), 0.0);
	}

        /**
	 * @param nodo1
	 * @param nodo2
	 */
	public Arista<T> conectar(Vertice<T> nodo1,Vertice<T> nodo2){
            Arista<T> arista = new Arista<T>(nodo1,nodo2,0.0);
            this.aristas.add(arista);
            return arista;
	}
        
	/**
	 * @param n1
	 * @param n2
	 * @param valor
	 */
	public void conectar(T n1,T n2,Number valor){
		this.conectar(getNodo(n1), getNodo(n2), valor);
	}

	/**
	 * @param nodo1
	 * @param nodo2
	 * @param valor
	 */
	public void conectar(Vertice<T> nodo1,Vertice<T> nodo2,Number valor){
		this.aristas.add(new Arista<T>(nodo1,nodo2,valor));
	}
	
	/**
	 * @param valor
	 * @return
	 */
	public Vertice<T> getNodo(T valor){
		return this.vertices.get(this.vertices.indexOf(new Vertice<T>(valor)));
	}

	/**
	 * @param valor
	 * @return
	 */
	public List<T> getAdyacentes(T valor){ 
		Vertice<T> unNodo = this.getNodo(valor);
		List<T> salida = new ArrayList<T>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin().getValor());
			}
		}
		return salida;
	}
	

	/**
	 * @param unNodo
	 * @return
	 */
	protected List<Vertice<T>> getAdyacentes(Vertice<T> unNodo){ 
		List<Vertice<T>> salida = new ArrayList<Vertice<T>>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin());
			}
		}
		return salida;
	}
                
	/**
	 * 
	 */
	public void imprimirAristas(){
		System.out.println(this.aristas.toString());
	}

	public void borrarMat(MaterialCapacitacion mat) {
		for(Vertice v : vertices) {
			if(v.getValor().equals(mat)) {
				vertices.remove(v);
				break;
			}
		}
	}
	
	public void actualizar(MaterialCapacitacion viejo, MaterialCapacitacion nuevo) {
		for(Vertice v : vertices) {
			if(v.getValor().equals(viejo)) {
				v.setValor(nuevo);
				break;
			}
		}
	}
        
	/**
	 * @param v1
	 * @param v2
	 * @return
	 */
	protected boolean esAdyacente(Vertice<T> v1,Vertice<T> v2){
            List<Vertice<T>> ady = this.getAdyacentes(v1);
            for(Vertice<T> unAdy : ady){
                if(unAdy.equals(v2)) return true;
            }
            return false;
    }
        
    public Boolean esVacio(){
    	if(this.vertices!= null && !this.vertices.isEmpty()) return false;
    	return true;
    }
    
    public List<T> listaVertices(){
    	List<T> lista = new ArrayList<>();
    	this.vertices.forEach(v -> lista.add(v.getValor()));
    	return lista;
    }

		/**
	 * @param vertice
	 * @return
	 */
	public Integer gradoEntrada(T v){
		Vertice<T> vertice = this.getNodo(v);
		Integer res =0;
		for(Arista a : aristas) {
			if(a.getFin().equals(vertice)) {
				res++;
			}
		}
		//TODO
		return res;
	}

	/**
	 * @param vertice
	 * @return
	 */
	public Integer gradoSalida(T v){
		Vertice<T> vertice = this.getNodo(v);
		Integer res =0;
		for(Arista a : aristas) {
			if(a.getInicio().equals(vertice)) {
				res++;
			}
		}
		//TODO
		return res;
	}

    public T primerVerticeGradoK(T v,Integer gradoK) {
    	
    	HashSet<Vertice> visitados= new HashSet<>();
    	Queue<Vertice> visitados2 = new LinkedList();
    	
    	T resultado=null;
    	
    	visitados2.add(this.getNodo(v));
    	visitados.add(this.getNodo(v)); boolean encontrado=false;
    	
    	while(visitados2.isEmpty()==false && encontrado==false) {
    		Vertice u = visitados2.poll();
    		for(Arista<T> a : aristas) {
    			if(a.getInicio().equals(u)) {
    				if(this.gradoSalida(a.getFin().getValor())== gradoK) {
    					resultado = a.getFin().getValor();
    					encontrado=true;
    					break;
    				}
    				else {
    					if(visitados.contains(a.getFin())==false) {
    						visitados.add(a.getFin());
    						visitados2.add(a.getFin());
    					}
    				}
    			}
    		}
    	}
    	
		return resultado;
    }


    
    public boolean existeCamino(T v) {
    	
		//Vertice<T> vertice = this.getNodo(v);
		
    	return this.gradoSalida(v)>0;
    	
    }
    
    
    /**
     * @param n1
     * @param n2
     * @param valor
     */
    public List<T> buscarCaminoNSaltos(T n1,T n2,Integer saltos){
		Vertice<T> origen = this.getNodo(n1);
		Vertice<T> destino= this.getNodo(n2);
        return this.buscarCaminoNSaltos(origen, destino, saltos, new HashSet<Vertice>());
         
    }
    private List<T> buscarCaminoNSaltos(Vertice<T> n1,Vertice<T> n2,Integer saltos,HashSet<Vertice> visitados){
    	
        ArrayList<T> resultado = new ArrayList<>();
        Vector<T> visitados2 = new Vector();
        
        if(n1.equals(n2) && saltos.intValue()==0) {
        	resultado.add(n1.getValor());
        }
        else {
        	if(n1.equals(n2) || saltos.intValue()==0) {
        		visitados.clear();
        	}
        	else {
        		visitados2.addAll((ArrayList)(this.getAdyacentes(n1.getValor())));
        		saltos--;
        		for(T v : visitados2) {
        			if(visitados.contains(this.getNodo(v))==false) {
        				visitados.add(this.getNodo(v));
        				List<T> resultado_aux = this.buscarCaminoNSaltos(this.getNodo(v), n2, saltos,visitados);
        				if(resultado_aux.contains(n2.getValor()) && resultado_aux.get(resultado_aux.size()-1).equals(n2.getValor())) {
        					resultado.add(n1.getValor()); resultado.addAll(resultado_aux); break;
        				}
        			}
        		}
        	}
        }
                       
        return resultado;
    }


}