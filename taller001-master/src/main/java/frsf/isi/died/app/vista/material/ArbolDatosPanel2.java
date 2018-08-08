package frsf.isi.died.app.vista.material;

import java.awt.Dialog.ModalityType;
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
	
	public ArbolDatosPanel() {
		this.setLayout(new GridBagLayout());
		
	}
	
	public void construir(String titulo) {
		
		GridBagConstraints gridConst= new GridBagConstraints();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		lblPrincipal= new JLabel("Agregar informacion del material de capacitacion");
		gridConst.gridx=0;
		gridConst.gridy=0;
		gridConst.weightx=0.0;
		this.add(lblPrincipal, gridConst);
		
		
		lblTitulo = new JLabel("Titulo: ");
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
		btnTipo.addActionListener(e -> {
			switch((TipoArbol) btnTipo.getSelectedItem()) {
			case Metadatos:
				
				this.remove(lblResumen);
				this.remove(lblAgregar2);
				this.remove(txtParrafo);
				this.remove(btnAgregar2);
				this.remove(lblAgregar2);
				this.remove(txtParrafo);
				this.remove(btnAgregar2);
				this.remove(lblCapitulos);
				this.remove(lblAgregar3);
				this.remove(btnAgregarCapitulos);
				this.remove(lblSeleccionarNodo);
				this.remove(btnNodos);
				this.remove(lblCapCap);
				this.remove(txtTitulo);
				this.remove(btnAgregar30);
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
				this.remove(lblMetadatos32);
				this.remove(lblAgregar32);
				this.remove(btnAgregarMetadatos32);
				this.remove(txtAgregarMetadatos32);
				this.remove(btnAgregar32);
				
				lblMetadatos = new JLabel("Metadatos");
				gridConst.gridx = 1;
				gridConst.gridy = 5;
				this.add(lblMetadatos, gridConst);
				
				lblAgregar = new JLabel("Agregar:");
				gridConst.gridx = 2;
				gridConst.gridy = 5;
				this.add(lblAgregar, gridConst);
				
				TipoArbol[] tipoMetadato = {TipoArbol.Autor, TipoArbol.Editorial, TipoArbol.Fecha_Publicacion,TipoArbol.Palabras_Claves};
				btnAgregarMetadatos =new JComboBox(tipoMetadato);
				gridConst.gridx = 2;
				gridConst.gridy = 6;
				this.add(btnAgregarMetadatos, gridConst);
				
				txtAgregarMetadatos = new JTextField();
				gridConst.gridx = 1;
				gridConst.gridy = 7;
				gridConst.gridwidth = 2;
				this.add(txtAgregarMetadatos, gridConst);
				
				btnAgregar = new JButton("Agregar");
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
				gridConst.gridx = 3;
				gridConst.gridy = 7;
				this.add(btnAgregar, gridConst);
				
				
				
				this.repaint();
				this.doLayout();
				
				break;
			case Resumen:
				lblResumen =  new JLabel("Resumen");
				gridConst.gridx = 1;
				gridConst.gridy = 5;
				this.add(lblResumen, gridConst);
				
				lblAgregar2 = new JLabel("Agregar Párrafo:");
				gridConst.gridx = 1;
				gridConst.gridy = 6;
				this.add(lblAgregar2, gridConst);
				
				txtParrafo = new JTextField();
				gridConst.gridx = 1;
				gridConst.gridy = 7;
				gridConst.gridheight=2;
				gridConst.gridwidth=2;
				this.add(txtParrafo, gridConst);
				
				btnAgregar2 = new JButton("Agregar");
				btnAgregar2.addActionListener(e2 ->{
					
					Nodo n = new Nodo(txtParrafo.getText(), TipoArbol.Parrafo);
					controller.addResumen(titulo,n);
					
					
				});
				gridConst.gridx = 3;
				gridConst.gridy = 8;
				this.add(btnAgregar2,gridConst);
				
				
				this.remove(lblMetadatos);
				this.remove(lblAgregar);
				this.remove(btnAgregarMetadatos);
				this.remove(txtAgregarMetadatos);
				this.remove(btnAgregar);
				this.remove(lblCapitulos);
				this.remove(lblAgregar3);
				this.remove(btnAgregarCapitulos);
				this.remove(lblSeleccionarNodo);
				this.remove(btnNodos);
				this.remove(lblCapCap);
				this.remove(txtTitulo);
				this.remove(btnAgregar30);
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
				this.remove(lblMetadatos32);
				this.remove(lblAgregar32);
				this.remove(btnAgregarMetadatos32);
				this.remove(txtAgregarMetadatos32);
				this.remove(btnAgregar32);
				
				this.repaint();
				this.doLayout();
				
				break;
			case Capitulos:
				lblCapitulos = new JLabel("Capitulos");
				gridConst.gridx = 1;
				gridConst.gridy = 5;
				this.add(lblCapitulos, gridConst);
				
				lblAgregar3 = new JLabel("Agregar parrafo:");
				gridConst.gridx = 1;
				gridConst.gridy = 6;
				this.add(lblAgregar3, gridConst);
				
				TipoArbol[] tipoCapitulo = {TipoArbol.Capitulos, TipoArbol.Seccion, TipoArbol.Metadatos};
				btnAgregarCapitulos = new JComboBox(tipoCapitulo);
				btnAgregarCapitulos.addActionListener(e3 -> {
					switch((TipoArbol) btnAgregarCapitulos.getSelectedItem()) {
					
					case Capitulos:
						lblCapCap = new JLabel("Título:");
						gridConst.gridx = 1;
						gridConst.gridy = 7;
						this.add(lblCapCap, gridConst);
						
						txtTitulo = new JTextField();
						gridConst.gridx = 1;
						gridConst.gridy = 8;
						this.add(txtTitulo, gridConst);
						
						btnAgregar30 = new JButton("Agregar");
						gridConst.gridx = 2;
						gridConst.gridy = 8;
						this.add(btnAgregar30, gridConst);
						
						break;
						
						
					case Seccion:
						
						
						lblSeleccionarNodo = new JLabel("Seleccionar capitulo:");
						gridConst.gridx = 1;
						gridConst.gridy = 7;
						this.add(lblSeleccionarNodo, gridConst);
						
						
						List<String> caps  = new ArrayList<>(); 
						caps= controller.getCapitulos(titulo);
						btnNodos = new JComboBox(caps.toArray());
						gridConst.gridx = 2;
						gridConst.gridy = 7;
						this.add(btnNodos, gridConst);
						
						lblElejirSeccion = new JLabel("Agregar:");
						gridConst.gridx = 1;
						gridConst.gridy = 8;
						this.add(lblElejirSeccion, gridConst);
						
						TipoArbol[] seccion = {TipoArbol.Seccion, TipoArbol.Parrafo};
						btnElejir = new JComboBox(seccion);
						gridConst.gridx = 2;
						gridConst.gridy = 8;
						this.add(btnElejir, gridConst);
						btnElejir.addActionListener(e1 -> {
							switch((TipoArbol)btnElejir.getSelectedItem()) {
							
							case Seccion:
								
								lblSecciones = new JLabel("Nombre de la nueva seccion");
								gridConst.gridx = 1;
								gridConst.gridy = 9;
								gridConst.gridwidth = 2;
								this.add(lblSecciones, gridConst);
								
								txtSeccion = new JTextField();
								gridConst.gridx = 3;
								gridConst.gridy = 9;
								gridConst.gridwidth=2;
								this.add(txtSeccion, gridConst);
								
								btnAgregarSeccion = new JButton("Agregar");
								gridConst.gridx = 5;
								gridConst.gridy = 9;
								this.add(btnAgregarSeccion, gridConst);
								btnAgregarSeccion.addActionListener(e2 -> {
									
									Nodo n1 = new Nodo(txtSeccion.getText(), (TipoArbol)btnElejir.getSelectedItem());
									controller.addCapSeccion(titulo, btnNodos.getSelectedItem().toString(),n1);
									
								});
								
								break;
							case Parrafo:
								
								lblSeleccionarSeccion = new JLabel("Seleccionar seccion:");
								gridConst.gridx = 1;
								gridConst.gridy = 9;
								this.add(lblSeleccionarSeccion, gridConst);
								
								
								List<String> sec  = new ArrayList<String>(); 
								sec= controller.getSecciones(titulo, btnNodos.getSelectedItem().toString());
								btnNodos2 = new JComboBox(sec.toArray());
								gridConst.gridx = 2;
								gridConst.gridy = 9;
								this.add(btnNodos2, gridConst);
								
								lblAgregar31 = new JLabel("Agregar parrafo:");
								gridConst.gridx = 1;
								gridConst.gridy = 10;
								this.add(lblAgregar31, gridConst);
								
								txtParrafo31 = new JTextField();
								gridConst.gridx = 2;
								gridConst.gridy = 10;
								gridConst.gridheight=2;
								gridConst.gridwidth=2;
								this.add(txtParrafo31, gridConst);
								
								btnAgregar31 = new JButton("Agregar");
								gridConst.gridx = 4;
								gridConst.gridy = 11;
								this.add(btnAgregar31, gridConst);
								btnAgregar31.addActionListener(e2 ->{
									
									Nodo n = new Nodo(txtParrafo31.getText(), (TipoArbol)btnElejir.getSelectedItem());
									controller.addCapSeccionParrafo(titulo, btnNodos.getSelectedItem().toString(),btnNodos2.getSelectedItem().toString(),n);
									
								});
								
								break;
								
							}
						});
						
						break;
					case Metadatos:
						
						lblSeleccionarNodo = new JLabel("Seleccionar capitulo:");
						gridConst.gridx = 1;
						gridConst.gridy = 7;
						this.add(lblSeleccionarNodo, gridConst);
						
						
						List<String> capit  = new ArrayList<String>(); 
						capit= controller.getCapitulos(titulo);
						btnNodos = new JComboBox(capit.toArray());
						gridConst.gridx = 1;
						gridConst.gridy = 8;
						this.add(btnNodos, gridConst);						
						
						lblMetadatos32 = new JLabel("Metadatos");
						gridConst.gridx = 1;
						gridConst.gridy = 9;
						this.add(lblMetadatos32, gridConst);
						
						lblAgregar32 = new JLabel("Agregar:");
						gridConst.gridx = 1;
						gridConst.gridy = 10;
						this.add(lblAgregar32, gridConst);
						
						TipoArbol[] tipoMetadato32 = {TipoArbol.Sitios_Web_Relacionados, TipoArbol.Sitios_Web_Ejercicios_Relacionados, TipoArbol.Palabras_Claves};
						btnAgregarMetadatos32 = new JComboBox(tipoMetadato32);
						gridConst.gridx = 2;
						gridConst.gridy = 10;
						this.add(btnAgregarMetadatos32, gridConst);
						
						txtAgregarMetadatos32 = new JTextField();
						gridConst.gridx = 1;
						gridConst.gridy = 11;
						gridConst.gridwidth = 2;
						this.add(txtAgregarMetadatos32, gridConst);
						
						btnAgregar32 = new JButton("Agregar");
						gridConst.gridx = 3;
						gridConst.gridy = 11;
						this.add(lblAgregar32, gridConst);
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
						this.remove(lblMetadatos);
						this.remove(lblAgregar);
						this.remove(btnAgregarMetadatos);
						this.remove(txtAgregarMetadatos);
						this.remove(btnAgregar);
						this.remove(lblResumen);
						this.remove(lblAgregar2);
						this.remove(txtParrafo);
						this.remove(btnAgregar2);
						
						
						this.repaint();
						this.doLayout();	
						break;
					default:
						break;
					}
				});
				gridConst.gridx = 2;
				gridConst.gridy = 6;
				this.add(lblAgregar3, gridConst);
				
				
				break;
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
	
	
	private boolean esValido(String s) {
		Integer mes,dia,ano;
		dia = Integer.valueOf(s.substring(0, 2));
		mes = Integer.valueOf(s.substring(3,5));
		ano = Integer.valueOf(s.substring(6, 10));
		if(mes >0 && mes<13 && dia >=1 && dia <=31) {
			if((mes == 4 || mes == 6 || mes == 9 || mes == 11)) {
				if(dia <=30 ) {return true;}
				else return false;
			}
			else {
				if(mes == 2) {
					if(dia<=28) {return true;}
					else return false;
				}
				else return true;
			}
		}
		else return false;
	}
	
	private Date inicializar(String s) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = formatter.parse(s); 
        return fecha;
	}

}