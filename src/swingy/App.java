package swingy;

import java.util.ArrayList;

import swingy.controller.CharacterController;
import swingy.controller.GameController;
import swingy.controller.MainMenuController;
import swingy.controller.WorldMapController;
import swingy.controller.loop.LoopMotor;
import swingy.controller.loop.MotorGraphics;
import swingy.entity.Entity;
import swingy.entity.Magician;
import swingy.enums.EModule;
import swingy.exceptions.ModuleException;
import swingy.module.IModule;
import swingy.module.factory.ModuleFactory;
import swingy.ressources.Sprite;
import swingy.utils.Utils;
import swingy.utils.Vector2;
import swingy.views.IView;
import swingy.views.Window;
import swingy.views.factory.ViewFactory;
import swingy.world.WorldMap;
import swingy.world.factory.WorldMapFactory;

public class App {
	
	public static final String			TITLE = "Swingy";
	public static final int				WIDTH = 1800;
	public static final int				HEIGHT = 1000;
	
	public static final int				SCALE = 30;
	
	public static boolean				toMainMenu = false;
	
	public static WorldMap				worldMap = null;
	
	public static IModule				modelInterface = null;
	
	public static LoopMotor				loopController = null;
	
	/*********************************************************/
	/**                         GUI                         **/
	public static Window				window = null;
	/*********************************************************/
	/**                        VIEWS                        **/
	public static IView					mainmenuview = null;
	public static IView					gameview = null;
	/*********************************************************/
	/**                     CONTROLLERS                     **/
	public static MainMenuController	mainMenuController = null;
	public static WorldMapController	worldMapController = null;
	public static CharacterController	characterController = null;
	public static GameController		gameController = null;
	/*********************************************************/
	
	public static Entity				Character = null;
	
	public static ArrayList<Entity>		Characters = new ArrayList<Entity>();
	
	public static String				arg;

	/**
	 * Main of swingy
	 * @param args
	 * @throws ModuleException
	 */
	public static void main(String[] args) throws ModuleException {
		if (args.length != 1)
			throw new ModuleException();
		arg = args[0];
		if (App.start(arg) == false) {
			throw new ModuleException(args[0]);
		}
	}
	
	/**
	 * Start function load and loop
	 * @param arg
	 * @return
	 */
	public static boolean start(String arg) {
		modelInterface = ModuleFactory.loadnewModule(arg);
		
		if (modelInterface == null)
			return false;
		lastFPS = System.currentTimeMillis();
		if (App.modelInterface.getinstance() == EModule.GUI)
			Sprite.LOAD();
		App.Characters = Utils.loadHeros();
		loadMainInterfaces();
		loopMainMenu();
		return true;
	}
	
	/**
	 * Interface loader
	 */
	public static void loadMainInterfaces() {
		
		window		= ViewFactory.loadWindow(modelInterface.getinstance(), TITLE, 1000, 350);
		mainmenuview	= ViewFactory.newMainMenu(modelInterface.getinstance());
	}
	
	/**
	 * Loop of main menu MVC
	 */
	public static void loopMainMenu() {
		mainMenuController = new MainMenuController();
		
		mainmenuview.init();
		
		loopController = new LoopMotor(new MotorGraphics() {

			@Override
			public void graphicControllerLoop() {
				// TODO Auto-generated method stub
				mainMenuController.control();
			}
			@Override
			public void graphicRenderingLoop() {
				// TODO Auto-generated method stub
				mainmenuview.update();
				//updateFPS();
			}
			
		});
		
		loopController.start();
		mainmenuview = null;
		if (Character != null)
			loadGame();
	}
	
	//#######################################################################################
	// LOAD GAME
	
	public static void loadGame() {
		
		loadGameInterface();
		loadWorldMap();
		loadControllers();
		loopApp();
	}
	
	/**
	 * loading graphics interfaces
	 */
	public static void loadGameInterface() {
		if (App.modelInterface.getinstance() == EModule.GUI) {
			window.setSize(WIDTH, HEIGHT);
			window.repaint();
		}
		gameview	= ViewFactory.newGameView(modelInterface.getinstance());
	}
	
	public static void loadCharacter() {
		Character = new Magician("tmp", new Vector2(0, 0));
	}
	
	/**
	 * load world map
	 */
	public static void loadWorldMap() {
		worldMap	= WorldMapFactory.generateWorldMap(Character.getLevel());
		worldMap.addCharacter(Character);
		worldMap.spawnMonsters();
		gameview.addModel(worldMap);
	}
	
	/**
	 * loading controllers
	 */
	public static void loadControllers() {
		worldMapController = new WorldMapController(App.worldMap);
		characterController = new CharacterController(Character);
	}
	
	/**
	 * game loop
	 */
	public static void loopApp() {
		//init gameview
		gameview.init();
		
		gameview.println("Spawn to map");
		
		gameController = new GameController();
		loopController = new LoopMotor(new MotorGraphics() {

			@Override
			public void graphicControllerLoop() {
				// TODO Auto-generated method stub
				gameController.control();
				worldMapController.control();
				characterController.control();
			}
			@Override
			public void graphicRenderingLoop() {
				// TODO Auto-generated method stub
				gameview.update();
				//updateFPS();
			}
			
		});
		loopController.start();
		if (toMainMenu) {
			toMainMenu = false;
			start(arg);
		} else {
			loadGame();
		}
	}
	
	//#####################################################################################
	
	//############################################################
	// FPS
	//############################################################
	public static long					lastFPS;
	public static long					fps;
	
	public static void updateFPS()
	{
		if (System.currentTimeMillis() - lastFPS > 1000)
		{
			System.out.println("FPS:"+ fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	//############################################################
}
