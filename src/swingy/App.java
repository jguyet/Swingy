package swingy;

import swingy.controller.WorldMapController;
import swingy.controller.loop.LoopMotor;
import swingy.controller.loop.MotorGraphics;
import swingy.exceptions.ModuleException;
import swingy.module.IModule;
import swingy.module.factory.ModuleFactory;
import swingy.ressources.Sprite;
import swingy.views.SwingyView;
import swingy.views.Window;
import swingy.world.WorldMap;
import swingy.world.factory.WorldMapFactory;

public class App {
	
	public static WorldMap				worldMap = null;
	
	public static IModule				modelInterface = null;
	
	/*********************************************************/
	/**                         GUI                         **/
	public static Window				window = null;
	/*********************************************************/
	/**                        VIEWS                        **/
	public static SwingyView			mainmenu = null;
	public static SwingyView			gameview = null;
	/*********************************************************/
	/**                     CONTROLLERS                     **/
	public static WorldMapController	worldMapController = null;
	/*********************************************************/
	
	public static LoopMotor		loopController = null;
	
	public static final String	TITLE = "Swingy";
	
	public static long lastFPS;
	public static long fps;

	public static void main(String[] args) throws ModuleException {
		if (args.length != 1)
			throw new ModuleException();
		if (App.start(args[0]) == false) {
			throw new ModuleException(args[0]);
		}
	}
	
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
	
	public static void loadInterface() {
		window		= new Window(TITLE, 1000, 1000);
		gameview	= new SwingyView(window);
	}
	
	public static void loadControllers() {
		worldMapController = new WorldMapController(App.worldMap);
	}
	
	public static void loadWorldMap() {
		worldMap	= WorldMapFactory.generateWorldMap(15);
		
		gameview.addModel(worldMap);
	}
	
	public static void loopApp() {
		//init gameview
		window.add(gameview);
		gameview.init();
		
		loopController = new LoopMotor(new MotorGraphics() {

			@Override
			public void graphicControllerLoop() {
				// TODO Auto-generated method stub
				worldMapController.control();
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
}
