package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import Entities.MenuThing;
import FrameWork.BufferedImageLoader;
import FrameWork.GameObject;
import FrameWork.ID;
import FrameWork.KeyInput;
import FrameWork.Texture;
import FrameWork.Window;

public class Game extends Canvas implements Runnable{

	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	private Thread thread;
	private boolean running = false;
	
	private BufferedImage level = null;
	static Texture tex;
	
	private Handler handler;
	Camera cam;
	Menu menu;
	HUD hud;
	
	Random r;
	
	public static int LEVEL = 1;
	
	public enum STATE{
		Game,
		Menu,
		End,
		Help
	}
	
	public static STATE currentState = STATE.Menu;
	
	public Game() {
		cam = new Camera(0,0);
		handler = new Handler(cam);
		menu = new Menu(handler);
		tex = new Texture();
		hud = new HUD(cam);
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png");
		r = new Random();
		
		this.addKeyListener(new KeyInput(handler));
		
		if(currentState == STATE.Menu) {
			for(int i = 0; i < 10; i++)handler.addObject(new MenuThing(r.nextInt(Game.WIDTH-35), r.nextInt(Game.HEIGHT-35), ID.MenuThing));
		}
		
	}
	
	public synchronized void start() {
		if (running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
		
	}
	
//	public synchronized void stop() {
//		if (running = false) return;
//		try {
//			thread.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		running = false;
//	}
	
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
		 long now = System.nanoTime();
		 delta += (now - lastTime) / ns;
		 lastTime = now;
		 while(delta >= 1){
		  tick();
		  updates++;
		  delta--;
		 }
		 render();
		 frames++;
		   
		 if(System.currentTimeMillis() - timer > 1000){
		  timer += 1000;
		  //System.out.println("FPS: " + frames + " TICKS: " + updates);
		  frames = 0;
		  updates = 0;
		 }
		}


	}
	
	public void tick() {
		if(currentState == STATE.Game) {
		handler.tick();
		if(LEVEL == 1)handler.switchLevel(); 
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				cam.tick(handler.object.get(i));
				hud.tick(handler.object.get(i));
			}
		}
		}
		
		if(currentState == STATE.Menu || currentState == STATE.End) {

			for(int i = 0; i < handler.object.size(); i++) {
				if(handler.object.get(i).getId() == ID.MenuThing) {
					GameObject tempObject = handler.object.get(i);	
					tempObject.tick();
				}
			}
			menu.tick();
		}
		if(currentState == STATE.Help) menu.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
				
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(currentState == STATE.Game) {
		g2d.translate(cam.getX(), cam.getY());
		
		handler.render(g);
		
		hud.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY());
		}
		
		if(currentState == STATE.Menu || currentState == STATE.End) {
			for(int i = 0; i < handler.object.size(); i++) {
				if(handler.object.get(i).getId() == ID.MenuThing) {
					GameObject tempObject = handler.object.get(i);	
					tempObject.render(g);
				}
			}
			menu.render(g);
		}
		if(currentState == STATE.Help) menu.render(g  );

		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var <= min) return var = min;
		if(var >= max) return var = max;
		else return var;
	}
	
	public static Texture getInstance() {
		return tex;
	}
	
	public static void main(String args[]) {
		new Window(WIDTH, HEIGHT, "Game", new Game());
	}
}
