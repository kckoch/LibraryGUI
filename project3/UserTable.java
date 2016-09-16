package cu.cs.cpsc2150.project3;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import cu.cs.cpsc2150.project2.Book;
import cu.cs.cpsc2150.project2.Catalog;

public class UserTable extends AbstractTableModel {
	private static final String[] columnHeaders = { "Title", "Author", "Genre" };

    private ArrayList<Book> myData;

    public UserTable(ArrayList<Book> data) {
        myData = data;
    }

    @Override
    public int getRowCount() {
        return myData.size();
    }

    @Override
    public int getColumnCount() {
        return columnHeaders.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnHeaders[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book thing = myData.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return thing.getMyTitle();
            case 1:
                return thing.getMyAuthor();
            case 2:
                return thing.getMyGenre();
            default:
                return "???";
        }
    }
    
    public Book getBookAt(int ndx) {
    	return myData.get(ndx);
    }
}
