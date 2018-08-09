package frsf.isi.died.app.vista;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import frsf.isi.died.app.controller.MenuController;
import frsf.isi.died.app.controller.TiposAcciones;

public class Principal {

	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
		        public void run() {
		          createAndShowGUI();
		        }
		    });
	}
	
	private static void createAndShowGUI() {
		JFrame f = new JFrame();
		 MenuController controller = new MenuController(f);
				    
	        JMenuBar menuBar;
	        JMenu menu;
	        JMenuItem menuItem;
	        JPopupMenu popup;

	        menuBar = new JMenuBar();

	        menu = new JMenu("Sistema");
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Nuevo Libro");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ABM_LIBROS));
	        menu.add(menuItem);

	        menuItem = new JMenuItem("Nuevo Video");
	        menu.add(menuItem);
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.ABM_VIDEOS));
	        
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Buscar Material");
	        menu.add(menuItem);
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.BSQ_MAT));
	      
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Wishlist");
	        menu.add(menuItem);
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.VER_WISH));
	      
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Buscar Documento");
	        menu.add(menuItem);
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.VER_DOC));
	      
	        menu.addSeparator();
	        
	        menuItem = new JMenuItem("Salir");
	        menuItem.addActionListener(e->System.exit(99));
	        menu.add(menuItem);

	        menuBar.add(menu);
	        
	        /*menu = new JMenu("Opciones");
	        menuBar.add(menu);
	        
	        menuItem = new JMenuItem("Buscar Camino");
	        menuItem.addActionListener(e -> controller.showView(TiposAcciones.VER_GRAFO));
	        menu.add(menuItem);
	        menuBar.add(menu);*/
	        
	        f.setJMenuBar(menuBar);
	        
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setExtendedState(Frame.MAXIMIZED_BOTH);
	        f.pack();
	        f.setVisible(true);
	}

}
