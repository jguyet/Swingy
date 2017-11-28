package swingy.views;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import swingy.model.ISwingyModel;
import swingy.views.events.ResponseListener;

public interface IView {
	
	public void init();
	
	public void destroy();
	
	public void update();
	
	public void addModel(ISwingyModel model);
	
	public void removeModel(ISwingyModel model);
	
	public void addKeyListener(KeyListener l);
	
	public void addMouseListener(MouseListener m);
	
	public void removeKeyListener(KeyListener l);
	
	public void removeMouseListener(MouseListener m);
	
	public void waitResponse(ResponseListener rep);
	
	public void println(String txt);
	
	public void print(String txt);
}
