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

import frsf.isi.died.app.controller.Criterios;
import frsf.isi.died.app.controller.MatController;
import frsf.isi.died.app.controller.Ordenamiento;
import frsf.isi.died.app.controller.Relevancia;
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
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnWishlist;
	private JDialog emergente;

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
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
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
		libro.addActionListener(e->{
			this.btnActualizar.setEnabled(false);
			this.btnEliminar.setEnabled(false);
			this.btnWishlist.setEnabled(false);
		});
		gridConst.gridx=1;
		gridConst.gridy=1;
		this.add(libro, gridConst);
		
		video = new JRadioButton("Video");
		video.addActionListener(e->{
			this.btnActualizar.setEnabled(false);
			this.btnEliminar.setEnabled(false);
			this.btnWishlist.setEnabled(false);
		});
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
						this.setListaLibros(this.controller.busqID(Integer.valueOf(txtCriterio.getText()), (Ordenamiento) btnOrdenam.getSelectedItem()), false);
						break;
					case Titulo:
						this.setListaLibros(this.controller.busqTitulo(txtCriterio.getText(), (Ordenamiento) btnOrdenam.getSelectedItem()), false);
						break;
					case Calificacion: 
						this.setListaLibros(this.controller.busqCalif(Integer.valueOf(txtCriterio.getText()), (Ordenamiento) btnOrdenam.getSelectedItem()), false);
						break;
					case Tema: 
						this.setListaLibros(this.controller.busqTema(txtCriterio.getText(), (Ordenamiento) btnOrdenam.getSelectedItem()), false);
						break;
					case RangoFechaDePublicacion: 
						//try {
							Date fechaMin = formatter.parse(txtFechaMin.getText());
							Date fechaMax = formatter.parse(txtFechaMax.getText());
							this.setListaLibros(this.controller.busqFecha(fechaMin,fechaMax, (Ordenamiento) btnOrdenam.getSelectedItem()), false);
						//} catch (ParseException g) {
						//	g.printStackTrace();
						//}
						break;
					default: break;
					};
					
					tabla = new JTable(this.libroTableModel);
					tabla.setFillsViewportHeight(true);
					
				}
				else {
					switch ((Criterios) btnCriterio.getSelectedItem()) {
					case ID:
						this.setListaVideos(this.controller.busqID1(Integer.valueOf(txtCriterio.getText()), (Ordenamiento) btnOrdenam.getSelectedItem()), false); 
						break;
					case Titulo:
						this.setListaVideos(this.controller.busqTitulo1(txtCriterio.getText(), (Ordenamiento) btnOrdenam.getSelectedItem()), false);
						break;
					case Calificacion:
						this.setListaVideos(this.controller.busqCalif1(Integer.valueOf(txtCriterio.getText()), (Ordenamiento) btnOrdenam.getSelectedItem()), false);
						break;
					case Tema:
						this.setListaVideos(this.controller.busqTema1(txtCriterio.getText(), (Ordenamiento) btnOrdenam.getSelectedItem()), false);
						break;
					case RangoFechaDePublicacion:
						//try {
							Date fechaMin = formatter.parse(txtFechaMin.getText());
							Date fechaMax = formatter.parse(txtFechaMax.getText());
							this.setListaVideos(this.controller.busqFecha1(fechaMin,fechaMax, (Ordenamiento) btnOrdenam.getSelectedItem()), false);
						//} catch (ParseException g) {
							//g.printStackTrace();
						//}
						break;
					default: break;
					};
					
					tabla = new JTable(this.videoTableModel);
					tabla.setFillsViewportHeight(true);
					
				}
				this.btnActualizar.setEnabled(true);
				this.btnEliminar.setEnabled(true);
				this.btnWishlist.setEnabled(true);
				this.remove(scrollPane);
				scrollPane = new JScrollPane(tabla);
				gridConst.gridx=0;
				gridConst.gridwidth=10;	
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
		gridConst.gridx=5;
		this.add(btnBuscar, gridConst);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(e->{
			if(tabla.getSelectedRow()!=-1) {
				emergente = new JDialog(); GridBagConstraints gridConst1 = new GridBagConstraints();
				
				emergente.setSize(900, 300);
				emergente.setAlwaysOnTop(true);
				emergente.setModal(true);
				emergente.setLocationRelativeTo(null);
				
				JPanel pan = new JPanel();
				pan.setLayout(new GridBagLayout());
				JLabel lbltit = new JLabel("Titulo");
				gridConst1.gridx = 0;
				gridConst1.gridy = 0;
				gridConst1.anchor = GridBagConstraints.LINE_START;
				pan.add(lbltit,gridConst1);
				
				JTextField txttit = new JTextField(this.tabla.getValueAt(this.tabla.getSelectedRow(), 1).toString());
				txttit.setColumns(20);
				gridConst1.gridx = 1;
				gridConst1.gridwidth = 6;
				pan.add(txttit,gridConst1);
				
				JLabel lblprecom = new JLabel("Precio Compra");
				gridConst1.gridwidth = 1;
				gridConst1.gridx = 0;
				gridConst1.gridy = 1;
				pan.add(lblprecom,gridConst1);
				
				JTextField txtprecom = new JTextField(this.tabla.getValueAt(this.tabla.getSelectedRow(), 2).toString());
				txtprecom.setColumns(5);
				gridConst1.gridx = 1;
				pan.add(txtprecom,gridConst1);
				
				JTextField txtcostopub = new JTextField(this.tabla.getValueAt(this.tabla.getSelectedRow(), 3).toString());
				txtcostopub.setColumns(5);
				gridConst1.gridx = 3;
				gridConst1.gridy = 1;
				pan.add(txtcostopub,gridConst1);
				
				if(this.libro.isSelected()) {
					JLabel lblcostopub = new JLabel("Costo Publicacion");
					gridConst1.gridx = 2;
					gridConst1.gridy = 1;
					pan.add(lblcostopub,gridConst1);
					
					JLabel lblpaginas = new JLabel("Paginas");
					gridConst1.gridx = 4;
					gridConst1.gridy = 1;
					pan.add(lblpaginas,gridConst1);
				}
				else {
					JLabel lblcostopub = new JLabel("Costo Por Segundo");
					gridConst1.gridx = 2;
					gridConst1.gridy = 1;
					pan.add(lblcostopub,gridConst1);
					
					JLabel lblpaginas = new JLabel("Duracion");
					gridConst1.gridx = 4;
					gridConst1.gridy = 1;
					pan.add(lblpaginas,gridConst1);
					
					txtcostopub.setEnabled(false);
				}
				
				JTextField txtpaginas = new JTextField(this.tabla.getValueAt(this.tabla.getSelectedRow(), 4).toString());
				txtpaginas.setColumns(5);
				gridConst1.gridx = 5;
				gridConst1.gridy = 1;
				pan.add(txtpaginas,gridConst1);
				
				JLabel lblfecha = new JLabel("Fecha de Publicacion");
				gridConst1.gridx = 6;
				gridConst1.gridy = 1;
				pan.add(lblfecha,gridConst1);
				 //this.tabla.getValueAt(this.tabla.getSelectedRow(), 6).toString()
				JTextField txtfecha = new JTextField(DateFormat.getDateInstance().format(this.tabla.getValueAt(this.tabla.getSelectedRow(), 6)));
				txtfecha.setColumns(6);
				gridConst1.gridx = 7;
				pan.add(txtfecha,gridConst1);
				
				JLabel lblrel = new JLabel("Relevancia");
				gridConst1.gridx = 8;
				gridConst1.gridy = 1;
				pan.add(lblrel,gridConst1);
				
				Relevancia[] rele = {Relevancia.Alta,Relevancia.Media,Relevancia.Baja};
				
				JComboBox btnrel = new JComboBox(rele);
				gridConst1.gridx = 9;
				pan.add(btnrel,gridConst1);
				
				JButton btnactualizar = new JButton("Actualizar");
				btnactualizar.addActionListener(e2->{
					try {
						Double costo = Double.valueOf(txtcostopub.getText());
						Double precio = Double.valueOf(txtprecom.getText());
						Integer paginas = Integer.valueOf(txtpaginas.getText());
						String date = txtfecha.getText();
						Date fecha = inicializar(date);
						if(esValido(date)) {
							fecha = inicializar(date);
						}
						else {
							throw new Exception("Fecha invalida");
						}
						if(this.libro.isSelected()) {
						Libro aux = libroTableModel.getLibros().get(tabla.getSelectedRow());
						Libro aux2 = aux;
						List<Libro> aux1 = this.libroTableModel.getLibros();
						aux1.remove(aux);
						aux.setTitulo(txttit.getText());
						aux.setCosto(costo);
						aux.setPrecioCompra(precio);
						aux.setPaginas(paginas);
						aux.setRelevancia((Relevancia) btnrel.getSelectedItem());
						aux1.add(aux);
						this.setListaLibros(aux1, false);
						controller.editarLibro(aux2,aux);
						
						}
						else {
							
							Video aux = videoTableModel.getVideos().get(tabla.getSelectedRow());
							Video aux2 = aux;
							List<Video> aux1 = this.videoTableModel.getVideos();
							aux1.remove(aux);
							aux.setTitulo(txttit.getText());
							aux.setCosto(costo);
							aux.setDuracion(paginas);
							aux.setRelevancia((Relevancia) btnrel.getSelectedItem());
							aux1.add(aux);
							this.setListaVideos(aux1, false);
							controller.editarVideo(aux2,aux);
						}
						emergente.dispose();
						this.repaint();
						this.doLayout();
					}catch(Exception ex) {
					    JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
					}
				});
				gridConst1.gridx = 4;
				gridConst1.gridy = 2;
				pan.add(btnactualizar,gridConst1);
				
				JButton btncancelar = new JButton("Cancelar");
				btncancelar.addActionListener(e1->{
					emergente.dispose();
				});
				gridConst1.gridx = 5;
				gridConst1.gridy = 2;
				pan.add(btncancelar,gridConst1);
				
				emergente.add(pan);
				
				emergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				emergente.setVisible(true);
			}
		});
		gridConst.gridx=6;
		this.add(btnActualizar, gridConst);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(e->{
			
			if(this.libro.isSelected()) {
				Libro aux = libroTableModel.getLibros().get(tabla.getSelectedRow());
				this.controller.eliminar(aux);
				this.libroTableModel.getLibros().remove(aux);
				this.setListaLibros(libroTableModel.getLibros(), false);	
			}
			else{
				Video aux = videoTableModel.getVideos().get(tabla.getSelectedRow());
				this.controller.eliminar1(aux);
				this.videoTableModel.getVideos().remove(aux);
				this.setListaVideos(videoTableModel.getVideos(), false);
			}
			
			this.repaint();
			this.doLayout();
			
		});
		gridConst.gridx=7;
		this.add(btnEliminar, gridConst);
		
		btnWishlist = new JButton("Agregar a Wishlist");
		btnWishlist.addActionListener(e->{
			if(this.libro.isSelected()) {
				this.controller.addWishlist(libroTableModel.getLibros().get(tabla.getSelectedRow()));
			}
			else {
				this.controller.addWishlist1(videoTableModel.getVideos().get(tabla.getSelectedRow()));
			}
		});
		gridConst.gridx=8;
		this.add(btnWishlist, gridConst);
		
		tabla = new JTable(this.libroTableModel);
		tabla.setFillsViewportHeight(true);
		
		scrollPane = new JScrollPane(tabla);
		gridConst.gridx=0;
		gridConst.gridwidth=10;	
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