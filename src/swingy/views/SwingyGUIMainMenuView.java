package swingy.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swingy.App;
import swingy.entity.Magician;
import swingy.entity.Princess;
import swingy.entity.Warrior;
import swingy.math.Vector2;
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
	
	private JPanel					top = new JPanel();
	private JPanel					swingyTitle = null;
	private MainMenuHerosTable		heroTable = new MainMenuHerosTable();
	
	
	private JPanel					bottom = new JPanel();
	private MainMenuSelectionHero	block_selection = new MainMenuSelectionHero();
	private MainMenuCreateHero		block_creation = new MainMenuCreateHero();
	
	private CreationForm			creationform = null;
	
	public SwingyGUIMainMenuView(Window win) {
		this.win = win;
		BorderLayout b = new BorderLayout();
		this.setLayout(b);
		
		this.top.setLayout(new BorderLayout(0, 15));
		this.bottom.setLayout(new BorderLayout());
		
		// TITLE
		this.swingyTitle = new MainMenuSwingyTitle();
		this.heroTable = new MainMenuHerosTable();
		
		this.win.add(this);
	}
	
	@Override
	public void init() {
		this.win.setSize(1000, 350);
		//###############################################################################
		//TOP
		//###############################################################################
		this.top.add(this.swingyTitle, BorderLayout.NORTH);
		this.heroTable.prepare();
		this.top.add(this.heroTable, BorderLayout.SOUTH);
		this.add(this.top, BorderLayout.NORTH);
		//###############################################################################
		//BOTTOM
		//###############################################################################
		
		this.block_selection.prepare();
		this.block_creation.prepare();
		
		this.bottom.add(this.block_selection, BorderLayout.NORTH);
		this.bottom.add(this.block_creation, BorderLayout.SOUTH);
		this.add(this.bottom, BorderLayout.SOUTH);
		
		this.setVisible(false);
		this.setVisible(true);
	}
	
	public void removeHeroTable() {
		this.top.remove(this.heroTable);
	}
	
	public void repaintHeroTable() {
		this.remove(this.top);
		this.heroTable.prepare();
		this.top.add(this.heroTable, BorderLayout.SOUTH);
		this.add(this.top, BorderLayout.NORTH);
		this.setVisible(false);
		this.setVisible(true);
	}
	
	public void addcreationForm() {
		this.remove(this.bottom);
		this.bottom.remove(this.block_selection);
		this.creationform = new CreationForm();
		this.bottom.add(this.creationform, BorderLayout.NORTH);
		this.add(this.bottom, BorderLayout.SOUTH);
	}
	
	public void removeCreationForm() {
		this.remove(this.bottom);
		this.bottom.remove(this.creationform);
		this.bottom.add(this.block_selection, BorderLayout.NORTH);
		this.add(this.bottom, BorderLayout.SOUTH);
	}
	
	public void paintBlockCreation() {
		
		if (this.block_creation.isSelected() && this.win.getHeight() != 370) {
			this.win.setSize(1000, 370);
			this.block_creation.comment.setVisible(false);
			this.block_creation.getButton().setText("Annuler");
			addcreationForm();
			this.block_selection.setVisible(false);
		} else if (!this.block_creation.isSelected() && this.win.getHeight() == 370) {
			this.win.setSize(1000, 350);
			this.block_creation.comment.setVisible(true);
			this.block_creation.getButton().setText("Create a new Hero");
			removeCreationForm();
			this.block_selection.setVisible(true);
		}
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
		
		paintBlockCreation();
		
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
	
	public MainMenuSelectionHero getSelectionHeroPanel() {
		return (block_selection);
	}
	
	public MainMenuCreateHero getCreationHeroPanel() {
		return (block_creation);
	}
	
	public class MainMenuSelectionHero extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JLabel	comment = new JLabel("*select your favorite hero and click START GAME.");
		private JButton	selectButton = new JButton("Select Hero");
		
		public MainMenuSelectionHero() {
			super();
			this.setLayout(new BorderLayout());
			selectButton.setForeground(Color.gray);
			selectButton.setEnabled(false);
		}
		
		public JButton getButton() {
			return (this.selectButton);
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
		
		public void addEventButtonMouseListener(MouseListener l) {
			selectButton.addMouseListener(l);
		}
		
	}
	
	public class MainMenuCreateHero extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JLabel	comment = new JLabel("*click add new hero for create a new hero.");
		private JButton	selectButton = new JButton("Create a new Hero");
		private boolean isSelected = false;
		
		public MainMenuCreateHero() {
			super();
			this.setLayout(new BorderLayout());
			selectButton.setSelected(false);
			selectButton.setEnabled(true);
			selectButton.setForeground(Color.black);
		}
		
		public JButton getButton() {
			return (this.selectButton);
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
		
		public void addEventButtonMouseListener(MouseListener l) {
			selectButton.addMouseListener(l);
		}
		
		public boolean isSelected() {
			return (isSelected);
		}
		
		public void setSelected(boolean s) {
			this.isSelected = s;
		}
		
	}
	
	public class CreationForm extends JPanel {
		
		/**
		 * 
		 */
		private static final long 	serialVersionUID = 1L;
		private final String[] 		classes = {"Magician", "Princess", "Warrior"};
		
		private JPanel 				top = new JPanel();
		
		//NAME
		private JLabel				lblName = new JLabel("Hero Name");
		private TextField			inputName = new TextField("\"saolo\"");
		//CLASS
		private JLabel				lblClass = new JLabel("Class");
		private JComboBox<String>	classSelect = new JComboBox<String>(classes);
		
		private JPanel				bottom = new JPanel();
		private JButton				valide = new JButton("Valider");
		
		public CreationForm() {
			super();
			this.setLayout(new BorderLayout());
			GridLayout experimentLayout = new GridLayout(0,2);
			top.setLayout(experimentLayout);
			//line 1
			top.add(lblName);
			top.add(lblClass);	
			//line 2
			top.add(inputName);
			classSelect.setSelectedIndex(0);
			top.add(classSelect);
			
			this.add(top, BorderLayout.NORTH);
			
			bottom.setLayout(new BorderLayout());
			bottom.add(valide, BorderLayout.SOUTH);
			this.add(bottom, BorderLayout.SOUTH);
			
			final CreationForm cf = this;
			
			valide.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					switch (((String)cf.classSelect.getSelectedItem())) {
					case "Magician":
						App.Characters.add(new Magician(cf.inputName.getText(), new Vector2(0,0)));
						break ;
					case "Princess":
						App.Characters.add(new Princess(cf.inputName.getText(), new Vector2(0,0)));
						break ;
					case "Warrior":
						App.Characters.add(new Warrior(cf.inputName.getText(), new Vector2(0,0)));
						break ;
					}
					block_creation.setSelected(false);
					paintBlockCreation();
				}
				
			});
			
		}
	}

}
