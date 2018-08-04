package frsf.isi.died.app.controller;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frsf.isi.died.app.vista.grafo.ControlPanel;
import frsf.isi.died.app.vista.grafo.GrafoPanel;
import frsf.isi.died.app.vista.grafo.ControlPanel;
import frsf.isi.died.app.vista.grafo.GrafoPanel;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.app.vista.material.MatPanel;
import frsf.isi.died.app.vista.material.VideoPanel;
import frsf.isi.died.app.vista.material.WishPanel;

public class MenuController {
	
	private JFrame framePrincipal;
	
	public MenuController(JFrame f) {
		this.framePrincipal = f;
	}
	
	public void showView(TiposAcciones accion) {
		switch (accion) {
		case ABM_LIBROS:
			LibroPanel panelLibros = new LibroPanel();
			LibroController libroController = new LibroController(panelLibros);
			libroController.crearPanel();
			framePrincipal.setContentPane(libroController.getPanelLibro());
			break;
		case ABM_VIDEOS:
			VideoPanel panelVideos = new VideoPanel();
			VideoController videoController = new VideoController(panelVideos);
			videoController.crearPanel();
			framePrincipal.setContentPane(videoController.getPanelVideo());
			break;
		case VER_GRAFO:
			JPanel panel = new JPanel(new BorderLayout());
			ControlPanel controlPanel = new ControlPanel();
			GrafoPanel grafoPanel = new GrafoPanel();
			GrafoController grfController = new GrafoController(grafoPanel,controlPanel);
			panel.add(controlPanel , BorderLayout.PAGE_START);
			panel.add(grafoPanel , BorderLayout.CENTER);
			framePrincipal.setContentPane(panel);
			break;
		case BSQ_MAT:
			MatPanel panelMat = new MatPanel(this);
			MatController matController = new MatController(panelMat);
			matController.crearPanel();
			framePrincipal.setContentPane(matController.getPanelMat());
			break;
		case VER_WISH:
			WishPanel panelWish = new WishPanel();
			WishController wishController = new WishController(panelWish);
			wishController.crearPanel();
			framePrincipal.setContentPane(wishController.getPanelWish());
			break;
		default:
			break;
		}
		framePrincipal.pack();
		framePrincipal.setExtendedState(Frame.MAXIMIZED_BOTH);
	}
	
}
