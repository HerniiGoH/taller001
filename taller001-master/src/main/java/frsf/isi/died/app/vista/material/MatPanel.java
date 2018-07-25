package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	
	public void construir() {
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
		gridConst.gridx=1;
		gridConst.gridy=2;
		this.add(btnCriterio, gridConst);
		
		txtCriterio = new JTextField();
		txtCriterio.setColumns(20);
		gridConst.gridx=3;
		gridConst.gridy=2;
		this.add(txtCriterio, gridConst);
		
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
				
				switch ((Criterios) btnCriterio.getSelectedItem()) {
				
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
		
		
		/*btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener( e ->{
			try {
				Double costo = Double.valueOf(txtCosto.getText());
				Double precio = Double.valueOf(txtPrecioCompra.getText());
				Integer paginas = Integer.valueOf(txtPaginas.getText());
				//controller.agregarMat(txtTitulo.getText(), costo, precio, paginas);
				txtTitulo.setText("");
				txtCosto.setText("");
				txtPrecioCompra.setText("");
				txtPaginas.setText("");
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.gridx=6;
		this.add(btnAgregar, gridConst);
		
		btnCancelar= new JButton("Cancelar");
		btnCancelar.addActionListener(e -> {
			txtTitulo.setText("");
			txtCosto.setText("");
			txtPaginas.setText("");
			txtPrecioCompra.setText("");
		});
		gridConst.gridx=6;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(btnCancelar, gridConst);
		
		//tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=7;	
		gridConst.gridy=2;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);*/
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

}