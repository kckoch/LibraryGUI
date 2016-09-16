package cu.cs.cpsc2150.project3;
import javax.swing.table.AbstractTableModel;

import cu.cs.cpsc2150.project2.Book;
import cu.cs.cpsc2150.project2.Catalog;

// the table model supplies the data that will appear in a JTable
public class CatalogTable extends AbstractTableModel {
    private static final String[] columnHeaders = { "ID", "Title", "Author", "Genre", "Checked Out" };

    private Catalog myData;

    public CatalogTable(Catalog data) {
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
        Book thing = myData.getBook(rowIndex);
        switch (columnIndex) {
            case 0:
                return thing.getID();
            case 1:
                return thing.getMyTitle();
            case 2:
                return thing.getMyAuthor();
            case 3:
                return thing.getMyGenre();
            case 4:
                return thing.checkedout();
            default:
                return "???";
        }
    }
}
