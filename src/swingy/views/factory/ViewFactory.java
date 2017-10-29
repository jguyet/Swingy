package swingy.views.factory;

import swingy.App;
import swingy.enums.EModule;
import swingy.views.IView;
import swingy.views.SwingyConsoleView;
import swingy.views.SwingyGUIGameView;
import swingy.views.SwingyGUIMainMenuView;
import swingy.views.Window;

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
}
