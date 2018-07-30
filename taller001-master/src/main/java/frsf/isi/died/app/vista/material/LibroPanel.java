package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.app.controller.Relevancia;
import frsf.isi.died.tp.modelo.productos.Libro;

public class LibroPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JTable tabla;
	private JLabel lblTitulo;
	private JLabel lblCosto;
	private JLabel lblPrecioCompra;
	private JLabel lblPaginas;
	private JTextField txtTitulo;
	private JTextField txtCosto;
	private JTextField txtPrecioCompra;
	private JTextField txtPaginas;
	private JButton btnAgregar;
	private JButton btnCancelar;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblRelevancia;
	private JComboBox btnRelevancia;

	private LibroTableModel tableModel;

	private LibroController controller;
	
	public LibroPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new LibroTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblTitulo, gridConst);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(40);
		gridConst.gridx=1;
		gridConst.gridwidth=6;
		this.add(txtTitulo, gridConst);
		

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener( e ->{
			try {
				Double costo = Double.valueOf(txtCosto.getText());
				Double precio = Double.valueOf(txtPrecioCompra.getText());
				Integer paginas = Integer.valueOf(txtPaginas.getText());
				String date = txtFecha.getText();
				Date fecha;
				if(esValido(date)) {
					fecha = inicializar(date);
				}
				else {
					throw new Exception("Fecha invalida");
				}
				controller.agregarLibro(txtTitulo.getText(), costo, precio, paginas, fecha, (Relevancia) btnRelevancia.getSelectedItem());
				txtTitulo.setText("");
				txtCosto.setText("");
				txtPrecioCompra.setText("");
				txtPaginas.setText("");
				txtFecha.setText("");
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.gridx=10;
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
		
		lblFecha = new JLabel("Fecha de Publicacion");
		gridConst.gridx=6;
		this.add(lblFecha,gridConst);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(6);
		gridConst.gridx = 7;
		this.add(txtFecha, gridConst);
		
		lblRelevancia = new JLabel("Relevancia");
		gridConst.gridx=8;
		this.add(lblRelevancia, gridConst);
		
		Relevancia[] rele = {Relevancia.Alta,Relevancia.Media,Relevancia.Baja};
		btnRelevancia = new JComboBox(rele);
		gridConst.gridx=9;
		this.add(btnRelevancia, gridConst);

		btnCancelar= new JButton("Cancelar");
		btnCancelar.addActionListener(e -> {
			txtTitulo.setText("");
			txtCosto.setText("");
			txtPaginas.setText("");
			txtPrecioCompra.setText("");
			txtFecha.setText("");
		});
		gridConst.gridx=10;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(btnCancelar, gridConst);
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=11;	
		gridConst.gridy=2;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
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

	public LibroController getController() {
		return controller;
	}

	public void setController(LibroController controller) {
		this.controller = controller;
	}
	
	public void setListaLibros(List<Libro> librosLista,boolean actualizar) {
		this.tableModel.setLibros(librosLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}

}