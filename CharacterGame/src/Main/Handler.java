package Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import Entities.BasicEnemy;
import Entities.Block;
import Entities.Flag;
import Entities.Player;
import FrameWork.BufferedImageLoader;
import FrameWork.GameObject;
import FrameWork.ID;

public class Handler {
	
	private Random r = new Random();
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private Camera cam;
	
	private BufferedImage level2 = null, level = null;
	
	public Handler(Camera cam) {
		this.cam = cam;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level2 = loader.loadImage("/level2.png");
		level = loader.loadImage("/level.png");

	}
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearLevel() {
		object.clear();
	}
	
	public void switchLevel() {
		clearLevel();
		cam.setX(0);
		switch (Game.LEVEL) {
		case 1: 
			loadImageLevel(level);
			break;
			
		case 2:
			loadImageLevel(level2);
			break;

		
		}
		Game.LEVEL++;
		if(Game.LEVEL > 3)Game.currentState = Game.STATE.End;

	}
	public void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
				
		for(int xx = 0; xx < h; xx++) {
			for(int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;;
				int blue = (pixel) & 0xff;;
				
				if(red == 255 && green == 255 && blue == 255)addObject(new Block(xx*32, yy*32, ID.Block, 1));
				if(red == 128 && green == 128 && blue == 128)addObject(new Block(xx*32, yy*32, ID.Block, 0));
				if(red == 0 && green == 38 && blue == 255)addObject(new Player(xx*32, yy*32, ID.Player, this, cam));
				if(red == 255 && green == 216 && blue == 0)addObject(new Flag(xx*32, yy*32, ID.Flag));
				if(red == 255 && green == 0 && blue == 0)addObject(new BasicEnemy(xx*32, yy*32, ID.BasicEnemy, this));
				
			}
		}
	}
	
		
}

