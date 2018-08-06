package frsf.isi.died.app.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import frsf.isi.died.app.dao.util.CsvDatasource;
import frsf.isi.died.app.dao.util.CsvRecord;
import frsf.isi.died.tp.estructuras.Arista;
import frsf.isi.died.tp.estructuras.Grafo;
import frsf.isi.died.tp.modelo.Biblioteca;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public class MaterialCapacitacionDaoDefault implements MaterialCapacitacionDao{

	private static Grafo<MaterialCapacitacion> GRAFO_MATERIAL  = new Grafo<MaterialCapacitacion>();
	private static Integer SECUENCIA_ID=0;
	private static Biblioteca biblioteca = new BibliotecaABB();
	private static Queue<Libro> wishlistLibro = new PriorityQueue(MaterialCapacitacion.comparador);
	private static Queue<Video> wishlistVideo = new PriorityQueue(MaterialCapacitacion.comparador);
	private static String Tema;
	
	private CsvDatasource dataSource;
	
	public MaterialCapacitacionDaoDefault() {
		dataSource = new CsvDatasource();
		if(GRAFO_MATERIAL.esVacio()) {
			cargarGrafo();
		}
		List<MaterialCapacitacion> aux = GRAFO_MATERIAL.listaVertices();
		if(!aux.isEmpty()) {
			Collections.sort(aux,((BibliotecaABB)biblioteca).getCompID());
			SECUENCIA_ID = aux.get(aux.size()-1).getId();
			}
	}
	
	public Queue<Libro> getWishlistLibros(){
		return this.wishlistLibro;
	}
	public Queue<Video> getWishlistVideos(){
		return this.wishlistVideo;
	}

	private void cargarGrafo() {
		
		List<List<String>> libros = dataSource.readFile("libros.csv");
		for(List<String> filaLibro : libros) {
			Libro aux = new Libro();
			aux.loadFromStringRow(filaLibro);
			GRAFO_MATERIAL.addNodo(aux);
		}
		List<List<String>> videos= dataSource.readFile("videos.csv");
		for(List<String> filaVideo: videos) {
			Video aux = new Video();
			aux.loadFromStringRow(filaVideo);
			GRAFO_MATERIAL.addNodo(aux);
		}
		List<List<String>> aristas= dataSource.readFile("aristas.csv");
		for(List<String> filaArista: aristas) {
			MaterialCapacitacion n1 = this.findById(Integer.valueOf(filaArista.get(0)));
			MaterialCapacitacion n2 = this.findById(Integer.valueOf(filaArista.get(2)));
			GRAFO_MATERIAL.conectar(n1, n2);
		}
		
		libros = dataSource.readFile("wishlistLibros.csv");
		for(List<String> filaLibro : libros) {
			Libro aux = new Libro();
			aux.loadFromStringRow(filaLibro);
			this.wishlistLibro.add(aux);
		}
		videos = dataSource.readFile("wishlistVideos.csv");
		for(List<String> filaVideo : videos) {
			Video aux = new Video();
			aux.loadFromStringRow(filaVideo);
			this.wishlistVideo.add(aux);
		}
 	}
	
	@Override
	public void agregarLibro(Libro mat) {
		mat.setId(++SECUENCIA_ID);
		GRAFO_MATERIAL.addNodo(mat);	
		biblioteca.agregar(mat);
		try {
			dataSource.agregarFilaAlFinal("libros.csv", mat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void agregarVideo(Video mat) {
		mat.setId(++SECUENCIA_ID);
		GRAFO_MATERIAL.addNodo(mat);				
		biblioteca.agregar(mat);
		try {
			dataSource.agregarFilaAlFinal("videos.csv", mat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Libro> listaLibros() {
		List<Libro> libros = new ArrayList<>();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.esLibro()) libros.add((Libro)mat); 
		}
		return libros;
	}

	@Override
	public List<Video> listaVideos() {
		List<Video> vids = new ArrayList<>();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.esVideo()) vids.add((Video)mat); 
		}
		return vids;
	}

	@Override
	public List<MaterialCapacitacion> listaMateriales() {
		// TODO Auto-generated method stub
		return GRAFO_MATERIAL.listaVertices();
	}
	
	public List<MaterialCapacitacion> listaMateriales1(){
		List<MaterialCapacitacion>aux = new ArrayList();
		for(MaterialCapacitacion mat : this.listaMateriales()) {
			if(mat.getTema().toLowerCase().equals(Tema.toLowerCase())) {
				aux.add(mat);
			}
		}
		return aux;
	}

	@Override
	public MaterialCapacitacion findById(Integer id) {
		// TODO Auto-generated method stub
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.getId().equals(id)) return mat;
		}
		return null;
	}
	public List<MaterialCapacitacion> findById1(Integer id) {
		// TODO Auto-generated method stub
		List<MaterialCapacitacion> res = new ArrayList();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.getId().equals(id)) res.add(mat);
		}
		return res;
	}
	
	@Override
	public List<MaterialCapacitacion> findByTitulo(String titulo) {
		List<MaterialCapacitacion> res = new ArrayList();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.getTitulo().toLowerCase().contains(titulo.toLowerCase())) res.add(mat);
		}
		return res;
	}
	
	@Override
	public List<MaterialCapacitacion> findByTema(String tema) {
		List<MaterialCapacitacion> res = new ArrayList();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.getTema().toLowerCase().contains(tema.toLowerCase())) res.add(mat);
		}
		return res;
	}
	
	@Override
	public List<MaterialCapacitacion> findByCalif(Integer calif) {
		List<MaterialCapacitacion> res = new ArrayList();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.getCalificacion().equals(calif)) res.add(mat);
		}
		return res;
	}
	
	@Override
	public List<MaterialCapacitacion> findByDate(Date fechaMin, Date fechaMax) {
		List<MaterialCapacitacion> res = new ArrayList();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.getFecha().after(fechaMin) && mat.getFecha().before(fechaMax)) res.add(mat);
		}
		return res;
	}

	@Override
	public List<MaterialCapacitacion> buscarCamino(Integer idOrigen, Integer idDestino, Integer saltos) {
		MaterialCapacitacion n1 = this.findById(idOrigen);
		MaterialCapacitacion n2 = this.findById(idDestino);
		return GRAFO_MATERIAL.buscarCaminoNSaltos(n1, n2, saltos);
	}

	@Override
	public void crearCamino(Integer idOrigen, Integer idDestino) {
		MaterialCapacitacion n1 = this.findById(idOrigen);
		MaterialCapacitacion n2 = this.findById(idDestino);
		if(GRAFO_MATERIAL.conectar(n1, n2)) {
			List<String> fila = new ArrayList<>();
			fila.add(n1.getId()+"");
			fila.add(n1.getTitulo());
			fila.add(n2.getId()+"");
			fila.add(n2.getTitulo());
			try {
				dataSource.agregarFilaAlFinal("aristas.csv", fila);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Libro> ordenPorTitulo(List<Libro>lista){
		 Collections.sort(lista, ((BibliotecaABB) biblioteca).getCompTit());
		 return lista;
	}
	public List<Libro> ordenPorCalificacion(List<Libro>lista){
		 Collections.sort(lista, ((BibliotecaABB) biblioteca).getCompCalif());
		 return lista;
	}
	public List<Libro> ordenPorPrecio(List<Libro>lista){
		 Collections.sort(lista, ((BibliotecaABB) biblioteca).getCompPrecio());
		 return lista;
	}
	public List<Libro> ordenPorFecha(List<Libro>lista){
		 Collections.sort(lista, ((BibliotecaABB) biblioteca).getCompFecha());
		 return lista;
	}
	public List<Libro> ordenPorRelevancia(List<Libro>lista){
		 Collections.sort(lista, ((BibliotecaABB) biblioteca).getCompRelev());
		 return lista;
	}
	
	public List<Video> ordenPorTitulo1(List<Video>lista){
		 Collections.sort(lista, ((BibliotecaABB) biblioteca).getCompTit());
		 return lista;
	}
	public List<Video> ordenPorCalificacion1(List<Video>lista){
		 Collections.sort(lista, ((BibliotecaABB) biblioteca).getCompCalif());
		 return lista;
	}
	public List<Video> ordenPorPrecio1(List<Video>lista){
		 Collections.sort(lista, ((BibliotecaABB) biblioteca).getCompPrecio());
		 return lista;
	}
	public List<Video> ordenPorFecha1(List<Video>lista){
		 Collections.sort(lista, ((BibliotecaABB) biblioteca).getCompFecha());
		 return lista;
	}
	public List<Video> ordenPorRelevancia1(List<Video>lista){
		 Collections.sort(lista, ((BibliotecaABB) biblioteca).getCompRelev());
		 return lista;
	}
	
	public void eliminar(Libro mat) {
		this.GRAFO_MATERIAL.borrarMat(mat);
		List<Libro> aux = this.listaLibros();
		try {
			this.dataSource.agregarFilaAlPrincipio("libros.csv", aux.remove(0));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(Libro lib : aux) {
			try {
				this.dataSource.agregarFilaAlFinal("libros.csv", lib);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(this.wishlistLibro.contains(mat)) {
			List<Libro> aux1 = new ArrayList(this.wishlistLibro);
			this.wishlistLibro=new PriorityQueue();
			aux1.remove(mat);
			for(Libro lib : aux1) {
				try {
					this.addWishlist(lib);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void eliminar1(Video mat) {
		this.GRAFO_MATERIAL.borrarMat(mat);
		List<Video> aux = this.listaVideos();
		try {
			this.dataSource.agregarFilaAlPrincipio("videos.csv", aux.remove(0));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(Video lib : aux) {
			try {
				this.dataSource.agregarFilaAlFinal("videos.csv", lib);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(this.wishlistVideo.contains(mat)) {
			List<Video> aux1 = new ArrayList(this.wishlistVideo);
			this.wishlistVideo=new PriorityQueue();
			aux1.remove(mat);
			for(Video vid : aux1) {
				try {
					this.addWishlist1(vid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void actualizar(Libro viejo, Libro nuevo) {
		this.GRAFO_MATERIAL.actualizar(viejo, nuevo);
		List<Libro> aux = this.listaLibros();
		try {
			this.dataSource.agregarFilaAlPrincipio("libros.csv", aux.remove(0));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(Libro lib : aux) {
			try {
				this.dataSource.agregarFilaAlFinal("libros.csv", lib);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(this.wishlistLibro.contains(viejo)) {
			List<Libro> aux1 = new ArrayList(this.wishlistLibro);
			this.wishlistLibro=new PriorityQueue();
			aux1.remove(viejo);
			aux1.add(nuevo);
			for(Libro lib : aux1) {
				try {
					this.addWishlist(lib);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void actualizar1(Video viejo, Video nuevo) {
		this.GRAFO_MATERIAL.actualizar(viejo, nuevo);
		List<Video> aux = this.listaVideos();
		try {
			this.dataSource.agregarFilaAlPrincipio("videos.csv", aux.remove(0));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(Video lib : aux) {
			try {
				this.dataSource.agregarFilaAlFinal("videos.csv", lib);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(this.wishlistVideo.contains(viejo)) {
			List<Video> aux1 = new ArrayList(this.wishlistVideo);
			this.wishlistVideo=new PriorityQueue();
			aux1.remove(viejo);
			aux.add(nuevo);
			for(Video vid : aux1) {
				try {
					this.addWishlist1(vid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void addWishlist(Libro libr) throws Exception {
		
		if(!this.wishlistLibro.contains(libr)) {
			
			this.wishlistLibro.add(libr);
			Queue<Libro> aux = new PriorityQueue(this.wishlistLibro);
		
			try {
				this.dataSource.agregarFilaAlPrincipio("wishlistLibros.csv", aux.poll());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while(!aux.isEmpty()) {
				try {
					this.dataSource.agregarFilaAlFinal("wishlistLibros.csv", aux.poll());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			throw new Exception("Material ya se encuentra en la wishlist");
		}
	}
	public void addWishlist1(Video vide) throws Exception {
		if(!this.wishlistVideo.contains(vide)) {
			
			this.wishlistVideo.add(vide);
			Queue<Video> aux = new PriorityQueue(this.wishlistVideo);
	
			try {
				this.dataSource.agregarFilaAlPrincipio("wishlistVideos.csv", aux.poll());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while(!aux.isEmpty()) {
				try {
					this.dataSource.agregarFilaAlFinal("wishlistVideos.csv", aux.poll());
				} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			throw new Exception("Material ya se encuentra en la wishlist");
		}
	}
	
	public void setTema(String tema) {
		this.Tema = tema;
	}
	
	public List<MaterialCapacitacion> obtenerAristas(){ 
		List<MaterialCapacitacion> aux2 = new ArrayList();
		for(MaterialCapacitacion mat : this.listaMateriales1()) {
			for(MaterialCapacitacion mat1 : this.GRAFO_MATERIAL.getAdyacentes(mat)) {
				aux2.add(mat); aux2.add(mat1);
			}
		}
		return aux2;
	}
	
}
