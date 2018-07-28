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
		
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblBusqueda = new JLabel("Busqueda Material");
		gridConst.gridx=0;
		gridConst.gridy=0;
		gridConst.anchor= GridBagConstraints.NORTHWEST;
		this.add(lblBusqueda, gridConst);
		
		lblTipo = new JLabel("Tipo: ");
		gridConst.gridx=0;
		gridConst.gridy=1;
		this.add(lblTipo, gridConst);
		
		/*lblLibro = new JLabel("Libro");
		gridConst.gridx=1;
		gridConst.gridy=1;
		this.add(lblLibro, gridConst);
		
		lblVideo = new JLabel("Video");
		gridConst.gridx=3;
		gridConst.gridy=1;
		this.add(lblVideo, gridConst);*/
		
		libro = new JRadioButton("Libro");
		gridConst.gridx=1;
		gridConst.gridy=1;
		this.add(libro, gridConst);
		
		video = new JRadioButton("Video");
		gridConst.gridx=2;
		gridConst.gridy=1;
		this.add(video, gridConst);
		
		ButtonGroup btnMats = new ButtonGroup();
		btnMats.add(libro);
		btnMats.add(video);
		libro.setSelected(true);
		
		lblCriterio = new JLabel("Criterio");
		gridConst.gridx=0;
		gridConst.gridy=2;
		gridConst.anchor= GridBagConstraints.NORTHWEST;
		this.add(lblCriterio, gridConst);
		
		txtCriterio = new JTextField();
		txtCriterio.setColumns(15);
		gridConst.gridx=2;
		gridConst.gridy=2;
		this.add(txtCriterio, gridConst);
		
		lblFechaMin = new JLabel("Fecha Minima");
		txtFechaMin = new JTextField();
		txtFechaMin.setColumns(6);
		lblFechaMax = new JLabel("Fecha Maxima");
		txtFechaMax = new JTextField();
		txtFechaMax.setColumns(6);
		
		Criterios[] criterios = {Criterios.ID,Criterios.Titulo,Criterios.Calificacion,Criterios.Tema,Criterios.RangoFechaDePublicacion};
		
		btnCriterio = new JComboBox(criterios);
		btnCriterio.addActionListener(e ->{
			switch ((Criterios) btnCriterio.getSelectedItem()) {
			case RangoFechaDePublicacion: 
				this.remove(txtCriterio);
				
				gridConst.gridwidth=1;
				gridConst.gridheight=1;
				gridConst.weighty=0.0;
				gridConst.weightx=0.0;
				gridConst.fill=GridBagConstraints.NONE;
				gridConst.anchor=GridBagConstraints.NORTHWEST;	
				
				gridConst.gridx=2;
				gridConst.gridy=2;
				this.add(lblFechaMin, gridConst);
				
				gridConst.gridx=3;
				gridConst.gridy=2;
				this.add(txtFechaMin, gridConst);
			
				gridConst.gridx=4;
				gridConst.gridy=2;
				this.add(lblFechaMax, gridConst);
				
				gridConst.gridx=5;
				gridConst.gridy=2;
				this.add(txtFechaMax, gridConst);
				this.repaint();
				this.doLayout();
				break;
			default: 
				this.remove(lblFechaMin);
				this.remove(txtFechaMin);
				this.remove(lblFechaMax);
				this.remove(txtFechaMax);
				gridConst.gridwidth=1;
				gridConst.gridheight=1;
				gridConst.weighty=0.0;
				gridConst.weightx=0.0;
				gridConst.fill=GridBagConstraints.NONE;
				gridConst.anchor=GridBagConstraints.NORTHWEST;	
				gridConst.gridx=2;
				gridConst.gridy=2;
				this.add(txtCriterio, gridConst);
				this.repaint();
				this.doLayout();
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
				if(this.libro.isSelected()) {
					switch ((Criterios) btnCriterio.getSelectedItem()) {
					case ID: 
						this.setListaLibros(this.controller.busqID(Integer.valueOf(txtCriterio.getText())), false);
						break;
					case Titulo:
						this.setListaLibros(this.controller.busqTitulo(txtCriterio.getText()), false);
						break;
					case Calificacion: 
						this.setListaLibros(this.controller.busqCalif(Integer.valueOf(txtCriterio.getText())), false);
						break;
					case Tema: 
						this.setListaLibros(this.controller.busqTema(txtCriterio.getText()), false);
						break;
					case RangoFechaDePublicacion: 
						this.setListaLibros(this.controller.busqFecha(Date.valueOf(txtFechaMin.getText()),Date.valueOf(txtFechaMax.getText())), false);
						break;
					default: break;
					};
					
					tabla = new JTable(this.libroTableModel);
					tabla.setFillsViewportHeight(true);
					
				}
				else {
					switch ((Criterios) btnCriterio.getSelectedItem()) {
					case ID:
						this.setListaVideos(this.controller.busqID1(Integer.valueOf(txtCriterio.getText())), false); 
						break;
					case Titulo:
						this.setListaVideos(this.controller.busqTitulo1(txtCriterio.getText()), false);
						break;
					case Calificacion:
						this.setListaVideos(this.controller.busqCalif1(Integer.valueOf(txtCriterio.getText())), false);
						break;
					case Tema:
						this.setListaVideos(this.controller.busqTema1(txtCriterio.getText()), false);
						break;
					case RangoFechaDePublicacion:
						this.setListaVideos(this.controller.busqFecha1(Date.valueOf(txtFechaMin.getText()),Date.valueOf(txtFechaMax.getText())), false);
						break;
					default: break;
					};
					
					tabla = new JTable(this.videoTableModel);
					tabla.setFillsViewportHeight(true);
					
				}
				
				this.remove(scrollPane);
				scrollPane = new JScrollPane(tabla);
				gridConst.gridx=0;
				gridConst.gridwidth=7;	
				gridConst.gridy=7;
				gridConst.weighty=1.0;
				gridConst.weightx=1.0;
				gridConst.fill=GridBagConstraints.BOTH;
				gridConst.anchor=GridBagConstraints.PAGE_START;		
				this.add(scrollPane, gridConst);
				
				this.repaint();
				this.doLayout();
				
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos invalidos", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.NORTHWEST;
		gridConst.gridx=3;
		this.add(btnBuscar, gridConst);
		
		tabla = new JTable(this.videoTableModel);
		tabla.setFillsViewportHeight(true);
		
		scrollPane = new JScrollPane(tabla);
		gridConst.gridx=0;
		gridConst.gridwidth=7;	
		gridConst.gridy=7;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
		
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