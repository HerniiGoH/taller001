package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Date;
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

import frsf.isi.died.app.controller.Criterios;
import frsf.isi.died.app.controller.MatController;
import frsf.isi.died.app.controller.Ordenamiento;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public class MatPanel extends JPanel{
	
	/*private JScrollPane scrollPane;
	private JTable tabla;*/
	private JLabel lblBusqueda;
	private JLabel lblTipo;
	private JLabel lblVideo;
	private JLabel lblLibro;
	private JRadioButton video;
	private JRadioButton libro;
	private JLabel lblCriterio;
	private JComboBox btnCriterio;
	private JTextField txtCriterio;
	private JLabel lblFechaMin;
	private JTextField txtFechaMin;
	private JLabel lblFechaMax;
	private JTextField txtFechaMax;
	private JLabel lblOrdenarPor;
	private JComboBox btnOrdenam;
	private JButton btnBuscar;
	private JTable tabla;
	private JScrollPane scrollPane;
	/*	private JButton btnAgregar;
	private JButton btnCancelar;*/

	private LibroTableModel libroTableModel;
	private VideoTableModel videoTableModel;

	private MatController controller;
	
	public MatPanel() {
		this.setLayout(new GridBagLayout());
		libroTableModel = new LibroTableModel();
		videoTableModel = new VideoTableModel();
	}
	
	@SuppressWarnings("deprecation")
	public void construir() {
		this.removeAll();
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblBusqueda = new JLabel("Busqueda Material");
		gridConst.gridx=0;
		gridConst.gridy=0;
		gridConst.anchor= GridBagConstraints.LINE_START;
		this.add(lblBusqueda, gridConst);
		
		lblTipo = new JLabel("Tipo: ");
		gridConst.gridx=0;
		gridConst.gridy=1;
		this.add(lblTipo, gridConst);
		
		lblLibro = new JLabel("Libro");
		gridConst.gridx=1;
		gridConst.gridy=1;
		this.add(lblLibro, gridConst);
		
		lblVideo = new JLabel("Video");
		gridConst.gridx=3;
		gridConst.gridy=1;
		this.add(lblVideo, gridConst);
		
		libro = new JRadioButton();
		gridConst.gridx=2;
		gridConst.gridy=1;
		this.add(libro, gridConst);
		
		video = new JRadioButton();
		gridConst.gridx=4;
		gridConst.gridy=1;
		this.add(video, gridConst);
		
		ButtonGroup btnMats = new ButtonGroup();
		btnMats.add(libro);
		btnMats.add(video);
		libro.setSelected(true);
		
		lblCriterio = new JLabel("Criterio");
		gridConst.gridx=0;
		gridConst.gridy=2;
		this.add(lblCriterio, gridConst);
		
		txtCriterio = new JTextField();
		txtCriterio.setColumns(20);
		gridConst.gridx=3;
		gridConst.gridy=2;
		this.add(txtCriterio, gridConst);
		
		lblFechaMin = new JLabel("Fecha Minima");
		txtFechaMin = new JTextField();
		txtFechaMin.setColumns(1);
		lblFechaMax = new JLabel("Fecha Maxima");
		txtFechaMax = new JTextField();
		txtFechaMax.setColumns(1);
		
		Criterios[] criterios = {Criterios.Titulo,Criterios.Calificacion,Criterios.Tema,Criterios.RangoFechaDePublicacion};
		
		btnCriterio = new JComboBox(criterios);
		btnCriterio.addActionListener(e ->{
			switch ((Criterios) btnCriterio.getSelectedItem()) {
			case RangoFechaDePublicacion: 
				this.remove(txtCriterio);
				
				gridConst.gridx=2;
				gridConst.gridy=2;
				this.add(lblFechaMin, gridConst);
				
				/*gridConst.gridx=3;
				gridConst.gridy=2;
				gridConst.weightx=0.0;
				this.add(txtFechaMin, gridConst);
			
				gridConst.gridx=5;
				gridConst.gridy=2;
				this.add(lblFechaMax, gridConst);
				
				gridConst.gridx=6;
				gridConst.gridy=2;
				gridConst.weightx=0.0;
				gridConst.weighty=0.0;
				this.add(txtFechaMax, gridConst);*/
				
				this.repaint();
				break;
			default: 
				this.remove(lblFechaMin);
				this.remove(txtFechaMin);
				this.remove(lblFechaMax);
				this.remove(txtFechaMax);
				gridConst.gridx=3;
				gridConst.gridy=2;
				this.add(txtCriterio, gridConst);
				this.repaint();
				break;
			};
		});
		gridConst.gridx=1;
		gridConst.gridy=2;
		this.add(btnCriterio, gridConst);
		
		lblOrdenarPor = new JLabel("Ordenar por");
		gridConst.gridx=0;
		gridConst.gridy=3;
		this.add(lblOrdenarPor, gridConst);
		
Ordenamiento[] critOrd = {Ordenamiento.TituloAlfabeticamente, Ordenamiento.Calificacion, Ordenamiento.Precio, Ordenamiento.FechaDePublicacion, Ordenamiento.Relevancia};
		
		btnOrdenam = new JComboBox(critOrd);
		gridConst.gridx=1;
		gridConst.gridy=3;
		this.add(btnOrdenam, gridConst);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> {
			try {
				String criterio = txtCriterio.getText();
				
				Date fechaMinima = Date.valueOf(txtFechaMin.getText());
				Date fechaMaxima = Date.valueOf(txtFechaMax.getText());
				
				switch ((Criterios) btnCriterio.getSelectedItem()) {
				case Titulo: this.controller.busqTitulo(criterio);
					break;
				case Calificacion: this.controller.busqCalif(Integer.valueOf(criterio));
					break;
				case Tema: this.controller.busqTema(criterio);
					break;
				case RangoFechaDePublicacion: this.controller.busqFecha(fechaMinima,fechaMaxima);
					break;
				default: break;
				};
				
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos invalido", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.gridx=3;
		this.add(btnBuscar, gridConst);
		
		tabla = new JTable(this.videoTableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=7;	
		gridConst.gridy=7;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
		
	}
	
	/*public void rePintar() {
		
		this.removeAll();
		
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblBusqueda = new JLabel("Busqueda Material");
		gridConst.gridx=0;
		gridConst.gridy=0;
		gridConst.weightx=1.0;
		this.add(lblBusqueda, gridConst);
		
		lblTipo = new JLabel("Tipo: ");
		gridConst.gridx=0;
		gridConst.gridy=1;
		this.add(lblTipo, gridConst);
		
		lblLibro = new JLabel("Libro");
		gridConst.gridx=1;
		gridConst.gridy=1;
		this.add(lblLibro, gridConst);
		
		lblVideo = new JLabel("Video");
		gridConst.gridx=3;
		gridConst.gridy=1;
		this.add(lblVideo, gridConst);
		
		libro = new JRadioButton();
		gridConst.gridx=2;
		gridConst.gridy=1;
		this.add(libro, gridConst);
		
		video = new JRadioButton();
		gridConst.gridx=4;
		gridConst.gridy=1;
		this.add(video, gridConst);
		
		ButtonGroup btnMats = new ButtonGroup();
		btnMats.add(libro);
		btnMats.add(video);
		libro.setSelected(true);
		
		lblCriterio = new JLabel("Criterio");
		gridConst.gridx=0;
		gridConst.gridy=2;
		this.add(lblCriterio, gridConst);
		
		Criterios[] criterios = {Criterios.Titulo,Criterios.Calificacion,Criterios.Tema,Criterios.RangoFechaDePublicacion};
		
		btnCriterio = new JComboBox(criterios);
		btnCriterio.addActionListener(e ->{
			switch ((Criterios) btnCriterio.getSelectedItem()) {
			case RangoFechaDePublicacion: controller.rePintar();	
				break;
			default: controller.pintar();
				break;
			};
		});
		gridConst.gridx=1;
		gridConst.gridy=2;
		this.add(btnCriterio, gridConst);
		
		lblFechaMin = new JLabel("Fecha Minima");
		gridConst.gridx=2;
		gridConst.gridy=2;
		this.add(lblFechaMin, gridConst);
		
		txtFechaMin = new JTextField();
		txtFechaMin.setColumns(15);
		gridConst.gridx=3;
		gridConst.gridy=2;
		this.add(txtFechaMin, gridConst);
		
		lblFechaMax = new JLabel("Fecha Maxima");
		gridConst.gridx=4;
		gridConst.gridy=2;
		this.add(lblFechaMax, gridConst);
		
		txtFechaMax = new JTextField();
		txtFechaMax.setColumns(15);
		gridConst.gridx=5;
		gridConst.gridy=2;
		this.add(txtFechaMax, gridConst);
		
		lblOrdenarPor = new JLabel("Ordenar por");
		gridConst.gridx=0;
		gridConst.gridy=3;
		this.add(lblOrdenarPor, gridConst);
		
Ordenamiento[] critOrd = {Ordenamiento.TituloAlfabeticamente, Ordenamiento.Calificacion, Ordenamiento.Precio, Ordenamiento.FechaDePublicacion, Ordenamiento.Relevancia};
		
		btnOrdenam = new JComboBox(critOrd);
		gridConst.gridx=1;
		gridConst.gridy=3;
		this.add(btnOrdenam, gridConst);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> {
			try {
				String criterio = txtCriterio.getText();
				
				Date fechaMinima = Date.valueOf(txtFechaMin.getText());
				Date fechaMaxima = Date.valueOf(txtFechaMax.getText());
				
				switch ((Criterios) btnCriterio.getSelectedItem()) {
				case Titulo: this.controller.busqTitulo(criterio);
					break;
				case Calificacion: this.controller.busqCalif(Integer.valueOf(criterio));
					break;
				case Tema: this.controller.busqTema(criterio);
					break;
				case RangoFechaDePublicacion: this.controller.busqFecha(fechaMinima,fechaMaxima);
					break;
				default: break;
				};
				
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos invalido", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.gridx=3;
		this.add(btnBuscar, gridConst);
		
		tabla = new JTable(this.videoTableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=7;	
		gridConst.gridy=7;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
		
	}*/

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

}