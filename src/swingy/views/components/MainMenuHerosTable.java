package swingy.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainMenuHerosTable extends JPanel {

	private static final int TAB_WIDTH = 1000;
	private static final int TAB_HEIGHT = 173;
	
	private Object[][]	contents =  new Object[0][0];
	private String[]	entetes = {"Name", "Class", "Level", "Experience", "Attack", "Defense", "Weapon", "Armor"};
	
	private JTable		tab;
	
	public MainMenuHerosTable() {
		super();
		this.setLayout(new BorderLayout());
	}
	
	public void prepare() {
		this.tab = new JTable(contents, entetes);
		this.add(this.tab.getTableHeader(), BorderLayout.NORTH);
		this.tab.setPreferredScrollableViewportSize(new Dimension(TAB_WIDTH,TAB_HEIGHT));
		JScrollPane pane = new JScrollPane(this.tab);
		this.add(pane, BorderLayout.SOUTH);
	}
	
	public void addnewHero(String name, String classe, int level, long exp, int attack, int defense, String weapon, String armor) {
		//{"John", "Warrior", 100, 10000, 5, 0, "Short bow", "unknow"}
		incrementContents();
		contents[contents.length - 1] = new Object[] {name, classe, level, exp, attack, defense, weapon, armor};
	}
	
	private void incrementContents() {
		if (contents.length == 0) {
			contents = new Object[1][];
		} else {
			Object[][] n = new Object[contents.length + 1][];
			for (int i = 0; i < contents.length; i++) {
				n[i] = contents[i];
			}
			contents = n;
		}
	}
	
	public JTable getTable() {
		return (this.tab);
	}
	
	public Object[] getRow(int id) {
		return (this.contents[id]);
	}
	
	public void addListSelectionListener(ListSelectionListener l) {
		this.tab.getSelectionModel().addListSelectionListener(l);
		
		//example
		/*tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	
		        int sel = tb.getSelectedRow();
		        
		        System.out.println(sel);
		        Object[] o = dm[sel];
		        
		        System.out.println(o[0]);
		    }
		});*/
	}
}
