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
	
	private LibroTableModel libroTableModel;
	private VideoTableModel videoTableModel;

	private MatController controller;
	
	public BusquedaPanel() {
		this.setLayout(new GridBagLayout());
		libroTableModel = new LibroTableModel();
		videoTableModel = new VideoTableModel();
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
				
				lblFiltro1 = new JLabel("Filtrar por:");
				gridConst.gridx = 0;
				gridConst.gridy = 2;
				this.add(lblFiltro1, gridConst);
				
				TipoArbol[] criterio2 = {TipoArbol.Autor, TipoArbol.Editorial, TipoArbol.Fecha_Publicacion, TipoArbol.Palabras_Claves};
				
				btnBox2 = new JComboBox(criterio2);
				gridConst.gridx = 1;
				gridConst.gridy = 2;
				gridConst.gridwidth = 2;
				this.add(btnBox2, gridConst);
				
				lblContenido1 = new JLabel("Contenido:");
				gridConst.gridx = 0;
				gridConst.gridy = 3;
				this.add(lblContenido1, gridConst);
				
				txtDatos1 = new JTextField();
				gridConst.gridx = 1;
				gridConst.gridy = 3;
				gridConst.gridwidth = 2;
				this.add(txtDatos1, gridConst);
				
				btnAgregar = new JButton("Agregar");
				gridConst.gridx = 0;
				gridConst.gridy = 5;
				this.add(btnAgregar, gridConst);
				btnAgregar.addActionListener(e1 -> {
					
					Filtro f = new Filtro(txtDatos1.getText(),box1.getSelectedItem().toString(),btnBox2.getSelectedItem().toString());
					controller.addFiltro(f);
					
				});
				break;}
			case Resumen:{
				
				lblContenido1 = new JLabel("Contenido del parrafo:");
				gridConst.gridx = 0;
				gridConst.gridy = 2;
				this.add(lblContenido1, gridConst);
				
				txtDatos1= new JTextField ();
				gridConst.gridx = 1;
				gridConst.gridy = 2;
				gridConst.gridheight=2;
				gridConst.gridwidth=2;
				this.add(txtDatos1, gridConst);
				
				btnAgregar= new JButton("Agregar");
				gridConst.gridx = 0;
				gridConst.gridy = 4;
				this.add(btnAgregar, gridConst);
				
				btnAgregar.addActionListener(e1 -> {
					Filtro f = new Filtro(txtDatos1.getText(),box1.getSelectedItem().toString(),TipoArbol.Parrafo.toString());
					controller.addFiltro(f);
				});
				
				break;}
			case Capitulos:{
				
				lblFiltro1 = new JLabel("Filtrar por:");
				gridConst.gridx = 0;
				gridConst.gridy = 2;
				this.add(lblFiltro1, gridConst);
				
				TipoArbol[] criterio2 = {TipoArbol.Capitulos, TipoArbol.Metadatos, TipoArbol.Seccion};
				
				btnBox2 = new JComboBox(criterio2);
				gridConst.gridx = 1;
				gridConst.gridy = 2;
				this.add(btnBox2, gridConst);
				
				switch((TipoArbol)btnBox2.getSelectedItem()) {
					case Capitulos:{
						lblContenido1 = new JLabel("Contenido:");
						gridConst.gridx = 0;
						gridConst.gridy = 3;
						this.add(lblContenido1, gridConst);
						
						txtDatos1 = new JTextField();
						gridConst.gridx = 1;
						gridConst.gridy = 3;
						gridConst.gridwidth = 2;
						this.add(txtDatos1, gridConst);
						
						btnAgregar = new JButton("Agregar");
						gridConst.gridx = 0;
						gridConst.gridy = 5;
						this.add(btnAgregar, gridConst);
						btnAgregar.addActionListener(e1 -> {
							
							Filtro f = new Filtro(txtDatos1.getText(),box1.getSelectedItem().toString(),btnBox2.getSelectedItem().toString());
							controller.addFiltro(f);
							
						});
						break;}
					
					case Metadatos:{
						
						lblFiltro2 = new JLabel("Tipo: ");
						gridConst.gridx = 0;
						gridConst.gridy = 3;
						this.add(lblFiltro2, gridConst);
						
						TipoArbol[] criterio3 = {TipoArbol.Palabras_Claves, TipoArbol.Sitios_Web_Relacionados, TipoArbol.Sitios_Web_Ejercicios_Relacionados};
						
						btnBox3 = new JComboBox(criterio3);
						gridConst.gridx = 1;
						gridConst.gridy = 3;
						gridConst.gridwidth = 2;
						this.add(btnBox3, gridConst);
						
						lblContenido2 = new JLabel("Contenido:");
						gridConst.gridx = 0;
						gridConst.gridy = 4;
						this.add(lblContenido2, gridConst);
						
						txtDatos1 = new JTextField();
						gridConst.gridx = 1;
						gridConst.gridy = 4;
						gridConst.gridwidth = 2;
						this.add(txtDatos1, gridConst);
						
						btnAgregar = new JButton("Agregar");
						gridConst.gridx = 0;
						gridConst.gridy = 5;
						this.add(btnAgregar, gridConst);
						btnAgregar.addActionListener(e1 -> {
							
							Filtro f = new Filtro(txtDatos1.getText(),box1.getSelectedItem().toString(),btnBox3.getSelectedItem().toString());
							controller.addFiltro(f);
							
							});
						
						
						
						break;}
					case Seccion:{
						
						lblFiltro2 = new JLabel("Tipo: ");
						gridConst.gridx = 0;
						gridConst.gridy = 3;
						this.add(lblFiltro2, gridConst);
						
						TipoArbol[] criterio3 = {TipoArbol.Seccion, TipoArbol.Parrafo};
						
						btnBox3 = new JComboBox(criterio3);
						gridConst.gridx = 1;
						gridConst.gridy = 3;
						gridConst.gridwidth = 2;
						this.add(btnBox3, gridConst);
						
						lblContenido2 = new JLabel("Contenido:");
						gridConst.gridx = 0;
						gridConst.gridy = 4;
						this.add(lblContenido2, gridConst);
						
						txtDatos1 = new JTextField();
						gridConst.gridx = 1;
						gridConst.gridy = 4;
						gridConst.gridwidth = 2;
						this.add(txtDatos1, gridConst);
						
						btnAgregar = new JButton("Agregar");
						gridConst.gridx = 0;
						gridConst.gridy = 5;
						this.add(btnAgregar, gridConst);
						btnAgregar.addActionListener(e1 -> {
							
							Filtro f = new Filtro(txtDatos1.getText(),box1.getSelectedItem().toString(),btnBox3.getSelectedItem().toString());
							controller.addFiltro(f);
							
							});
						
						
						
						break;}
				
				}
				
				break;}
			
			}
			
		});
		
		
		
		
		
		
	}
	

	public MatController getController() {
		return controller;
	}

	public void setController(MatController controller) {
		this.controller = controller;
	}
	
	public void setListaLibros(List<Libro> matLista,boolean actualizar) {
		this.libroTableModel.setLibros(matLista);
		if(actualizar) this.libroTableModel.fireTableDataChanged();
	}
	
	public void setListaVideos(List<Video> matLista,boolean actualizar) {
		this.videoTableModel.setVideos(matLista);
		if(actualizar) this.videoTableModel.fireTableDataChanged();
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