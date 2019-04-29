package smrt2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SmartTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Double[]> data = new ArrayList<Double[]>();
	private List<String> colNames = new ArrayList<String>();
	private String name;
	
	public SmartTableModel(List<String> colNames, String name){
		this.colNames.add("Time");
		this.colNames.addAll(colNames);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Double[] getColumnAt(int i) {
		Double[] columnData = new Double[getRowCount()];
		for (int j = 0; j < getRowCount(); j++) {
			columnData[j] = (Double) getValueAt(j, i);
		}
		return columnData;
	}
	
	public Double[] getRowAt(int i){
		return data.get(i);
	}
	
	public void AddRow(Double[] row){
		data.add(row);
		fireTableRowsInserted(getRowCount(), getRowCount());
	}

	@Override
	public int getColumnCount() {
		return colNames.size();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex)[columnIndex];
	}
	
	@Override
	public String getColumnName(int i){
		return this.colNames.get(i);
	}


}
