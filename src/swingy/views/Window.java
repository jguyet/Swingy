package swingy.views;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Window extends JFrame{
	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;
	
	private int sizeX;
	private int sizeY;

	public Window(String title, int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		initialize(title);
	}
	
	private void initialize(String title) {
		setOsLook();
        this.setTitle(title);
        this.setSize(this.sizeX, this.sizeY);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
	}
	
	public void setOsLook()
	{
		try { 
     	   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
     	   SwingUtilities.updateComponentTreeUI(this); 
     	   //force chaque composant de la fenêtre à appeler sa méthode updateUI 
     	} catch (InstantiationException e) { 
     	} catch (ClassNotFoundException e) { 
     	} catch (UnsupportedLookAndFeelException e) { 
     	} catch (IllegalAccessException e) {}
	}
	
	public void addComponent(Component comp) {
		this.add(comp);
	}
}
