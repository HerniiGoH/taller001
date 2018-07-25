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
	/*	private JButton btnAgregar;
	private JButton btnCancelar;*/

	//private MatTableModel tableModel;

	private MatController controller;
	
	public MatPanel() {
		this.setLayout(new GridBagLayout());
		//tableModel = new MatTableModel();
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
		
		/*lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblTitulo, gridConst);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(40);
		gridConst.gridx=1;
		gridConst.gridwidth=5;
		this.add(txtTitulo, gridConst);
		

		btnAgregar = new JButton("Agregar");
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
		
		
		lblCosto= new JLabel("Costo: ");		
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.weightx=0.0;
		this.add(lblCosto, gridConst);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(5);
		gridConst.gridx=1;
		this.add(txtCosto, gridConst);
		
		lblPrecioCompra= new JLabel("Precio Compra: ");
		gridConst.gridx=2;
		this.add(lblPrecioCompra, gridConst);
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setColumns(5);
		gridConst.gridx=3;
		this.add(txtPrecioCompra, gridConst);
		
		lblPaginas= new JLabel("Paginas: ");		
		gridConst.gridx=4;
		this.add(lblPaginas, gridConst);
		
		txtPaginas = new JTextField();
		txtPaginas.setColumns(5);
		gridConst.gridx=5;
		this.add(txtPaginas, gridConst);


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
	
	//public void setListaMats(List<Mat> matsLista,boolean actualizar) {
		//this.tableModel.setMats(matsLista);
		//if(actualizar) this.tableModel.fireTableDataChanged();
	//}

}