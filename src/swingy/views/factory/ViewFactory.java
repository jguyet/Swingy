package swingy.views.factory;

import java.util.ArrayList;

import swingy.App;
import swingy.entity.Entity;
import swingy.entity.artefacs.Artefact;
import swingy.enums.EModule;
import swingy.views.IView;
import swingy.views.SwingyConsoleView;
import swingy.views.SwingyGUIGameView;
import swingy.views.SwingyGUIMainMenuView;
import swingy.views.Window;
import swingy.views.components.EndFightMenuComponent;
import swingy.views.components.FightStartMenuComponent;
import swingy.views.components.GameBannerInterfaceComponent;

public class ViewFactory {
	
	public static Window loadWindow(EModule type, String title, int width, int height) {
		if (type == EModule.GUI)
			return (new Window(title, width, height));
		return (null);
	}

	public static IView newMainMenu(EModule type) {
		if (type == EModule.CONSOLE) {
			return (new SwingyConsoleView());
		} else if (type == EModule.GUI) {
			return (new SwingyGUIMainMenuView(App.window));
		}
		return (null);
	}
	
	public static IView newGameView(EModule type) {
		if (type == EModule.CONSOLE) {
			return (new SwingyConsoleView());
		} else if (type == EModule.GUI) {
			return (new SwingyGUIGameView(App.window));
		}
		return (null);
	}
	
	public static GameBannerInterfaceComponent newGameGuiInterface(EModule type, IView v) {
		if (type == EModule.GUI) {
			return (new GameBannerInterfaceComponent((SwingyGUIGameView)v));
		}
		return null;
	}
	
	public static EndFightMenuComponent newEndFightMenuComponent(EModule type, IView v, Entity p1, Entity p2, boolean hasWin, ArrayList<Artefact> drops) {
		if (type == EModule.GUI) {
			return (new EndFightMenuComponent((SwingyGUIGameView)v, p1, p2, hasWin, drops));
		}
		return null;
	}
	
	public static FightStartMenuComponent newFightStartMenuComponent(EModule type, IView v, Entity p1, Entity p2) {
		if (type == EModule.GUI) {
			return (new FightStartMenuComponent((SwingyGUIGameView)v, p1, p2));
		}
		return null;
	}
}
