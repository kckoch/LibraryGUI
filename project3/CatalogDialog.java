package cu.cs.cpsc2150.project3;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import cu.cs.cpsc2150.project2.Book;
import cu.cs.cpsc2150.project2.Catalog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CatalogDialog extends JDialog { 
    private Book myDataThing = null;
    private boolean mySuccessFlag;
    private int id;
    private JRadioButton rom, com, scifi, horror, action, psych, thrill, fantasy;
    private ButtonGroup group;
    private GridBagConstraints left, right;

    public CatalogDialog(int id) {
    	super(new JFrame(), "New Book", true);
        this.setSize(new Dimension(400, 250));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        mySuccessFlag = false;
        this.id = id;
    }

    public void initialize() {
        JPanel windowPanel = new JPanel(new GridBagLayout());
        left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        right = new GridBagConstraints();
		right.weightx = 2.0;
        right.fill = GridBagConstraints.HORIZONTAL;
        right.gridwidth = GridBagConstraints.REMAINDER;
        windowPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Title:");
        title.setBorder(new EmptyBorder(2, 2, 2, 2));
        JLabel author = new JLabel("Author:");
        author.setBorder(new EmptyBorder(2, 2, 2, 2));
        JLabel genre = new JLabel("Genre:");
        genre.setBorder(new EmptyBorder(2, 2, 2, 2));
        JPanel buttons = genreButtons();
        JLabel tags = new JLabel("Tags:");
        tags.setBorder(new EmptyBorder(2, 2, 2, 2));
        
        final JTextField titleField = new JTextField();
        final JTextField authorField = new JTextField();
        final JTextField tagsField = new JTextField();

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	StringTokenizer token = new StringTokenizer(tagsField.getText(), ",");
                	ArrayList<String> arr = new ArrayList<String>();
                	while(token.hasMoreTokens()) {
                		arr.add(token.nextToken());
                	}
                	String gen = findGenre();
                    myDataThing = new Book(id, titleField.getText(), authorField.getText(), gen, arr);
                    mySuccessFlag = true;
                    CatalogDialog.this.setVisible(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CatalogDialog.this, "Invalid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CatalogDialog.this.setVisible(false);
            }
        });

        windowPanel.add(title, left);
        windowPanel.add(titleField, right);
        windowPanel.add(author, left);
        windowPanel.add(authorField, right);
        windowPanel.add(genre, left);
        windowPanel.add(buttons, right);
        windowPanel.add(tags, left);
        windowPanel.add(tagsField, right);
        windowPanel.add(okButton);
        windowPanel.add(cancelButton);

        this.getContentPane().add(windowPanel);
    }

    public boolean wasSuccessful() {
        return mySuccessFlag;
    }

    public Book getResult() {
        return myDataThing;
    }
    
    private JPanel genreButtons() {
    	JPanel panel = new JPanel(new GridBagLayout());
    	rom = new JRadioButton("Romance");
    	com = new JRadioButton("Comedy");
    	scifi = new JRadioButton("Science Fiction");
    	horror = new JRadioButton("Horror");
    	action = new JRadioButton("Action");
    	psych = new JRadioButton("Psychological");
    	thrill = new JRadioButton("Thriller");
    	fantasy = new JRadioButton("Fantasy");
    	
    	group = new ButtonGroup();
    	group.add(rom);
    	group.add(com);
    	group.add(scifi);
    	group.add(horror);
    	group.add(action);
    	group.add(psych);
    	group.add(thrill);
    	group.add(fantasy);
    	
    	panel.add(rom, left);
    	panel.add(com, right);
    	panel.add(scifi, left);
    	panel.add(horror, right);
    	panel.add(action, left);
    	panel.add(psych, right);
    	panel.add(thrill, left);
    	panel.add(fantasy, right);
    	
    	return panel;
    }
    
    private String findGenre() {
    	if(rom.isSelected())
    		return "Romance";
    	else if(com.isSelected())
    		return "Comedy";
    	else if (scifi.isSelected())
    		return "Science Fiction";
    	else if (horror.isSelected())
    		return "Horror";
    	else if(action.isSelected())
    		return "Action";
    	else if (psych.isSelected())
    		return "Psychological";
    	else if (thrill.isSelected())
    		return "Thrill";
    	else
    		return "Fantasy";
    }
}
