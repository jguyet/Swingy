package swingy.module.factory;

import swingy.enums.EModule;
import swingy.module.IModule;
import swingy.module.interfaces.ConsoleInterface;
import swingy.module.interfaces.GuiInterface;

public class ModuleFactory {

	public static IModule loadnewModule(String name) {
		
		EModule selectedModule = EModule.getModule(name.toLowerCase());
		
		if (selectedModule == EModule.CONSOLE) {
			return (new ConsoleInterface());
		}
		if (selectedModule == EModule.GUI) {
			return (new GuiInterface());
		}
		return null;
	}
}
