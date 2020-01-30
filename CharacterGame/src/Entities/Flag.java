package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import FrameWork.GameObject;
import FrameWork.ID;

public class Flag extends GameObject{

	public Flag(float x, float y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
