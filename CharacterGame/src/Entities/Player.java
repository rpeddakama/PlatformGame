package Entities;

import Main.Handler;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import FrameWork.GameObject;
import FrameWork.ID;
import FrameWork.Texture;
import Main.Camera;
import Main.Game;
import Main.Handler;

public class Player extends GameObject{
	
	private Handler handler;
	private int width = 50, height = 60;
	private GameObject player;
	private Camera cam;
	
	Texture tex = Game.getInstance();
	
	private float gravity = 0.5f;
	private final float MAX_SPEED = 5;

	public Player(float x, float y, ID id, Handler handler, Camera cam) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
		
	}

	public void tick() {
		x+=velX;
		y+=velY;
				
		if(falling || jumping) velY+=gravity;
		if(gravity > MAX_SPEED) gravity = MAX_SPEED;
		
		collision();
	}

	public void render(Graphics g) {
		
		g.drawImage(tex.player[0], (int)x, (int)y-35, 48, 96, null);
		
//		g.setColor(Color.blue);
//		g.fillRect((int)x, (int)y, width, height);
//		g.setColor(Color.orange);
//		g.fillRect((int)x+5, (int)y+10, 15, 15);
//		g.fillRect((int)x+width-20, (int)y+10, 15, 15);
//		g.fillRect((int)x+5, (int)y+35, 40, 10);
		
		Graphics2D g2d = (Graphics2D) g;
//		 	
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x+10, (int)y+height/2, width-20, height/2);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+10, 5, height-20);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+width-5, (int)y+10, 5, height-20);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x+10, (int)y, width-20, height/2);
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Block){
				if(getBounds().intersects(tempObject.getBounds())) {
					velY = 0;
					y = tempObject.getY()-60; 
					falling = false;
					jumping = false;
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX()+27;
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX()-45;
					
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					velY = 0;
					y = tempObject.getY()+35;
				}
				
				else falling = true;
				
			}
			
			if(tempObject.getId() == ID.Flag) {
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					handler.switchLevel();
				}
			}
		}
	}

}
