package cu.cs.cpsc2150.project3;

import javax.swing.table.AbstractTableModel;

import cu.cs.cpsc2150.project2.Account;
import cu.cs.cpsc2150.project2.AccountDatabase;
import cu.cs.cpsc2150.project2.Book;
import cu.cs.cpsc2150.project2.Catalog;

public class AccountTable extends AbstractTableModel{
	private static final String[] columnHeaders = { "ID", "Name", "Username", "Password", "Type" };
	
	private AccountDatabase myData;

    public AccountTable(AccountDatabase data) {
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
        Account thing = myData.getAccount(rowIndex);
        switch (columnIndex) {
            case 0:
                return thing.getID();
            case 1:
                return thing.getMyName();
            case 2:
                return thing.getUsername();
            case 3:
                return thing.getPassword();
            case 4:
                return thing.getType();
            default:
                return "???";
        }
    }
}
