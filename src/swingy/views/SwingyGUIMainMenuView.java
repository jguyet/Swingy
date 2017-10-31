package swingy.views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import swingy.model.ISwingyModel;
import swingy.views.components.MainMenuHerosTable;
import swingy.views.components.MainMenuSwingyTitle;
import swingy.views.events.ResponseListener;

public class SwingyGUIMainMenuView extends JPanel implements IView{

	/**
	 * VERSION
	 */
	private static final long serialVersionUID = 1L;
	
	private Window win;
	
	private MainMenuHerosTable heroTable = null;
	
	public SwingyGUIMainMenuView(Window win) {
		this.win = win;
		BorderLayout b = new BorderLayout();
		this.setLayout(b);
		
		//###############################################################################
		//TOP
		//###############################################################################
		JPanel containertop = new JPanel();
		containertop.setLayout(new BorderLayout(0, 15));
		// TITLE
		JPanel swingyTitle = new MainMenuSwingyTitle();
		containertop.add(swingyTitle, BorderLayout.NORTH);
		//HERO TABLE
		this.heroTable = new MainMenuHerosTable();
		this.heroTable.addnewHero("test", "Warrior", 10, 0, 10, 12, "weapon", "unknow");
		this.heroTable.addnewHero("test2", "Warrior", 10, 0, 10, 12, "weapon", "unknow");
		this.heroTable.prepare();
		containertop.add(this.heroTable, BorderLayout.SOUTH);
		//###############################################################################
		//ADD TOP
		this.add(containertop, BorderLayout.NORTH);
		//###############################################################################
		
		
		//###############################################################################
		//BOTTOM
		//###############################################################################
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		
		/*JPanel block_selection = new JPanel();
		block_selection.setLayout(new BorderLayout());
		block_selection.add(new JLabel("*select your favorite hero and click START GAME."), BorderLayout.NORTH);
		JButton jj = new JButton("START GAME");
		jj.setForeground(Color.gray);
		block_selection.add(jj, BorderLayout.SOUTH);*/
		
		MainMenuSelectionHero block_selection = new MainMenuSelectionHero();
		block_selection.prepare();
		
		JPanel block_creation = new JPanel();
		block_creation.setLayout(new BorderLayout());
		block_creation.add(new JLabel("*click add new hero for create new hero."), BorderLayout.NORTH);
		block_creation.add(new JButton("ADD NEW HERO"), BorderLayout.SOUTH);
		
		bottom.add(block_selection, BorderLayout.NORTH);
		bottom.add(block_creation, BorderLayout.SOUTH);
		
		this.add(bottom, BorderLayout.SOUTH);
		
		final MainMenuSelectionHero tmp = block_selection;
		final MainMenuHerosTable tablee = this.heroTable;
		this.heroTable.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	tmp.setButtonEnabled(true);
		    	int sel = tablee.getTable().getSelectedRow();
		        
		        System.out.println(sel);
		        Object[] o = tablee.getRow(sel);
		        
		        System.out.println(o[0]);
		    }
		});
	}
	
	@Override
	public void init() {
		this.win.add(this);
		this.setVisible(false);
		this.setVisible(true);
	}
	
	@Override
	public void destroy() {
		this.win.remove(this);
		this.win.setVisible(false);
		this.win.setVisible(true);
	}
	
	public Window getWindow() {
		return (this.win);
	}

	@Override
	public void update() {
		this.repaint();
	}
	
	public void addHerosTable(MainMenuHerosTable heroTable) {
		this.heroTable = heroTable;
	}

	@Override
	public void addModel(ISwingyModel model) {
	}

	@Override
	public void removeModel(ISwingyModel model) {
	}
	
	/**
	 * child method painting
	 */
	protected void paintComponent(java.awt.Graphics g)
	{
		super.paintComponent(g);
		
		this.setOpaque(false);
		this.setBackground(new Color(0,0,0,0));
		
		//g.clearRect(0, 0, this.win.getWidth(), this.win.getHeight());
		
		/*ArrayList<ISwingyModel> tmp = new ArrayList<ISwingyModel>(models);
		
		for (ISwingyModel model : tmp) {
			model.paint(g);
		}*/
		
	}

	@Override
	public void waitResponse(ResponseListener rep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void println(String txt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print(String txt) {
		// TODO Auto-generated method stub
		
	}
	
	private class MainMenuSelectionHero extends JPanel {
		
		private JLabel	comment = new JLabel("*select your favorite hero and click START GAME.");
		private JButton	selectButton = new JButton("START GAME");
		
		public MainMenuSelectionHero() {
			super();
			this.setLayout(new BorderLayout());
			selectButton.setForeground(Color.gray);
			selectButton.setSelected(false);
			selectButton.setEnabled(false);
		}
		
		public void prepare() {
			this.add(this.comment, BorderLayout.NORTH);
			this.add(this.selectButton, BorderLayout.SOUTH);
		}
		
		public void setButtonEnabled(boolean enable) {
			if (enable == true)
				selectButton.setForeground(Color.black);
			else
				selectButton.setForeground(Color.gray);
			selectButton.setEnabled(enable);
			selectButton.repaint();
		}
		
	}

}
