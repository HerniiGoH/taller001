package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.app.controller.Relevancia;
import frsf.isi.died.app.controller.WishController;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Video;

public class WishPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JTable tabla;
	private JRadioButton libro;
	private JRadioButton video;

	private LibroTableModel libroTableModel;
	private VideoTableModel videoTableModel;

	private WishController controller;
	
	public WishPanel() {
		this.setLayout(new GridBagLayout());
		libroTableModel = new LibroTableModel();
		videoTableModel = new VideoTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst= new GridBagConstraints();
		
		libro = new JRadioButton("Libros");
		libro.addActionListener(e->{
			this.remove(scrollPane);
			tabla = new JTable(this.libroTableModel);
			tabla.setFillsViewportHeight(true);
			scrollPane= new JScrollPane(tabla);
			
			gridConst.gridx=0;
			gridConst.gridwidth=3;	
			gridConst.gridy=2;
			gridConst.weighty=1.0;
			gridConst.weightx=1.0;
			gridConst.fill=GridBagConstraints.BOTH;
			gridConst.anchor=GridBagConstraints.PAGE_START;		
			this.add(scrollPane, gridConst);
			
			this.repaint();
			this.doLayout();
		});
		gridConst.gridx=0;
		this.add(libro, gridConst);
		
		video = new JRadioButton("Videos");
		video.addActionListener(e1->{
			
			this.remove(scrollPane);
			
			tabla = new JTable(this.videoTableModel);
			tabla.setFillsViewportHeight(true);
			scrollPane= new JScrollPane(tabla);
			
			gridConst.gridx=0;
			gridConst.gridwidth=3;	
			gridConst.gridy=2;
			gridConst.weighty=1.0;
			gridConst.weightx=1.0;
			gridConst.fill=GridBagConstraints.BOTH;
			gridConst.anchor=GridBagConstraints.PAGE_START;		
			this.add(scrollPane, gridConst);
			
			this.repaint();
			this.doLayout();
		});
		gridConst.gridx=1;
		this.add(video, gridConst);
		
		ButtonGroup btnMats = new ButtonGroup();
		btnMats.add(libro);
		btnMats.add(video);
		video.setSelected(true);
		
		tabla = new JTable(this.videoTableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=3;	
		gridConst.gridy=2;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
	}

	public WishController getController() {
		return controller;
	}

	public void setController(WishController controller) {
		this.controller = controller;
	}
	
	public void setListaLibros(List<Libro> librosLista,boolean actualizar) {
		this.libroTableModel.setLibros(librosLista);
		if(actualizar) this.libroTableModel.fireTableDataChanged();
	}
	public void setListaVideos(List<Video> videosLista,boolean actualizar) {
		this.videoTableModel.setVideos(videosLista);
		if(actualizar) this.videoTableModel.fireTableDataChanged();
	}

}