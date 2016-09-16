package cu.cs.cpsc2150.project3;

import java.awt.Dimension;

import javax.swing.JFrame;

public class CatalogWindow extends JFrame {
	public CatalogWindow() {
    	super("Catalog");
    	this.setPreferredSize(new Dimension(400, 400));
    	this.setMinimumSize(new Dimension(750, 500));
    	this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	this.setLocationRelativeTo(null);
    }
}
