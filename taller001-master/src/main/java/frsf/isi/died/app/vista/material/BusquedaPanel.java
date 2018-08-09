package frsf.isi.died.app.vista.material;

import java.awt.Dialog.ModalityType;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

import frsf.isi.died.app.controller.ArbolController;
import frsf.isi.died.app.controller.BusquedaController;
import frsf.isi.died.app.controller.Criterios;
import frsf.isi.died.app.controller.MatController;
import frsf.isi.died.app.controller.Ordenamiento;
import frsf.isi.died.app.controller.Relevancia;
import frsf.isi.died.app.controller.TipoArbol;
import frsf.isi.died.app.vista.filtro.Filtro;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public class BusquedaPanel extends JPanel{
	//Primer nivel
	private JLabel busqueda;
	private JLabel criterio;
	private JComboBox box1;
	//Segundo Nivel
	private JLabel lblFiltro1;
	private JComboBox btnBox2;
	private JLabel lblContenido1;
	private JTextField txtDatos1;
	//Tercer nivel
	private JLabel lblFiltro2;
	private JComboBox btnBox3;
	private JLabel lblContenido2;

	// Boton agregar
	private JButton btnAgregar;
	private JButton btnBuscarDocumento;
	
	//Paneles
	private JPanel wrapper= new JPanel();
	private JPanel wrapper2= new JPanel();
	
	private BusquedaController controller;
	
	public BusquedaPanel() {
		this.setLayout(new GridBagLayout());

	}
	public void construir() {
		
		GridBagConstraints gridConst= new GridBagConstraints();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		busqueda = new JLabel("Busqueda de documentos");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(busqueda, gridConst);
		
		criterio = new JLabel("Criterio:");
		gridConst.gridx=0;
		gridConst.gridy=1;
		this.add(criterio, gridConst);
		
		TipoArbol[] criterio1 = {TipoArbol.Metadatos, TipoArbol.Resumen, TipoArbol.Capitulos};
		
		box1 = new JComboBox(criterio1);
		gridConst.gridx=1;
		gridConst.gridy=1;
		gridConst.gridwidth = 2;
		this.add(box1, gridConst);
		box1.addActionListener(e -> {
			
			switch((TipoArbol)box1.getSelectedItem()) {
			
			case Metadatos:{
				
				this.remove(wrapper);
				
				wrapper = new JPanel(new GridBagLayout());
				
				lblFiltro1 = new JLabel("Filtrar por:");
				gridConst.gridwidth = 1;
				gridConst.gridx = 0;
				gridConst.gridy = 2;
				wrapper.add(lblFiltro1, gridConst);
				
				TipoArbol[] criterio2 = {TipoArbol.Autor, TipoArbol.Editorial, TipoArbol.Fecha_Publicacion, TipoArbol.Palabras_Claves};
				
				btnBox2 = new JComboBox(criterio2);
				gridConst.gridx = 1;
				gridConst.gridy = 2;
				gridConst.gridwidth = 2;
				wrapper.add(btnBox2, gridConst);
				
				lblContenido1 = new JLabel("Contenido:");
				gridConst.gridwidth = 1;
				gridConst.gridx = 0;
				gridConst.gridy = 3;
				wrapper.add(lblContenido1, gridConst);
				
				txtDatos1 = new JTextField();
				txtDatos1.setColumns(10);
				gridConst.gridx = 1;
				gridConst.gridy = 3;
				wrapper.add(txtDatos1, gridConst);
				
				btnBuscarDocumento = new JButton("Buscar Material");
				gridConst.gridx = 1;
				gridConst.gridy = 4;
				wrapper.add(btnBuscarDocumento, gridConst);
				btnBuscarDocumento.addActionListener(e1 -> {
					
					List<MaterialCapacitacion> listaVertices = controller.filtrar();
					
					JDialog emergente = new JDialog(); 
		        	GridBagConstraints gridConst1 = new GridBagConstraints();
					emergente.setSize(500,300);
					emergente.setAlwaysOnTop(true);
					emergente.setModal(true); emergente.setLocationRelativeTo(null);
					
					JPanel pan = new JPanel(); pan.setLayout(new GridBagLayout());
					
					JList list = new JList(listaVertices.toArray());
					list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					//list.setLayoutOrientation(JList.HORIZONTAL_WRAP); 
					JScrollPane listScroller = new JScrollPane(list);
					listScroller.setBounds(list.getX(), list.getY(), 220, 80);
					listScroller.setAlignmentX(CENTER_ALIGNMENT);
					pan.add(listScroller);
					
					emergente.add(pan); emergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); emergente.setVisible(true);
					
				});
				
				
				btnAgregar = new JButton("Agregar");
				gridConst.gridx = 0;
				gridConst.gridy = 4;
				wrapper.add(btnAgregar, gridConst);
				btnAgregar.addActionListener(e1 -> {
					
					Filtro f = new Filtro(txtDatos1.getText(),box1.getSelectedItem().toString(),btnBox2.getSelectedItem().toString());
					controller.addFiltro(f);
					
				});
				
				gridConst.gridx=0;
				gridConst.gridy=3;
				gridConst.gridwidth=4;
				this.add(wrapper,gridConst);
				
				this.repaint();
				wrapper.repaint();
				
				this.doLayout();
				wrapper.doLayout();
				
				break;}
			case Resumen:{
				
				this.remove(wrapper);
				
				wrapper = new JPanel(new GridBagLayout());
				
				lblContenido1 = new JLabel("Contenido del parrafo:");
				gridConst.gridwidth = 1;
				gridConst.gridx = 0;
				gridConst.gridy = 2;
				wrapper.add(lblContenido1, gridConst);
				
				txtDatos1= new JTextField ();
				txtDatos1.setColumns(10);
				gridConst.gridx = 1;
				gridConst.gridy = 2;
				wrapper.add(txtDatos1, gridConst);
				
				btnBuscarDocumento = new JButton("Buscar Material");
				gridConst.gridx = 1;
				gridConst.gridy = 3;
				wrapper.add(btnBuscarDocumento, gridConst);
				btnBuscarDocumento.addActionListener(e1 -> {
					
					List<MaterialCapacitacion> listaVertices = controller.filtrar();
					
					JDialog emergente = new JDialog(); 
		        	GridBagConstraints gridConst1 = new GridBagConstraints();
					emergente.setSize(500,300);
					emergente.setAlwaysOnTop(true);
					emergente.setModal(true); emergente.setLocationRelativeTo(null);
					
					JPanel pan = new JPanel(); pan.setLayout(new GridBagLayout());
					
					JList list = new JList(listaVertices.toArray());
					list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					//list.setLayoutOrientation(JList.HORIZONTAL_WRAP); 
					JScrollPane listScroller = new JScrollPane(list);
					listScroller.setBounds(list.getX(), list.getY(), 220, 80);
					listScroller.setAlignmentX(CENTER_ALIGNMENT);
					pan.add(listScroller);
					
					emergente.add(pan); emergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); emergente.setVisible(true);
					
				});
				
				btnAgregar= new JButton("Agregar");
				gridConst.gridx = 0;
				gridConst.gridy = 3;
				wrapper.add(btnAgregar, gridConst);
				btnAgregar.addActionListener(e1 -> {
					Filtro f = new Filtro(txtDatos1.getText(),box1.getSelectedItem().toString(),TipoArbol.Parrafo.toString());
					controller.addFiltro(f);
				});
				
				gridConst.gridx=0;
				gridConst.gridy=2;
				gridConst.gridwidth=4;
				this.add(wrapper,gridConst);
				
				this.repaint();
				wrapper.repaint();
				
				this.doLayout();
				wrapper.doLayout();
				
				break;}
			case Capitulos:{
				
				this.remove(wrapper);
				
				wrapper = new JPanel(new GridBagLayout());
				
				lblFiltro1 = new JLabel("Filtrar por:");
				gridConst.gridwidth = 1;
				gridConst.gridx = 0;
				gridConst.gridy = 2;
				wrapper.add(lblFiltro1, gridConst);
				
				TipoArbol[] criterio2 = {TipoArbol.Capitulos, TipoArbol.Metadatos, TipoArbol.Seccion};
				
				btnBox2 = new JComboBox(criterio2);
				gridConst.gridx = 1;
				gridConst.gridy = 2;
				wrapper.add(btnBox2, gridConst);
				btnBox2.addActionListener(e1 -> {
					
				switch((TipoArbol)btnBox2.getSelectedItem()) {
					case Capitulos:{
						
						wrapper.remove(wrapper2);
						
						wrapper2 = new JPanel(new GridBagLayout());
						
						lblContenido1 = new JLabel("Contenido:");
						gridConst.gridwidth = 1;
						gridConst.gridx = 0;
						gridConst.gridy = 3;
						wrapper2.add(lblContenido1, gridConst);
						
						txtDatos1 = new JTextField();
						txtDatos1.setColumns(10);
						gridConst.gridx = 1;
						gridConst.gridy = 3;
						wrapper2.add(txtDatos1, gridConst);
						
						btnBuscarDocumento = new JButton("Buscar Material");
						gridConst.gridx = 1;
						gridConst.gridy = 4;
						wrapper2.add(btnBuscarDocumento, gridConst);
						btnBuscarDocumento.addActionListener(e3 -> {
							
							List<MaterialCapacitacion> listaVertices = controller.filtrar();
							
							JDialog emergente = new JDialog(); 
				        	GridBagConstraints gridConst1 = new GridBagConstraints();
							emergente.setSize(500,300);
							emergente.setAlwaysOnTop(true);
							emergente.setModal(true); emergente.setLocationRelativeTo(null);
							
							JPanel pan = new JPanel(); pan.setLayout(new GridBagLayout());
							
							JList list = new JList(listaVertices.toArray());
							list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
							//list.setLayoutOrientation(JList.HORIZONTAL_WRAP); 
							JScrollPane listScroller = new JScrollPane(list);
							listScroller.setBounds(list.getX(), list.getY(), 220, 80);
							listScroller.setAlignmentX(CENTER_ALIGNMENT);
							pan.add(listScroller);
							
							emergente.add(pan); emergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); emergente.setVisible(true);
							
						});
						
						btnAgregar = new JButton("Agregar");
						gridConst.gridx = 0;
						gridConst.gridy = 4;
						wrapper2.add(btnAgregar, gridConst);
						btnAgregar.addActionListener(e2 -> {
							
							Filtro f = new Filtro(txtDatos1.getText(),box1.getSelectedItem().toString(),btnBox2.getSelectedItem().toString());
							controller.addFiltro(f);
							
						});
						
						gridConst.gridx=0;
						gridConst.gridy=3;
						gridConst.gridwidth=4;
						wrapper.add(wrapper2,gridConst);
						
						wrapper2.repaint();
						
						this.doLayout();
						wrapper.doLayout();
						wrapper2.doLayout();
						
						break;}
					
					case Metadatos:{
						
						wrapper.remove(wrapper2);
						
						wrapper2 = new JPanel(new GridBagLayout());
						
						lblFiltro2 = new JLabel("Tipo: ");
						gridConst.gridwidth = 1;
						gridConst.gridx = 0;
						gridConst.gridy = 3;
						wrapper2.add(lblFiltro2, gridConst);
						
						TipoArbol[] criterio3 = {TipoArbol.Palabras_Claves, TipoArbol.Sitios_Web_Relacionados, TipoArbol.Sitios_Web_Ejercicios_Relacionados};
						
						btnBox3 = new JComboBox(criterio3);
						gridConst.gridx = 1;
						gridConst.gridy = 3;
						gridConst.gridwidth = 2;
						wrapper2.add(btnBox3, gridConst);
						
						lblContenido2 = new JLabel("Contenido:");
						gridConst.gridx = 0;
						gridConst.gridy = 4;
						wrapper2.add(lblContenido2, gridConst);
						
						txtDatos1 = new JTextField();
						txtDatos1.setColumns(10);
						gridConst.gridx = 1;
						gridConst.gridy = 4;
						wrapper2.add(txtDatos1, gridConst);
						
						btnBuscarDocumento = new JButton("Buscar Material");
						gridConst.gridx = 1;
						gridConst.gridy = 5;
						wrapper2.add(btnBuscarDocumento, gridConst);
						btnBuscarDocumento.addActionListener(e3 -> {
							
							List<MaterialCapacitacion> listaVertices = controller.filtrar();
							
							JDialog emergente = new JDialog(); 
				        	GridBagConstraints gridConst1 = new GridBagConstraints();
							emergente.setSize(500,300);
							emergente.setAlwaysOnTop(true);
							emergente.setModal(true); emergente.setLocationRelativeTo(null);
							
							JPanel pan = new JPanel(); pan.setLayout(new GridBagLayout());
							
							JList list = new JList(listaVertices.toArray());
							list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
							//list.setLayoutOrientation(JList.HORIZONTAL_WRAP); 
							JScrollPane listScroller = new JScrollPane(list);
							listScroller.setBounds(list.getX(), list.getY(), 220, 80);
							listScroller.setAlignmentX(CENTER_ALIGNMENT);
							pan.add(listScroller);
							
							emergente.add(pan); emergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); emergente.setVisible(true);
							
						});
						
						btnAgregar = new JButton("Agregar");
						gridConst.gridx = 0;
						gridConst.gridy = 5;
						wrapper2.add(btnAgregar, gridConst);
						btnAgregar.addActionListener(e2 -> {
							
							Filtro f = new Filtro(txtDatos1.getText(),box1.getSelectedItem().toString(),btnBox3.getSelectedItem().toString());
							controller.addFiltro(f);
							
							});
						
						gridConst.gridx=0;
						gridConst.gridy=3;
						gridConst.gridwidth=4;
						wrapper.add(wrapper2,gridConst);
						
						wrapper2.repaint();
						
						this.doLayout();
						wrapper.doLayout();
						wrapper2.doLayout();
						
						break;}
					case Seccion:{
						
						wrapper.remove(wrapper2);
						
						wrapper2 = new JPanel(new GridBagLayout());
						
						lblFiltro2 = new JLabel("Tipo: ");
						gridConst.gridwidth = 1;
						gridConst.gridx = 0;
						gridConst.gridy = 3;
						wrapper2.add(lblFiltro2, gridConst);
						
						TipoArbol[] criterio3 = {TipoArbol.Seccion, TipoArbol.Parrafo};
						
						btnBox3 = new JComboBox(criterio3);
						gridConst.gridx = 1;
						gridConst.gridy = 3;
						wrapper2.add(btnBox3, gridConst);
						
						lblContenido2 = new JLabel("Contenido:");
						gridConst.gridx = 0;
						gridConst.gridy = 4;
						wrapper2.add(lblContenido2, gridConst);
						
						txtDatos1 = new JTextField();
						txtDatos1.setColumns(10);
						gridConst.gridx = 1;
						gridConst.gridy = 4;
						wrapper2.add(txtDatos1, gridConst);
						
						btnBuscarDocumento = new JButton("Buscar Material");
						gridConst.gridx = 1;
						gridConst.gridy = 5;
						wrapper2.add(btnBuscarDocumento, gridConst);
						btnBuscarDocumento.addActionListener(e3 -> {
							
							List<MaterialCapacitacion> listaVertices = controller.filtrar();
							
							JDialog emergente = new JDialog(); 
				        	GridBagConstraints gridConst1 = new GridBagConstraints();
							emergente.setSize(500,300);
							emergente.setAlwaysOnTop(true);
							emergente.setModal(true); emergente.setLocationRelativeTo(null);
							
							JPanel pan = new JPanel(); pan.setLayout(new GridBagLayout());
							
							JList list = new JList(listaVertices.toArray());
							list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
							//list.setLayoutOrientation(JList.HORIZONTAL_WRAP); 
							JScrollPane listScroller = new JScrollPane(list);
							listScroller.setBounds(list.getX(), list.getY(), 220, 80);
							listScroller.setAlignmentX(CENTER_ALIGNMENT);
							pan.add(listScroller);
							
							emergente.add(pan); emergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); emergente.setVisible(true);
							
						});
						
						btnAgregar = new JButton("Agregar");
						gridConst.gridx = 0;
						gridConst.gridy = 5;
						wrapper2.add(btnAgregar, gridConst);
						btnAgregar.addActionListener(e2 -> {
							
							Filtro f = new Filtro(txtDatos1.getText(),box1.getSelectedItem().toString(),btnBox3.getSelectedItem().toString());
							controller.addFiltro(f);
							
						});
				
						
						gridConst.gridx=0;
						gridConst.gridy=3;
						gridConst.gridwidth=4;
						wrapper.add(wrapper2,gridConst);
						
						wrapper2.repaint();
						
						this.doLayout();
						wrapper.doLayout();
						wrapper2.doLayout();
						
						break;}
				
				
				}
				});
				
				gridConst.gridx=0;
				gridConst.gridy=2;
				gridConst.gridwidth=4;
				this.add(wrapper,gridConst);
				
				this.repaint();
				wrapper.repaint();
				
				this.doLayout();
				wrapper.doLayout();
				
				break;}
			
			}
			
		});
		
	}
	

	public BusquedaController getController() {
		return controller;
	}

	public void setController(BusquedaController controller) {
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