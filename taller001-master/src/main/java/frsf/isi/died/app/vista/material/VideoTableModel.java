package frsf.isi.died.app.vista.material;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import frsf.isi.died.tp.modelo.productos.Video;

public class VideoTableModel  extends AbstractTableModel {

	private List<Video> videos;
	private String[] columnas = {"ID","Titulo","Tema","Precio Compra","Costo Por Segundo","Duracion","Precio Suscripcion","Fecha de publicacion", "Calificacion", "Relevancia"};
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}
	
	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Override
	public int getRowCount() {
		return videos.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valor = null;
		switch (columnIndex) {
		case 0:
			valor = this.videos.get(rowIndex).getId();
			break;
		case 1:
			valor = this.videos.get(rowIndex).getTitulo();
			break;
		case 2:
			valor = this.videos.get(rowIndex).getTema();
			break;
		case 3:
			valor = this.videos.get(rowIndex).getCosto();
			break;
		case 4:
			valor = this.videos.get(rowIndex).costoPorSeg;
			break;
		case 5:
			valor = this.videos.get(rowIndex).duracionVideo;
			break;
		case 6:
			valor = this.videos.get(rowIndex).precio();
			break;
		case 7:
			valor = dateFormat.format(this.videos.get(rowIndex).getFecha());
			break;
		case 8:
			valor = this.videos.get(rowIndex).getCalificacion();
			break;
		case 9:
			valor = this.videos.get(rowIndex).getRelevancia();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		return valor;
	}

}
