package swingy;

import swingy.controller.CharacterController;
import swingy.controller.WorldMapController;
import swingy.controller.loop.LoopMotor;
import swingy.controller.loop.MotorGraphics;
import swingy.entity.CrazyHero;
import swingy.entity.Entity;
import swingy.enums.EModule;
import swingy.exceptions.ModuleException;
import swingy.math.Vector2;
import swingy.module.IModule;
import swingy.module.factory.ModuleFactory;
import swingy.ressources.Sprite;
import swingy.views.IView;
import swingy.views.SwingyConsoleView;
import swingy.views.SwingyGUIGameView;
import swingy.views.Window;
import swingy.views.factory.ViewFactory;
import swingy.world.WorldMap;
import swingy.world.factory.WorldMapFactory;

public class App {
	
	public static final String			TITLE = "Swingy";
	public static final int				WIDTH = 1000;
	public static final int				HEIGHT = 1000;
	
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
	public static WorldMapController	worldMapController = null;
	public static CharacterController	characterController = null;
	/*********************************************************/

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
		loadInterface();
		loadWorldMap();
		loadControllers();
		loopApp();
		//Start interface and load game
		System.out.println("Instance : " + modelInterface.getinstance());
		return true;
	}
	
	/**
	 * loading graphics interfaces
	 */
	public static void loadInterface() {
		
		window		= ViewFactory.loadWindow(modelInterface.getinstance(), TITLE, 1000, 1000);
		gameview	= ViewFactory.newGameView(modelInterface.getinstance());
		mainmenuview	= ViewFactory.newMainMenu(modelInterface.getinstance());
	}
	
	/**
	 * loading controllers
	 */
	public static void loadControllers() {
		worldMapController = new WorldMapController(App.worldMap);
	}
	
	/**
	 * load world map
	 */
	public static void loadWorldMap() {
		worldMap	= WorldMapFactory.generateWorldMap(15);
		gameview.addModel(worldMap);
	}
	
	/**
	 * game loop
	 */
	public static void loopApp() {
		//init gameview
		gameview.init();
		
		Entity e = new CrazyHero("tmp", new Vector2(0, 0));
		characterController = new CharacterController(e);
		
		gameview.addModel(e);
		
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
