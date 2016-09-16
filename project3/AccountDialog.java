package cu.cs.cpsc2150.project3;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import cu.cs.cpsc2150.project2.Account;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AccountDialog extends JDialog {
    private Account myDataThing = null;
    private boolean mySuccessFlag;
    private int id;

    public AccountDialog(int id) {
        super(new JFrame(), "New Account", true);
        this.setSize(new Dimension(300, 300));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        mySuccessFlag = false;
        this.id = id;
    }

    public void initialize() {
    	JPanel windowPanel = new JPanel(new GridBagLayout());
		GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        GridBagConstraints right = new GridBagConstraints();
		right.weightx = 2.0;
        right.fill = GridBagConstraints.HORIZONTAL;
        right.gridwidth = GridBagConstraints.REMAINDER;
        windowPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel name = new JLabel("Name:");
        name.setBorder(new EmptyBorder(2, 2, 2, 2));
        JLabel username = new JLabel("Username:");
        username.setBorder(new EmptyBorder(2, 2, 2, 2));
        JLabel password = new JLabel("Password:");
        password.setBorder(new EmptyBorder(2, 2, 2, 2));
        JLabel type = new JLabel("Account Type:");
        JLabel email = new JLabel("Email:");
        JLabel phone = new JLabel("Phone Number: ");
        JLabel phoneEx = new JLabel("Ex: XXX-XXX-XXXX");
        phoneEx.setSize(12, 1);
        final JLabel invalEmail = new JLabel("Not a valid email!");
        invalEmail.setVisible(false);
        invalEmail.setForeground(Color.red);
        final JLabel invalPhone = new JLabel("Not a valid phone number!");
        invalPhone.setVisible(false);
        invalPhone.setForeground(Color.red);
        type.setBorder(new EmptyBorder(2, 2, 2, 2));
        final JRadioButton staf = new JRadioButton("Staff");
        JRadioButton mem = new JRadioButton("Member");
        ButtonGroup group = new ButtonGroup();
        group.add(staf);
        group.add(mem);
        final JTextField nameField = new JTextField();
        final JTextField usernameField = new JTextField();
        final JPasswordField passwordField = new JPasswordField();
        final JTextField emailField = new JTextField();
        emailField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if(!(emailField.getText()).matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-z]{2,}"))
                	invalEmail.setVisible(true);
            }
            public void focusGained(FocusEvent e) {
            	invalEmail.setVisible(false);
            }
        });
        final JTextField phoneField = new JTextField();
        phoneField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if(!(phoneField.getText()).matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$"))
                	invalPhone.setVisible(true);
            }
            public void focusGained(FocusEvent e) {
            	invalPhone.setVisible(false);
            }
        });
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!invalEmail.isVisible() && !invalPhone.isVisible()) {
	                try {
	                	boolean isstaff = false;
	                	if(staf.isSelected())
	                		isstaff = true;
	                    myDataThing = new Account(id, nameField.getText(), usernameField.getText(),String.valueOf(passwordField.getPassword()), 
	                    							emailField.getText(), phoneField.getText(), isstaff);
	                    mySuccessFlag = true;
	                    AccountDialog.this.setVisible(false);
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(AccountDialog.this, "Invalid account.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
            	}
            	else {
            		JOptionPane.showMessageDialog(AccountDialog.this, "Invalid account.", "Error", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountDialog.this.setVisible(false);
            }
        });
        
        JPanel butt = new JPanel(new FlowLayout());
        butt.add(staf);
        butt.add(mem);
        
        windowPanel.add(name, left);
        windowPanel.add(nameField, right);
        windowPanel.add(username, left);
        windowPanel.add(usernameField, right);
        windowPanel.add(password, left);
        windowPanel.add(passwordField, right);
        windowPanel.add(type, left);
        windowPanel.add(butt, right);
        windowPanel.add(email, left);
        windowPanel.add(emailField, right);
        windowPanel.add(invalEmail, right);
        windowPanel.add(phone, left);
        windowPanel.add(phoneField, right);
        windowPanel.add(phoneEx, right);
        windowPanel.add(invalPhone, right);
        windowPanel.add(okButton, left);
        windowPanel.add(cancelButton, right);

        this.getContentPane().add(windowPanel);
    }

    public boolean wasSuccessful() {
        return mySuccessFlag;
    }

    public Account getResult() {
        return myDataThing;
    }
}
