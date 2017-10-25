package swingy.module.interfaces;

import swingy.enums.EModule;
import swingy.module.IModule;

public class GuiInterface implements IModule{

	public GuiInterface() {
		
	}

	public EModule getinstance() {
		// TODO Auto-generated method stub
		return EModule.GUI;
	}
}
