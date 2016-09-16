/* Author: Blair Durkee 
 * Editor: Kyra Koch*/

package cu.cs.cpsc2150.project3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;

import cu.cs.cpsc2150.project2.*;

public class GUI {
	private static AccountDatabase myAccountDatabase;
    private static Catalog myCatalog;
    private static Account myLoggedInAccount;
    private static Cart cart;
    private static JFrame login;
    private static JFrame appFrame;
    private static boolean loggedout;
    private static JTable acctbl;
    private static JTable catatbl;
    private static JTable usertbl;
    private static JTable actiontbl;
    private static AccountTable acctable;
    private static CatalogTable catatable;
    private static UserTable usertable;
    private static ActionTable actiontable;
    private static GridBagConstraints left;
    private static GridBagConstraints right;
    private static FuzzySearch fuzzy;
    private static LiteralSearch literal;
    
    public static void runGUI() throws FileNotFoundException, ClassNotFoundException, IOException {
    	init();
    	while(true) {
			login();
			login.setVisible(true);
			while(myLoggedInAccount == null) {
				System.out.print("");
			}
			loggedout = false;
			login.setVisible(false);
			login.dispose();
			
	    	appFrame();
	    	appFrame.setVisible(true);
	    	while(!loggedout) {
	    		System.out.print("");
	    	}
	    	appFrame.setVisible(false);
	    	appFrame.dispose();
	    	try {
				save();
			} catch (Exception e1) {
				//
			}
    	}
    }
    
    private static void init() throws FileNotFoundException, ClassNotFoundException, IOException {
    	myAccountDatabase = new AccountDatabase();
        myCatalog = new Catalog();
        cart = new Cart();
        fuzzy = fuzzy.getInstance();
        literal = literal.getInstance();
        myLoggedInAccount = null;
        loggedout = true;
        left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        right = new GridBagConstraints();
        right.anchor = GridBagConstraints.LINE_END;
		right.weightx = 2.0;
		right.fill = GridBagConstraints.HORIZONTAL;
        right.gridwidth = GridBagConstraints.REMAINDER;
        
    	File datFile = new File("input.dat");
        if(datFile.exists()) {
        	load();
        }
        else {
        	save();
        }
    }
    
    private static void login() {
    	final JTextField nameF;
    	final JPasswordField Password;
    	final JLabel inval;
    	
    	login = new JFrame("Login");
    	login.setSize(300, 112);
		login.setLocationRelativeTo(null);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel topPanel = new JPanel(new GridBagLayout());
        
		topPanel.add(new JLabel("Enter your username: "),left);
		nameF = new JTextField(20);
		topPanel.add(nameF, right);
		topPanel.add(new JLabel("Enter your password: "),left);
		Password = new JPasswordField(20);
		topPanel.add(Password, right);
		inval = new JLabel("Please enter valid username/password!");
        inval.setVisible(false);
        inval.setForeground(Color.red);
        topPanel.add(inval, right);
		
        int condition = JComponent.WHEN_FOCUSED;
        InputMap iMap = Password.getInputMap(condition);
        ActionMap aMap = Password.getActionMap();

        String enter = "enter";
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
        aMap.put(enter, new AbstractAction() {

           @Override
           public void actionPerformed(ActionEvent arg0) {
        	   myLoggedInAccount = myAccountDatabase.login(nameF.getText(), String.valueOf(Password.getPassword()));
				if(myLoggedInAccount == null) {
					inval.setVisible(true);
				}
           }
        });
        
		final JPanel windowPane = new JPanel(new BorderLayout());
		
		// create bottom panel
		final JPanel bottomPanel = new JPanel(new BorderLayout());
		JButton button1 = new JButton("Login");
		button1.setSize(30, 1);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					myLoggedInAccount = myAccountDatabase.login(nameF.getText(), String.valueOf(Password.getPassword()));
					if(myLoggedInAccount == null) {
						inval.setVisible(true);
					}
				} catch (Exception e1) {
					//
				}
			}
		});
		JButton button2 = new JButton("Quit");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent d) {
				System.exit(0);
			}
		});
		bottomPanel.add(BorderLayout.CENTER, button1);
		bottomPanel.add(BorderLayout.EAST, button2);
		
		
		windowPane.add(BorderLayout.NORTH, topPanel);
		windowPane.add(BorderLayout.SOUTH, bottomPanel);
		
		login.setContentPane(windowPane);
    }
    
    private static void appFrame() {
    	appFrame = new JFrame("Clemson Library");
    	appFrame.setMinimumSize(new Dimension(750, 500));
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	appFrame.setLocationRelativeTo(null);
    	
		JPanel windowPanel = appPanel();
		final JPanel windowPane = new JPanel(new BorderLayout());
		
		windowPane.add(BorderLayout.NORTH, windowPanel);
		
		appFrame.setContentPane(windowPane);
	}
    
    private static JPanel appPanel() {
    	JPanel upperPanel = new JPanel(new BorderLayout());
		JTabbedPane tabs = tabs();
		JPanel pane = new JPanel(new GridBagLayout());
		
		
    	String type = "Member";
		if(myLoggedInAccount.getStaff())
			type = "Staff";
		JComponent user = new JTextArea("User: " + myLoggedInAccount.getMyName() + "\tAccount Type: " + type);
		
		JPanel tools = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		
		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loggedout = true;
				myLoggedInAccount = null;
			}
		});
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
		tools.add(logout);
		tools.add(exit);
		pane.add(user, left);
		pane.add(tools, right);
		
		upperPanel.add(BorderLayout.NORTH, pane);
		upperPanel.add(BorderLayout.SOUTH, tabs);
		
		return upperPanel;
    }
    
    private static JTabbedPane tabs() {
    	JTabbedPane tabs = new JTabbedPane();

    	JPanel catapanel = CatalogTab();
		tabs.addTab("Catalog", null, catapanel, "The coolest books ever");
		
		if(myLoggedInAccount.getStaff()) {
			JPanel accpanel = AccountTab();
			tabs.addTab("Accounts", null, accpanel, "All of the RADICAL people who like books");
		}
		
    	JPanel check = Check();
		tabs.addTab("Checkout/Return", null, check, "The place where happiness goes to die");
		
		return tabs;
    }
    
    //ALL OF THE CATALOG TAB STUFF
    private static JPanel CatalogTab() {
    	JPanel catapanel = new JPanel(new BorderLayout());
    	catapanel.setPreferredSize(new Dimension(300, 400));

		catatable = new CatalogTable(myCatalog);
		catatbl = new JTable(catatable);
		catatbl.setPreferredSize(new Dimension(300, 400));
		catatbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(catatbl);
        catapanel.add(BorderLayout.CENTER, scroll);
        
        
        JPanel left = leftPanelCatalog();
		left.setBorder(new EmptyBorder(50, 0, 50, 0));
        catapanel.add(BorderLayout.WEST, left);
        
        return catapanel;
    }
    
    private static JPanel leftPanelCatalog() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 0, 20));
        panel.setSize(75, 400);
        panel.setBorder(new EmptyBorder(100, 10, 5, 10));

        if(myLoggedInAccount.getStaff()) {
	        JButton addButton = new JButton("Add Book");
	        addButton.setSize(2, 1);
	        addButton.setBorder(new EmptyBorder(5, 10, 5, 10));
	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                CatalogDialog dialog = new CatalogDialog(myCatalog.getSize());
	                dialog.initialize();
	                dialog.setVisible(true);
	                if (dialog.wasSuccessful()) {
	                    myCatalog.addBook(dialog.getResult());
	                    catatable.fireTableDataChanged();
	                    try {
							save();
						} catch (Exception e1) {
							//
						}
	                }
	            }
	        });
	
	        JButton removeButton = new JButton("Remove Book");
	        removeButton.setSize(2, 1);
	        removeButton.setBorder(new EmptyBorder(5, 10, 5, 10));
	        removeButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int index = catatbl.getSelectedRow();
	                if (index >= 0) {
	                	myCatalog.removeBook(catatbl.getSelectedRow());
	                    catatable.fireTableDataChanged();
	                }
	                try {
						save();
					} catch (Exception e1) {
						//
					}
	            }
	        });
	
	        panel.add(addButton);
	        panel.add(removeButton);
        }
        JPanel search = searchPanel();
        panel.add(search);
        
        return panel;
    }
    
    private static JPanel searchPanel() {
    	JPanel search = new JPanel(new GridBagLayout());
    	search.setSize(75, 400);
    	
    	JLabel entersearch = new JLabel("Enter search: ");
    	final JTextField text = new JTextField();
    	final JRadioButton fuzz = new JRadioButton("Fuzzy");
    	JRadioButton litral = new JRadioButton("Literal");
    	JPanel type = new JPanel(new GridBagLayout());
    	type.add(fuzz);
    	type.add(litral);
    	
    	JPanel buttons = new JPanel();
    	JButton srch = new JButton("Search");
    	srch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ArrayList<Book> ret;
                if(fuzz.isSelected()) {
                	ret = fuzzy.search(text.getText(), myCatalog.getMyBooks());
                }
                else {
                	ret = literal.search(text.getText(), myCatalog.getMyBooks());
                }
            }
        });
    	JButton reset = new JButton("Reset");
    	reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
    	buttons.add(srch);
    	buttons.add(reset);
    	
    	ButtonGroup button = new ButtonGroup();
    	button.add(fuzz);
    	button.add(litral);
    	
    	search.add(entersearch, left);
    	search.add(text, right);
    	search.add(buttons, left);
    	search.add(type, right);
    	
    	return search;
    }
    //END CATALOG PANEL STUFF

    //ALL OF THE ACCOUNT TAB STUFF
    private static JPanel AccountTab() {
    	JPanel accpanel = new JPanel(new BorderLayout());
		accpanel.setSize(300, 400);
		
		JPanel left = leftPanelAccounts();
		left.setBorder(new EmptyBorder(50, 0, 50, 0));
		
		acctable = new AccountTable(myAccountDatabase);
        acctbl = new JTable(acctable);
        acctbl.setSize(300, 400);
        acctbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(acctbl);
        accpanel.add(BorderLayout.CENTER, scroll);
        accpanel.add(BorderLayout.WEST, left);
        
        return accpanel;
    }
    
    private static JPanel leftPanelAccounts() {
        JPanel panel = new JPanel(new GridLayout(8, 1, 0, 20));
        panel.setSize(75, 400);
        panel.setBorder(new EmptyBorder(100, 10, 5, 10));

        JButton addButton = new JButton("Add Account");
        addButton.setSize(2, 1);
        addButton.setBorder(new EmptyBorder(5, 10, 5, 10));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountDialog dialog = new AccountDialog(myAccountDatabase.getSize());
                dialog.initialize();
                dialog.setVisible(true);
                if (dialog.wasSuccessful()) {
                    myAccountDatabase.addAccount(dialog.getResult());
                    acctable.fireTableDataChanged();
                    try {
						save();
					} catch (Exception e1) {
						//
					}
                }
            }
        });

        JButton removeButton = new JButton("Remove Account");
        removeButton.setSize(2, 1);
        removeButton.setBorder(new EmptyBorder(5, 10, 5, 10));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = acctbl.getSelectedRow();
                if (index >= 0) {
                	myAccountDatabase.removeAccount(acctbl.getSelectedRow());
                    acctable.fireTableDataChanged();
                }
                try {
					save();
				} catch (Exception e1) {
					//
				}
            }
        });

        panel.add(BorderLayout.NORTH, addButton);
        panel.add(BorderLayout.SOUTH, removeButton);
        return panel;
    }
    //END ACCOUNT PANEL STUFF
    
    //CHECKOUT STUFF
    private static JPanel Check() {
    	JPanel checkpanel = new JPanel(new BorderLayout());
		checkpanel.setSize(750, 400);
		
		if(!myLoggedInAccount.getStaff()) {
			JPanel userbooks = userBooks();
			JPanel actions = actions();
			JPanel buttons = new JPanel(new GridLayout(8, 1, 0, 20));
			
			JButton checkout = new JButton("Checkout");
			checkout.setBorder(new EmptyBorder(10, 0, 10, 0));
			checkout.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JFrame frame = CatalogWindow();
	                frame.setVisible(true);
	            }
	        });
			JButton complete = new JButton("Complete");
			complete.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                cart.completeCheckout();
	                actiontable.fireTableDataChanged();
	                catatable.fireTableDataChanged();
	                catatable = new CatalogTable(myCatalog);
	                catatbl.setModel(catatable);
	                catatable.fireTableDataChanged();
		           	usertable = new UserTable(myCatalog.userBooks(myLoggedInAccount));
		           	usertbl.setModel(usertable);
		           	usertable.fireTableDataChanged();
	                try {
						save();
					} catch (Exception e1) {
						//
					}
	            }
	        });
			complete.setBorder(new EmptyBorder(10, 0, 10, 0));
			buttons.add(checkout);
			buttons.add(complete);
			
			checkpanel.add(BorderLayout.WEST, userbooks);
			checkpanel.add(BorderLayout.CENTER, buttons);
			checkpanel.add(BorderLayout.EAST, actions);
		}
		else {
			JPanel err = new JPanel(new GridLayout(3, 1, 0, 0));
			JLabel error1 = new JLabel("ERROR YOU CAN'T CHECKOUT BOOKS");
			JLabel error2 = new JLabel("BECAUSE YOU'RE STAFF AND");
			JLabel error3 = new JLabel("CAN'T HAVE FUN");
			error1.setFont(new Font("Serif", Font.BOLD, 30));
			error2.setFont(new Font("Serif", Font.BOLD, 30));
			error3.setFont(new Font("Serif", Font.BOLD, 30));
			
			err.add(error1);
			err.add(error2);
			err.add(error3);
			checkpanel.add(BorderLayout.CENTER, err);
		}
		
		return checkpanel;
    }
    
    private static JPanel userBooks() {
    	JPanel userpanel = new JPanel(new BorderLayout());
    	
    	JLabel header = new JLabel("Books you have checked out:");
    	usertable = new UserTable(myCatalog.userBooks(myLoggedInAccount));
		usertbl = new JTable(usertable);
		usertbl.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2) {
		            cart.returnBook(usertable.getBookAt(row), myLoggedInAccount);
		            actiontable.fireTableDataChanged();
		        }
		    }
		});
		usertbl.setPreferredSize(new Dimension(300, 400));
		usertbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usertbl.setPreferredScrollableViewportSize(usertbl.getPreferredSize());
		usertbl.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(usertbl);
        
        userpanel.add(BorderLayout.NORTH, header);
        userpanel.add(BorderLayout.WEST, scroll);
        
        return userpanel;
    }
    
    private static JPanel actions() {
    	JPanel userpanel = new JPanel(new BorderLayout());
    	
    	JLabel header = new JLabel("Actions to complete:");
    	
    	actiontable = new ActionTable(cart);
		actiontbl = new JTable(actiontable);
		actiontbl.setPreferredSize(new Dimension(300, 400));
		actiontbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actiontbl.setPreferredScrollableViewportSize(actiontbl.getPreferredSize());
		actiontbl.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(actiontbl);
        
        userpanel.add(BorderLayout.NORTH, header);
        userpanel.add(BorderLayout.EAST, scroll);
        
        return userpanel;
    }
    
    public static JFrame CatalogWindow() {
    	final JFrame frame = new JFrame("Catalog");
    	frame.setPreferredSize(new Dimension(400, 400));
    	frame.setMinimumSize(new Dimension(400, 400));
    	frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	frame.setLocationRelativeTo(null);
    	
    	JPanel catapanel = new JPanel(new BorderLayout());
    	final JTable tempcata = catatbl;
    	JButton select = new JButton("Select");
    	select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tempcata.getSelectedRow();
            	cart.checkoutBook(myCatalog.getBook(index), myLoggedInAccount);
            	actiontable.fireTableDataChanged();
            	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
    	JScrollPane scroll = new JScrollPane(catatbl);
        catapanel.add(BorderLayout.CENTER, scroll);
    	catapanel.add(BorderLayout.EAST, select);
    	
    	JPanel windowPanel = catapanel;
		final JPanel windowPane = new JPanel(new BorderLayout());
		
		windowPane.add(BorderLayout.NORTH, windowPanel);
		
		frame.setContentPane(windowPane);
		return frame;
    }
    //END CHECKOUT STUFF
    
    private static void save() throws FileNotFoundException, IOException {
    	File datFile = new File("input.dat");
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(datFile));
        oos.writeObject(myAccountDatabase);
        oos.writeObject(myCatalog);
        oos.close();
    }
    
    private static void load() throws FileNotFoundException, IOException, ClassNotFoundException {
    	File datFile = new File("input.dat");
    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(datFile));
        myAccountDatabase = (AccountDatabase) ois.readObject();
        myCatalog = (Catalog) ois.readObject();
        ois.close();
    }
}
