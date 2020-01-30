package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import FrameWork.GameObject;
import FrameWork.ID;
import FrameWork.Texture;
import Main.Game;

public class Block extends GameObject{
	
	Texture tex = Game.getInstance();
	private int type;

	public Block(float x, float y, ID id, int type) {
		super(x, y, id);
		this.type = type;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if(type == 0) g.drawImage(tex.block[0], (int)x, (int)y, null);
		if(type == 1) g.drawImage(tex.block[1], (int)x, (int)y, null);
		
		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(Color.green);
//		g2d.draw(getBounds());
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
