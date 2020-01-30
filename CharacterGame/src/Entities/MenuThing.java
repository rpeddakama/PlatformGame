package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import FrameWork.GameObject;
import FrameWork.ID;
import Main.Game;

public class MenuThing extends GameObject{
	
	private int width = 32, height = 32;
	
	private int red, blue, green;
	
	Random r;
	Color col;

	public MenuThing(float x, float y, ID id) {
		super(x, y, id);
		 
		r = new Random();
		
		red = r.nextInt(255);
		blue = r.nextInt(255);
		green = r.nextInt(255);
		
		col = new Color(red, blue, green);
		
		velX = r.nextInt(10) + 5;
		velY = r.nextInt(10) + 5;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0) {
			x = 0;
			velX = -velX;
		}
		if(x >= Game.WIDTH) {
			x = Game.WIDTH - 35;
			velX = -velX;
		}
		if(y <= 0) {
			y = 0;
			velY = -velY;
		}
		if(y >= Game.HEIGHT) {
			y = Game.HEIGHT - 35;
			velY = -velY;
		}
	}

	public void render(Graphics g) {
		g.setColor(new Color(red, blue, green));
		g.fillRect((int)x, (int)y, width, height);
	}

	public Rectangle getBounds() {
		return null;
	}
	
}
