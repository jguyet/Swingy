package swingy.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;

import swingy.model.ISwingyModel;
import swingy.views.SwingyGUIMainMenuView;

public class HerosTableComponent extends JPanel implements ISwingyModel {

	private static final long serialVersionUID = 1L;
	//##########################################################
	//STATICS
	private static final int 		TAB_WIDTH = 1000;
	private static final int 		TAB_HEIGHT = 173;
	//##########################################################
	// JTABLE
	private Object[][]				contents =  new Object[0][0];
	private String[]				entetes = {"Name", "Class", "Level", "Experience", "HitPoint", "Attack", "Defense", "Weapon", "Armor", "Helm"};
	private JTable					tab;
	//##########################################################
	// VIEW
	private SwingyGUIMainMenuView	view;
	//##########################################################
	
	private JPopupMenu				popupMenu = new JPopupMenu();
	private JMenuItem				deleteItem = new JMenuItem("Delete");
	private ActionListener			deleteItemActionListener = null;
	
	
	public HerosTableComponent(SwingyGUIMainMenuView view) {
		super();
		this.view = view;
		this.setLayout(new BorderLayout());
	}

	/**
	 * Paint Hero information table on south of view top.
	 */
	@SuppressWarnings("serial")
	public void paintModel() {
		
		//add characters contents and title table and scrollbar
		this.tab = new JTable(contents, entetes) {
			//SET NOT EDITABLE COLUMNS AND ROWS
			public boolean isCellEditable(int row, int col){return false;}
		};
		this.add(this.tab.getTableHeader(), BorderLayout.NORTH);
		this.tab.setPreferredScrollableViewportSize(new Dimension(TAB_WIDTH,TAB_HEIGHT));
		
		JScrollPane pane = new JScrollPane(this.tab);
		this.add(pane, BorderLayout.SOUTH);
		//add to view
		this.view.addtoTop(this, BorderLayout.SOUTH);
		this.setVisible(false);
		this.setVisible(true);
		this.view.update();
		this.setVisible(false);
		this.setVisible(true);
		
		popupMenu.add(deleteItem);
		deleteItem.addActionListener(deleteItemActionListener);
		
		this.tab.setComponentPopupMenu(popupMenu);
	}
	
	public void remove() {
		this.view.removeToTop(this);
		this.view.update();
	}
	
	public void addnewHero(String name, String classe, int level, long exp, int hitPoint, int attack, int defense, String weapon, String armor, String helm) {
		//{"John", "Warrior", 100, 10000, 5, 0, "Short bow", "unknow"}
		incrementContents();
		contents[contents.length - 1] = new Object[] {name, classe, level, exp, hitPoint, attack, defense, weapon, armor, helm};
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
	
	public void deleteRow(int id) {
		if (this.contents.length > id) {
			Object[][] n = new Object[contents.length][];
			int o = 0;
			for (int i = 0; i < contents.length; i++) {
				
				if (i == id)
					continue ;
				
				n[o] = contents[i];
				o++;
			}
			contents = n;
		}
		
		this.remove();
		this.paintModel();
	}
	
	public void addDeleteActionListener(ActionListener a) {
		this.deleteItemActionListener = a;
	}
	
	/**
	 * Hook Table selection events
	 * @param l
	 */
	public void addListSelectionListener(ListSelectionListener l) {
		this.tab.getSelectionModel().addListSelectionListener(l);
	}
}
