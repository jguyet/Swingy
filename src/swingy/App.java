package swingy;

import swingy.controller.CharacterController;
import swingy.controller.MainMenuController;
import swingy.controller.WorldMapController;
import swingy.controller.loop.LoopMotor;
import swingy.controller.loop.MotorGraphics;
import swingy.entity.Entity;
import swingy.entity.Magician;
import swingy.exceptions.ModuleException;
import swingy.math.Vector2;
import swingy.module.IModule;
import swingy.module.factory.ModuleFactory;
import swingy.ressources.Sprite;
import swingy.views.IView;
import swingy.views.Window;
import swingy.views.factory.ViewFactory;
import swingy.world.WorldMap;
import swingy.world.factory.WorldMapFactory;

public class App {
	
	public static final String			TITLE = "Swingy";
	public static final int				WIDTH = 1000;
	public static final int				HEIGHT = 1000;
	
	public static final int				SCALE = 30;
	
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
	/*********************************************************/
	
	public static Entity				Character = null;

	/**
	 * Main of swingy
	 * @param args
	 * @throws ModuleException
	 */
	public static void main(String[] args) throws ModuleException {
		if (args.length != 1)
			throw new ModuleException();
		if (App.start(args[0]) == false) {
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
		Sprite.LOAD();
		loadMainInterfaces();
		loopMainMenu();
		//Start interface and load game
		System.out.println("Instance : " + modelInterface.getinstance());
		return true;
	}
	
	public static void loadMainInterfaces() {
		
		window		= ViewFactory.loadWindow(modelInterface.getinstance(), TITLE, 1000, 350);
		mainmenuview	= ViewFactory.newMainMenu(modelInterface.getinstance());
	}
	
	public static void loopMainMenu() {
		mainmenuview.init();
		
		mainMenuController = new MainMenuController();
		
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
				updateFPS();
			}
			
		});
		
		loopController.start();
	}
	
	//#######################################################################################
	// LOAD GAME
	
	public static void loadGame() {
		loadCharacter();
		loadWorldMap();
		loadControllers();
		loopApp();
	}
	
	/**
	 * loading graphics interfaces
	 */
	public static void loadGameInterface() {
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
		WorldMapFactory.loadRandomMonsters(worldMap);
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
		
		loopController = new LoopMotor(new MotorGraphics() {

			@Override
			public void graphicControllerLoop() {
				// TODO Auto-generated method stub
				worldMapController.control();
				characterController.control();
			}
			@Override
			public void graphicRenderingLoop() {
				// TODO Auto-generated method stub
				gameview.update();
				updateFPS();
			}
			
		});
		
		loopController.start();
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
			//System.out.println("FPS:"+ fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	//############################################################
}
