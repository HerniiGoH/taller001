package frsf.isi.died.app;

import java.util.Date;

import frsf.isi.died.tp.modelo.productos.Libro;

public class Paso04App {
	public static void main(String[] args) {
		// crear un libro
		Libro l1 = new Libro(1, "Algoritmos 1", 50.0, 25.0, 310,new Date());
		System.out.println("Libro creado: " + l1.toString());
		// crear un libro
		Libro l2 = new Libro(2, "Java 1", 30.0, 40.0, 155,new Date());
		System.out.println("Libro creado: " + l2.toString());
		// crear un libro
		Libro l3 = new Libro(3, "Python", 20.0, 100.0, 460,new Date());
		System.out.println("Libro creado: " + l3.toString());
	}
}
