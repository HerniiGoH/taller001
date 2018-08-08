/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.app.vista.grafo;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

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
    private JButton btnPageRank;
    
    private Comparator<MaterialCapacitacion> prComp = (m1,m2)-> m2.getPageRank().compareTo(m1.getPageRank());
        
    public void armarPanel( List<MaterialCapacitacion> listaVertices){
    	
    	this.setLayout(new GridBagLayout());
    	GridBagConstraints cons = new GridBagConstraints();
    	
    	this.caminos= new ArrayList();
    	this.listaVertices = listaVertices;
    	this.btnRepintar = new JButton("Redistribuir Nodos");
    	//btnRepintar.setEnabled(false);
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
        	List<MaterialCapacitacion> aux = caminos.remove(0);
        	this.controller.getPanel().caminoPintar(aux);
        	caminos.add(aux);
        	if(caminos.size()==1) {
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
                    this.btnSiguiente.setEnabled(false);
                    }catch(NumberFormatException ex) {
                    	Integer idOrigen = this.listaVertices.get(cmbVertice1.getSelectedIndex()).getId();
                        Integer idDestino= this.listaVertices.get(cmbVertice2.getSelectedIndex()).getId();
                        this.caminos = controller.buscarCamino(idOrigen,idDestino);
                        if(!caminos.isEmpty() && caminos.size()!=1) {
                        this.btnSiguiente.setEnabled(true);
                        }
                    } 
                }
        );
        this.btnPageRank = new JButton("Orden por PageRank");
        this.btnPageRank.addActionListener(e->{
        	JDialog emergente = new JDialog(); 
        	GridBagConstraints gridConst1 = new GridBagConstraints();
			emergente.setSize(500,300);
			emergente.setAlwaysOnTop(true);
			emergente.setModal(true); emergente.setLocationRelativeTo(null);
			
			JPanel pan = new JPanel(); pan.setLayout(new GridBagLayout());
			
			Collections.sort(this.listaVertices, this.prComp);
			
			JList list = new JList(listaVertices.toArray());
			list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			//list.setLayoutOrientation(JList.HORIZONTAL_WRAP); 
			JScrollPane listScroller = new JScrollPane(list);
			listScroller.setBounds(list.getX(), list.getY(), 220, 80);
			listScroller.setAlignmentX(CENTER_ALIGNMENT);
			pan.add(listScroller);
			
			emergente.add(pan); emergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); emergente.setVisible(true);
        });
    	cons.anchor=GridBagConstraints.CENTER;
    	cons.gridx=0; this.add(new JLabel("Vertice Origen"));
        cons.gridx++; this.add(cmbVertice1);
    	cons.gridx++; this.add(new JLabel("Vertice Destino"));
    	cons.gridx++; this.add(cmbVertice2);
    	cons.gridx++; this.add(new JLabel("Cantidad de saltos(Vacio para ver todos)"));
    	cons.gridx++; this.add(txtLongitudCamino);
    	cons.gridy=20; cons.gridx=1; this.add(btnBuscarCamino,cons);
    	cons.gridx++; this.add(btnSiguiente,cons);
    	cons.gridx++; this.add(btnRepintar,cons);
    	cons.gridx++; this.add(btnPageRank,cons);
    }

    public GrafoController getController() {
        return controller;
    }

    public void setController(GrafoController controller) {
        this.controller = controller;
    }
    
}
