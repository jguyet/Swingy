package swingy.module.interfaces;

import swingy.enums.EModule;
import swingy.module.IModule;

public class ConsoleInterface implements IModule {

	public ConsoleInterface() {
		
	}

	public EModule getinstance() {
		// TODO Auto-generated method stub
		return EModule.CONSOLE;
	}
}
