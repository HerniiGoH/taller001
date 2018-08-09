package frsf.isi.died.app.vista.material;

import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.*;

import frsf.isi.died.app.controller.ArbolController;
import frsf.isi.died.app.controller.Criterios;
import frsf.isi.died.app.controller.MatController;
import frsf.isi.died.app.controller.Ordenamiento;
import frsf.isi.died.app.controller.Relevancia;
import frsf.isi.died.app.controller.TipoArbol;
import frsf.isi.died.app.vista.arbol.ArbolN;
import frsf.isi.died.app.vista.arbol.Nodo;
import frsf.isi.died.app.vista.arbol.Tipo;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public class ArbolDatosPanel extends JPanel{
	
	private JLabel lblPrincipal = new JLabel();
	private JLabel lblTitulo = new JLabel();
	private JLabel lblTextTitulo = new JLabel();			
	private JLabel lblAgregarNodo = new JLabel();
	private JComboBox  btnTipo = new JComboBox();
	//MetadATOS
	private JLabel lblMetadatos = new JLabel();
	private JLabel lblAgregar = new JLabel();
	private JComboBox  btnAgregarMetadatos = new JComboBox();
	private JTextField txtAgregarMetadatos = new JTextField();
	private JButton btnAgregar = new JButton();
	/*
	this.remove(lblMetadatos);
	this.remove(lblAgregar);
	this.remove(btnAgregarMetadatos);
	this.remove(txtAgregarMetadatos);
	this.remove(btnAgregar);
*/
	//Resumen
	private JLabel lblResumen = new JLabel();
	private JLabel lblAgregar2 = new JLabel();
	private JTextField txtParrafo = new JTextField();
	private JButton btnAgregar2 = new JButton();
	/*
	this.remove(lblResumen);
	this.remove(lblAgregar2);
	this.remove(txtParrafo);
	this.remove(btnAgregar2);
	*/
	//CAPITULOS
	private JLabel lblCapitulos = new JLabel();
	private JLabel lblAgregar3 = new JLabel();
	private JComboBox  btnAgregarCapitulos = new JComboBox();
	private JLabel lblSeleccionarNodo = new JLabel();
	private JComboBox btnNodos = new JComboBox();
	/*
	this.remove(lblCapitulos);
	this.remove(lblAgregar3);
	this.remove(btnAgregarCapitulos);
	this.remove(lblSeleccionarNodo);
	this.remove(btnNodos);
	*/
	//CAP-CAP :V
	private JLabel lblCapCap = new JLabel();
	private JTextField txtTitulo = new JTextField();
	private JButton btnAgregar30 = new JButton();
	/*
	this.remove(lblCapCap);
	this.remove(txtTitulo);
	this.remove(btnAgregar30);
	*/
	//CAP-SECCIONES
	private JComboBox btnNodos2 = new JComboBox();
	private JLabel lblSeleccionarSeccion = new JLabel();
	private JLabel lblElejirSeccion = new JLabel();
	private JComboBox btnElejir = new JComboBox();
	private JLabel lblSecciones = new JLabel();
	private JTextField txtSeccion = new JTextField();
	private JButton btnAgregarSeccion = new JButton();
	private JLabel lblAgregar31 = new JLabel();
	private JTextField txtParrafo31 = new JTextField();
	private JButton btnAgregar31 = new JButton();
	/*
	this.remove(btnNodos2);
	this.remove(lblSeleccionarSeccion);
	this.remove(lblElejirSeccion);
	this.remove(btnElejir);
	this.remove(lblSecciones);
	this.remove(txtSeccion);
	this.remove(btnAgregarSeccion);
	this.remove(lblAgregar31);
	this.remove(txtParrafo31);
	this.remove(btnAgregar31);
	*/
	//CAP-METADATOS
	private JLabel lblMetadatos32 = new JLabel();
	private JLabel lblAgregar32 = new JLabel();
	private JComboBox  btnAgregarMetadatos32 = new JComboBox();
	private JTextField txtAgregarMetadatos32 = new JTextField();
	private JButton btnAgregar32 = new JButton();
	/*
	this.remove(lblMetadatos32);
	this.remove(lblAgregar32);
	this.remove(btnAgregarMetadatos32);
	this.remove(txtAgregarMetadatos32);
	this.remove(btnAgregar32);
	*/
	private ArbolController controller;
	
	private JPanel wrapper= new JPanel();
	private JPanel wrapper2= new JPanel();
	private JPanel wrapper3= new JPanel();
	
	public ArbolDatosPanel() {
		this.setLayout(new GridBagLayout());	
	}
	
	public void construir(String titulo) {
		
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblPrincipal= new JLabel("Agregar informacion del material de capacitacion");
		gridConst.gridx=0;
		gridConst.gridy=0;
		gridConst.weightx=0.0;
		gridConst.gridwidth=3;
		gridConst.anchor=GridBagConstraints.PAGE_START;
		this.add(lblPrincipal, gridConst);
				
		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridwidth=1;
		gridConst.gridx=0;
		gridConst.gridy=2;
		this.add(lblTitulo, gridConst);
		
		lblTextTitulo = new JLabel(titulo);
		gridConst.gridx = 1;
		gridConst.gridy = 2;
		this.add(lblTextTitulo, gridConst);
		
		lblAgregarNodo = new JLabel ("Agregar nodo:");
		gridConst.gridx = 0;
		gridConst.gridy = 3;
		this.add(lblAgregarNodo, gridConst);
		
		TipoArbol[] tipoArbol = {TipoArbol.Metadatos, TipoArbol.Resumen, TipoArbol.Capitulos};
		
		btnTipo = new JComboBox(tipoArbol);
		btnTipo.addActionListener(e->{
			switch((TipoArbol)btnTipo.getSelectedItem()) {
			
				case Metadatos:{
					
					this.remove(wrapper);
					
					wrapper = new JPanel(new GridBagLayout());
					
					lblMetadatos = new JLabel("Metadatos");
					gridConst.gridwidth=1;
					gridConst.gridx = 0; gridConst.gridy = 4;
					wrapper.add(lblMetadatos, gridConst);
					
					lblAgregar = new JLabel("Agregar:");
					gridConst.gridx = 0; gridConst.gridy = 5;
					wrapper.add(lblAgregar, gridConst);
					
					TipoArbol[] tipoArbol2 = {TipoArbol.Autor, TipoArbol.Editorial, TipoArbol.Fecha_Publicacion,TipoArbol.Palabras_Claves};
					btnAgregarMetadatos = new JComboBox(tipoArbol2);
					gridConst.gridx = 1; gridConst.gridy = 5;
					wrapper.add(btnAgregarMetadatos, gridConst);
					
					txtAgregarMetadatos = new JTextField();
					txtAgregarMetadatos.setColumns(12);
					gridConst.gridx = 0; gridConst.gridy = 6;
					wrapper.add(txtAgregarMetadatos, gridConst);
					
					btnAgregar = new JButton("Agregar");
					gridConst.gridx = 1; gridConst.gridy = 6;
					wrapper.add(btnAgregar, gridConst);
					btnAgregar.addActionListener(e1 -> {
						switch((TipoArbol)btnAgregarMetadatos.getSelectedItem()) {
						case Autor:
							Nodo n1 = new Nodo(txtAgregarMetadatos.getText(), (TipoArbol)btnAgregarMetadatos.getSelectedItem());
							controller.addMetadato(titulo,n1);
							
							break;
						case Editorial:
							Nodo n2 = new Nodo(txtAgregarMetadatos.getText(), (TipoArbol)btnAgregarMetadatos.getSelectedItem());
							controller.addMetadato(titulo,n2);
							
							break;
						case Fecha_Publicacion:
							Nodo n3 = new Nodo(txtAgregarMetadatos.getText(), (TipoArbol)btnAgregarMetadatos.getSelectedItem());
							controller.addMetadato(titulo,n3);
							
							break;
						case Palabras_Claves:
							Nodo n4 = new Nodo(txtAgregarMetadatos.getText(), (TipoArbol)btnAgregarMetadatos.getSelectedItem());
							controller.addMetadato(titulo,n4);
							
							break;
						default:
							break;
						}
					});
					
					gridConst.gridx=0;
					gridConst.gridy=4;
					gridConst.gridwidth=3;
					this.add(wrapper,gridConst);
					
					wrapper.repaint();
					
					this.repaint();
					this.doLayout();
					wrapper.doLayout();
					
					break;	
				}
				case Resumen:{
					
					this.remove(wrapper);
					
					wrapper = new JPanel(new GridBagLayout());
					
					lblResumen = new JLabel("Resumen");
					gridConst.gridwidth=1;
					gridConst.gridx = 0; gridConst.gridy = 4;
					wrapper.add(lblResumen, gridConst);
					
					lblAgregar2 = new JLabel("Agregar Parrafo:");
					gridConst.gridx = 0; gridConst.gridy = 5;
					wrapper.add(lblAgregar2, gridConst);
					
					txtParrafo = new JTextField();
					txtParrafo.setColumns(12);
					txtParrafo.setPreferredSize(new Dimension(200,60));
					gridConst.gridx = 0; gridConst.gridy = 6;
					wrapper.add(txtParrafo, gridConst);
					
					btnAgregar2 = new JButton("Agregar");
					gridConst.gridx = 1; gridConst.gridy = 6;
					wrapper.add(btnAgregar2, gridConst);
					btnAgregar2.addActionListener(e2 ->{
						
						Nodo n = new Nodo(txtParrafo.getText(), TipoArbol.Parrafo);
						controller.addResumen(titulo,n);
						
					});
					
					gridConst.gridx=0;
					gridConst.gridy=4;
					gridConst.gridwidth=3;
					this.add(wrapper,gridConst);
					
					wrapper.repaint();
					
					
					this.repaint();
					this.doLayout();
					wrapper.doLayout();
					break;	
				}
				case Capitulos:{
					
					this.remove(wrapper);
					
					wrapper = new JPanel(new GridBagLayout());
					
					lblCapitulos = new JLabel("Capitulos");
					gridConst.gridwidth=1;
					gridConst.gridx=0; gridConst.gridy=4;
					wrapper.add(lblCapitulos,gridConst);
					lblAgregar3 = new JLabel("Agregar");
					gridConst.gridx=0; gridConst.gridy=5;
					wrapper.add(lblAgregar3,gridConst);
					TipoArbol[] tipoArbol3 = {TipoArbol.Capitulos, TipoArbol.Seccion, TipoArbol.Metadatos};
					btnAgregarCapitulos = new JComboBox(tipoArbol3);
					gridConst.gridx=1; gridConst.gridy=5;
					wrapper.add(btnAgregarCapitulos,gridConst);
					
					btnAgregarCapitulos.addActionListener(e1->{
						switch((TipoArbol)btnAgregarCapitulos.getSelectedItem()) {
							case Capitulos:{
								wrapper.remove(wrapper2);
								
								wrapper2 = new JPanel(new GridBagLayout());
								
								lblCapCap = new JLabel("Titulo:");
								gridConst.gridwidth=1;
								gridConst.gridx=0;gridConst.gridy=6;
								wrapper2.add(lblCapCap, gridConst);
								
								txtTitulo = new JTextField();
								txtTitulo.setColumns(10);
								gridConst.gridx=1;gridConst.gridy=6;
								wrapper2.add(txtTitulo, gridConst);
								
								btnAgregar30 = new JButton("Agregar");
								gridConst.gridx=0;gridConst.gridy=7;
								wrapper2.add(btnAgregar30, gridConst);
								btnAgregar30.addActionListener(e2->{
									Nodo n = new Nodo(txtTitulo.getText(),TipoArbol.Capitulos);
									controller.addCapitulo(titulo, n);
								});
								
								gridConst.gridx=0;
								gridConst.gridy=6;
								gridConst.gridwidth=3;
								
								wrapper.add(wrapper2,gridConst);
								
								wrapper2.repaint();
								
								this.doLayout();
								wrapper.doLayout();
								wrapper2.doLayout();
								
								break;	
							}
							case Seccion:{
								wrapper.remove(wrapper2);
								
								wrapper2 = new JPanel(new GridBagLayout());
								
								lblSeleccionarNodo = new JLabel("Seleccionar Capitulo:");
								gridConst.gridwidth=1;
								gridConst.gridx=0;gridConst.gridy=6;
								wrapper2.add(lblSeleccionarNodo,gridConst);
								
								List<String> caps = new ArrayList();
								caps=controller.getCapitulos(titulo);
								btnNodos = new JComboBox(caps.toArray());
								gridConst.gridx=1;gridConst.gridy=6;
								wrapper2.add(btnNodos,gridConst);
								
								lblElejirSeccion=new JLabel("Agregar:");
								gridConst.gridx=0;gridConst.gridy=7;
								wrapper2.add(lblElejirSeccion,gridConst);
								
								TipoArbol[] tipoArbol4= {TipoArbol.Seccion,TipoArbol.Parrafo};
								btnNodos2 = new JComboBox(tipoArbol4);
								gridConst.gridx=1;gridConst.gridy=7;
								wrapper2.add(btnNodos2,gridConst);
								btnNodos2.addActionListener(e3->{
									switch((TipoArbol)btnNodos2.getSelectedItem()) {
										case Seccion:{
											wrapper2.remove(wrapper3);
											
											wrapper3 = new JPanel(new GridBagLayout());
											
											lblSecciones = new JLabel("Agregar:");
											gridConst.gridwidth=1;
											gridConst.gridx=0;gridConst.gridy=8;
											wrapper3.add(lblSecciones,gridConst);
											
											txtSeccion = new JTextField();
											txtSeccion.setColumns(10);
											gridConst.gridx=1;gridConst.gridy=8;
											wrapper3.add(txtSeccion,gridConst);
											
											btnAgregarSeccion = new JButton("Agregar");
											gridConst.gridx=2;gridConst.gridy=8;
											wrapper3.add(btnAgregarSeccion,gridConst);
											btnAgregarSeccion.addActionListener(e2 -> {
												
												Nodo n1 = new Nodo(txtSeccion.getText(), (TipoArbol)btnElejir.getSelectedItem());
												controller.addCapSeccion(titulo, btnNodos.getSelectedItem().toString(),n1);
												
											});
											
											gridConst.gridx=0;
											gridConst.gridy=8;
											gridConst.gridwidth=3;
											
											wrapper2.add(wrapper3,gridConst);
											
											wrapper3.repaint();
											
											this.doLayout();
											wrapper.doLayout();
											wrapper2.doLayout();
											wrapper3.doLayout();
											break;	 
										}
										case Parrafo:{
											wrapper2.remove(wrapper3);
											
											wrapper3 = new JPanel(new GridBagLayout());
											
											lblSeleccionarSeccion = new JLabel("Elija una Seccion:");
											gridConst.gridwidth=1;
											gridConst.gridx=0;gridConst.gridy=8;
											wrapper3.add(lblSeleccionarSeccion,gridConst);											
											
											List<String>sec=new ArrayList();
											sec = controller.getSecciones(titulo, btnNodos.getSelectedItem().toString());
											btnElejir = new JComboBox(sec.toArray());
											gridConst.gridx=1;gridConst.gridy=8;
											wrapper3.add(btnElejir,gridConst);
											
											lblAgregar31 = new JLabel("Contenido del Parrafo:");
											gridConst.gridx=0;gridConst.gridy=9;
											wrapper3.add(lblAgregar31,gridConst);
											
											txtParrafo31 = new JTextField();
											txtParrafo31.setColumns(10);
											gridConst.gridx=1;gridConst.gridy=9;
											wrapper3.add(txtParrafo31,gridConst);
											
											btnAgregar31 = new JButton("Agregar");
											gridConst.gridx=2;gridConst.gridy=9;
											wrapper3.add(btnAgregar31,gridConst);
											btnAgregar31.addActionListener(e2 ->{
												
												Nodo n = new Nodo(txtParrafo31.getText(), (TipoArbol)btnElejir.getSelectedItem());
												controller.addCapSeccionParrafo(titulo, btnNodos.getSelectedItem().toString(),btnNodos2.getSelectedItem().toString(),n);
												
											});
											
											gridConst.gridx=0;
											gridConst.gridy=8;
											gridConst.gridwidth=3;
											
											wrapper2.add(wrapper3,gridConst);
											
											wrapper3.repaint();
											
											this.doLayout();
											wrapper.doLayout();
											wrapper2.doLayout();
											wrapper3.doLayout();
											break;	 
										}
										default: break;
									}
								});
								
								
								gridConst.gridx=0;
								gridConst.gridy=6;
								gridConst.gridwidth=3;
								
								wrapper.add(wrapper2,gridConst);
								
								wrapper2.repaint();
								
								this.doLayout();
								wrapper.doLayout();
								wrapper2.doLayout();
								break;	
							}
							case Metadatos:{
								wrapper.remove(wrapper2);
								
								wrapper2 = new JPanel(new GridBagLayout());
								
								lblSeleccionarNodo = new JLabel("Seleccionar Capitulo:");
								gridConst.gridwidth=1;
								gridConst.gridx=0;gridConst.gridy=6;
								wrapper2.add(lblSeleccionarNodo,gridConst);
								
								List<String> caps = new ArrayList();
								//caps=controller.getCapitulos(titulo);
								btnNodos = new JComboBox(caps.toArray());
								gridConst.gridx=1;gridConst.gridy=6;
								wrapper2.add(btnNodos,gridConst);
								
								lblAgregar32 = new JLabel("Agregar");
								gridConst.gridx=0;gridConst.gridy=7;
								wrapper2.add(lblAgregar32,gridConst);
								
								TipoArbol[] tipoArbol5= {TipoArbol.Sitios_Web_Ejercicios_Relacionados,TipoArbol.Sitios_Web_Relacionados,TipoArbol.Palabras_Claves};
								btnAgregarMetadatos32 = new JComboBox(tipoArbol5);
								gridConst.gridwidth=2;
								gridConst.gridx=1;gridConst.gridy=7;
								wrapper2.add(btnAgregarMetadatos32,gridConst);
								
								lblMetadatos32 = new JLabel("Contenido:");
								gridConst.gridwidth=1;
								gridConst.gridx=0;gridConst.gridy=8;
								wrapper2.add(lblMetadatos32,gridConst);
								
								txtAgregarMetadatos32 = new JTextField();
								txtAgregarMetadatos32.setColumns(10);
								gridConst.gridx=1;gridConst.gridy=8;
								wrapper2.add(txtAgregarMetadatos32,gridConst);
								
								btnAgregar32 = new JButton("Agregar");
								gridConst.gridx=2;gridConst.gridy=8;
								wrapper2.add(btnAgregar32,gridConst);
								btnAgregar32.addActionListener(e2 ->{
									
									switch((TipoArbol)btnAgregarMetadatos32.getSelectedItem()) {
									case Sitios_Web_Relacionados:
										Nodo n1 = new Nodo(txtAgregarMetadatos32.getText(), (TipoArbol)btnAgregarMetadatos32.getSelectedItem());
										controller.addCapMetadato(titulo, btnNodos.getSelectedItem().toString(),n1);
										
										break;
									case Sitios_Web_Ejercicios_Relacionados:
										Nodo n2 = new Nodo(txtAgregarMetadatos32.getText(), (TipoArbol)btnAgregarMetadatos32.getSelectedItem());
										controller.addCapMetadato(titulo, btnNodos.getSelectedItem().toString(),n2);
										
										break;
									case Palabras_Claves:
										Nodo n3 = new Nodo(txtAgregarMetadatos32.getText(), (TipoArbol)btnAgregarMetadatos32.getSelectedItem());
										controller.addCapMetadato(titulo, btnNodos.getSelectedItem().toString(),n3);
										
										break;
									default:
										break;
									}
									
								});
								
								gridConst.gridx=0;
								gridConst.gridy=6;
								gridConst.gridwidth=3;
								
								wrapper.add(wrapper2,gridConst);
								
								wrapper2.repaint();
								
								this.doLayout();
								wrapper.doLayout();
								wrapper2.doLayout();
								break;	
							}
						}
					});
					
					gridConst.gridx=0;
					gridConst.gridy=4;
					gridConst.gridwidth=3;
					this.add(wrapper,gridConst);
					
					wrapper.repaint();
					
					this.repaint();
					this.doLayout();
					wrapper.doLayout();
					
					break;	
				}
				default: 
					break;
			}
		});
		gridConst.gridx = 1;
		gridConst.gridy = 3;
		this.add(btnTipo, gridConst);
				
	}
	

	public ArbolController getController() {
		return controller;
	}

	public void setController(ArbolController controller) {
		this.controller = controller;
	}

}