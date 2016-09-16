package cu.cs.cpsc2150.project3;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import cu.cs.cpsc2150.project2.Book;
import cu.cs.cpsc2150.project2.Cart;
import cu.cs.cpsc2150.project2.CartItem;

public class ActionTable extends AbstractTableModel {
	private static final String[] columnHeaders = { "Action", "Book" };

    private Cart myData;

    public ActionTable(Cart data) {
        myData = data;
    }

    @Override
    public int getRowCount() {
        return myData.getSize();
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
        CartItem thing = myData.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return thing.getCommand();
            case 1:
                return thing.getBook().getMyTitle();
            default:
                return "???";
        }
    }
    
    public CartItem getItemAt(int ndx) {
    	return myData.get(ndx);
    }
}
