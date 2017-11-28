package swingy.views;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;

import swingy.model.ISwingyModel;
import swingy.views.events.ResponseListener;

public class SwingyConsoleView implements IView{

	private ArrayList<ISwingyModel> models = new ArrayList<ISwingyModel>();
	
	@SuppressWarnings("unused")
	private KeyListener keyl = null;
	@SuppressWarnings("unused")
	private MouseListener mousel = null;
	@SuppressWarnings("unused")
	private ResponseListener responselistener = null;
	
	public SwingyConsoleView() {
		
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void destroy() {
		
	}
	
	@Override
	public void update() {
		
	}
	
	public void waitResponse(ResponseListener rep) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		rep.onResponse(s.nextLine());
	}
	
	@Override
	public void addModel(ISwingyModel model) {
		models.add(model);
	}
	
	@Override
	public void removeModel(ISwingyModel model) {
		models.remove(model);
	}

	@Override
	public void addKeyListener(KeyListener l) {
		this.keyl = l;
	}

	@Override
	public void addMouseListener(MouseListener m) {
		this.mousel = m;
	}

	@Override
	public void println(String txt) {
		System.out.println(txt);
	}

	@Override
	public void print(String txt) {
		System.out.print(txt);
	}

	@Override
	public void removeKeyListener(KeyListener l) {
		this.keyl = null;
	}

	@Override
	public void removeMouseListener(MouseListener m) {
		this.mousel = null;
	}

}
