package frsf.isi.died.app.dao.util;

import java.text.ParseException;
import java.util.List;

public interface CsvRecord {

	public List<String> asCsvRow();
	public void loadFromStringRow(List<String> datos) throws ParseException;
}
