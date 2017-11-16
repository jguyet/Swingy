package swingy.controller.loop;

import swingy.utils.Time;

public class LoopMotor {

	public long		graphRate = (long)((double) 1000000000L) / 60;
	public long		controllerRate = (long)((double) 1000000000L) / 100;
	public boolean	running = false;
	
	private GraphicLoop graphloop;
	private ControllerLoop controllerloop;
	private MotorGraphics motor;
	
	public LoopMotor(MotorGraphics motor)
	{
		this.motor = motor;
	}
	
	public void start() {

		if (running)
			return;
		running = true;
		graphloop = new GraphicLoop();
		controllerloop = new ControllerLoop();

		controllerloop.start();
		
		graphloop.run();
	}

	public void stop() {
		if (!running)
			return;

		running = false;
	}
	
	private void call(int id) {
		switch (id) {
			case 1:
				motor.graphicRenderingLoop();
				break ;
			case 2:
				motor.graphicControllerLoop();
				break ;
		}
	}
	
	public class GraphicLoop implements Runnable {

		public GraphicLoop() {
		
		}
		
		@Override
		public void run() {

			long frames = 0;
			long frameCounter = 0;

			long lastTime = Time.getTime();

			while (running) {
				
				long startTime = Time.getTime();
				long passedTime = startTime - lastTime;
				
				if (passedTime > graphRate)
				{
					lastTime = Time.getTime();
					Time.setDelta(passedTime);
					Time.setFrame(frames);
					call(1);
					frames++;
					frameCounter++;
				} else {
					try { Thread.sleep(10); } catch (Exception e) {}
				}
				if (frameCounter >= 1000000000L) {
					frames = 0;
					frameCounter = 0;
				}
			}
		}
	}
	
	public class ControllerLoop implements Runnable {

		private Thread _t;
		
		public ControllerLoop() {
			this._t = new Thread(this);
		}
		
		public void start() {
			this._t.start();
		}
		
		@Override
		public void run() {

			long lastTime = Time.getTime();
			
			while (running) {
				
				long startTime = Time.getTime();
				long passedTime = startTime - lastTime;
				
				if (passedTime > controllerRate)
				{
					lastTime = Time.getTime();
					call(2);
				} else {
					try { Thread.sleep(10); } catch (Exception e) {}
				}
			}
		}
	}
}
