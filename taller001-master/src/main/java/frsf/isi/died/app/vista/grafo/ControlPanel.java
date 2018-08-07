/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.app.vista.grafo;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.GrafoController;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

/**
 *
 * @author mdominguez
 */
public class ControlPanel extends JPanel {
    
    private JComboBox<MaterialCapacitacion> cmbVertice1; 
    private JComboBox<MaterialCapacitacion> cmbVertice2; 
    private JTextField txtLongitudCamino; 
    private JButton btnBuscarCamino; 
    private GrafoController controller;
    private List<MaterialCapacitacion> listaVertices;
    private JButton btnRepintar;
    private JButton btnSiguiente;
    private List<List<MaterialCapacitacion>> caminos;
        
    public void armarPanel( List<MaterialCapacitacion> listaVertices){
    	this.caminos= new ArrayList();
    	this.listaVertices = listaVertices;
    	this.btnRepintar = new JButton("Redistribuir Nodos");
    	btnRepintar.addActionListener(e->{
    		this.btnSiguiente.setEnabled(false);
    		this.controller.getPanel().repintar(true);
    	});
    	this.cmbVertice1 = new JComboBox(listaVertices.toArray()); 
        this.cmbVertice2 = new JComboBox(listaVertices.toArray()); 
        this.txtLongitudCamino = new JTextField(2);
        this.btnBuscarCamino = new JButton("Buscar Camino(s)");
        this.btnSiguiente = new JButton("Siguiente Camino");
        this.btnSiguiente.addActionListener(e->{
        	this.controller.getPanel().repintar(false);
        	this.controller.getPanel().caminoPintar(caminos.remove(0));
        	if(caminos.isEmpty()) {
        		this.btnSiguiente.setEnabled(false);
        	}
        });
        this.btnSiguiente.setEnabled(false);
        this.btnBuscarCamino.addActionListener(
                e -> {
                	this.controller.getPanel().repintar(false);
                	try {
                    Integer n =Integer.parseInt(txtLongitudCamino.getText());
                    Integer idOrigen = this.listaVertices.get(cmbVertice1.getSelectedIndex()).getId();
                    Integer idDestino= this.listaVertices.get(cmbVertice2.getSelectedIndex()).getId();
                    controller.buscarCamino(idOrigen,idDestino,n);
                    }catch(NumberFormatException ex) {
                    	Integer idOrigen = this.listaVertices.get(cmbVertice1.getSelectedIndex()).getId();
                        Integer idDestino= this.listaVertices.get(cmbVertice2.getSelectedIndex()).getId();
                        this.caminos = controller.buscarCamino(idOrigen,idDestino);
                        if(!caminos.isEmpty()) {
                        this.btnSiguiente.setEnabled(true);
                        }
                    } 
                }
        );
        this.add(new JLabel("Vertice Origen"));        
    	this.add(cmbVertice1);
    	this.add(new JLabel("Vertice Destino"));
    	this.add(cmbVertice2);
    	this.add(new JLabel("Cantidad de saltos(Vacio para ver todos)"));        
    	this.add(txtLongitudCamino);        
    	this.add(btnBuscarCamino);
    	this.add(btnSiguiente);
    	this.add(btnRepintar);
    }

    public GrafoController getController() {
        return controller;
    }

    public void setController(GrafoController controller) {
        this.controller = controller;
    }

    
}
